package cs.hse.telesfor.user;

import com.fnklabs.smsaero.client.core.Credentials;
import com.fnklabs.smsaero.client.core.SmsAeroClient;
import com.fnklabs.smsaero.client.http.HttpClientTransport;
import com.fnklabs.smsaero.client.jackson.JacksonMarshaller;
import cs.hse.telesfor.registration.token.ConfirmationCode;
import cs.hse.telesfor.registration.token.ConfirmationCodeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "User with phone number %s has not been found";
    private final UserRepository userRepository;
    private final ConfirmationCodeService confirmationCodeService;
    private final BCryptPasswordEncoder encoder;

    // Username is a phone number
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public AccountResponse getAccountByLogin(String login) {
        Account user = userRepository.findByPhoneNumber(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, login)));

        return new AccountResponse(user.getId(), user.getPhoneNumber(), user.getFirstName(),
                user.getLastName(), user.getPatronymic(), user.getAge(), user.getHeight(), user.getEducation(),
                user.getWorkExperience(), user.getSpecialization(), user.getRole().name(), user.getLocked(),
                user.getEnabled());
    }

    public List<AccountResponse> getAllAccounts() {
        return userRepository.findAll()
                .stream()
                .map(user -> new AccountResponse(user.getId(), user.getPhoneNumber(), user.getFirstName(),
                        user.getLastName(), user.getPatronymic(), user.getAge(), user.getHeight(), user.getEducation(),
                        user.getWorkExperience(), user.getSpecialization(), user.getRole().name(), user.getLocked(),
                        user.getEnabled())).collect(Collectors.toList());
    }

    public List<AccountResponse> getAllDoctors(){
        return userRepository.getAllUsersByRole(UserRole.ROLE_DOCTOR)
                .stream()
                .map(user -> new AccountResponse(user.getId(), user.getPhoneNumber(), user.getFirstName(),
                        user.getLastName(), user.getPatronymic(), user.getAge(), user.getHeight(), user.getEducation(),
                        user.getWorkExperience(), user.getSpecialization(), user.getRole().name(), user.getLocked(),
                        user.getEnabled())).collect(Collectors.toList());
    }

    public List<AccountResponse> getAllPatients(){
        return userRepository.getAllUsersByRole(UserRole.ROLE_USER)
                .stream()
                .map(user -> new AccountResponse(user.getId(), user.getPhoneNumber(), user.getFirstName(),
                        user.getLastName(), user.getPatronymic(), user.getAge(), user.getHeight(), user.getEducation(),
                        user.getWorkExperience(), user.getSpecialization(), user.getRole().name(), user.getLocked(),
                        user.getEnabled())).collect(Collectors.toList());
    }



    public String signUp(Account account) {

        boolean userExists = userRepository.findByPhoneNumber(account.getPhoneNumber()).isPresent();

        if (userExists) {
            throw new IllegalStateException(
                    String.format("User with phone number %s already exists", account.getPhoneNumber()));
        }

        String encodedPassword = encoder.encode(account.getPassword());
        account.setPassword(encodedPassword);

        userRepository.save(account);

        Random random = new Random();
        String generatedCode = String.format("%04d", random.nextInt(10000));

        ConfirmationCode code = new ConfirmationCode(generatedCode, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), account);

        confirmationCodeService.saveConfirmationCode(code);

        //TODO: Найти другой сервис для отправки смс сообщений

        try {
            SmsAeroClient smsAeroClient = SmsAeroClient.builder()
                    .setCredentials(new Credentials("lrezunic@gmail.com", "6DxPXS3CSPjDOPsCVIFkbZ8WjPgY"))
                    .setTransport(new HttpClientTransport(new JacksonMarshaller()))
                    .build();

            if (!smsAeroClient.send(account.getPhoneNumber(), "SMS Aero", generatedCode)) {
                throw new IllegalStateException("can't send sms");
            }

        } catch (Exception e) {
            throw new IllegalStateException("Exception while sending SMS");

        }

        return generatedCode;
    }

    public int enableUser(String phoneNumber) {
        return userRepository.enableUser(phoneNumber);
    }

}

