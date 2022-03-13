package cs.hse.telesfor.user;

import cs.hse.telesfor.registration.token.ConfirmationCode;
import cs.hse.telesfor.registration.token.ConfirmationCodeService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

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

    public String signUp(Account account){

        boolean userExists = userRepository.findByPhoneNumber(account.getPhoneNumber()).isPresent();

        if(userExists){
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

        //TODO: Send SMS

        return generatedCode;
    }

    public int enableUser(String phoneNumber) {
        return userRepository.enableUser(phoneNumber);
    }
}
