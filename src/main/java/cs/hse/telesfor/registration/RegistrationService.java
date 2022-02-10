package cs.hse.telesfor.registration;

import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PhoneNumberValidator validator;
    private final UserService userService;

    public String register(RegistrationRequest request){
        if(!validator.test(request.getPhoneNumber())){
            return String.format("phone number %s is not valid", request.getPhoneNumber());
        }

        userService.signUp(
                new Account(
                        request.getPhoneNumber(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPatronymic()
                )
        );

        return "it works";
    }
}
