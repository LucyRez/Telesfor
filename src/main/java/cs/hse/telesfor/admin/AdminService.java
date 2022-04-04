package cs.hse.telesfor.admin;

import com.fnklabs.smsaero.client.core.Credentials;
import com.fnklabs.smsaero.client.core.SmsAeroClient;
import com.fnklabs.smsaero.client.http.HttpClientTransport;
import com.fnklabs.smsaero.client.jackson.JacksonMarshaller;
import cs.hse.telesfor.registration.PhoneNumberValidator;
import cs.hse.telesfor.registration.RegistrationRequest;
import cs.hse.telesfor.registration.token.ConfirmationCode;
import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.UserRepository;
import cs.hse.telesfor.user.UserRole;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AdminService {

    // TODO: Populate all methods
    // TODO: Change all responses
    private final UserService userService;
    private final PhoneNumberValidator validator;

    public String registerDoctor(RegistrationRequest request) {

        if (!validator.test(request.getPhoneNumber())) {
            return String.format("phone number %s is not valid", request.getPhoneNumber());
        }

        String code = userService.signUp(
                new Account(
                        request.getPhoneNumber(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPatronymic(),
                        UserRole.ROLE_DOCTOR
                )
        );

        return String.format("success %s", code);
    }

    public String editUser(String userId) {
        return "successful edit of user " + userId;
    }

    public String deleteUser(String userId) {
        return "successful delete of user " + userId;
    }
}
