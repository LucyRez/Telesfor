package cs.hse.telesfor.registration;

import cs.hse.telesfor.registration.token.ConfirmationCode;
import cs.hse.telesfor.registration.token.ConfirmationCodeService;
import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PhoneNumberValidator validator;
    private final ConfirmationCodeService confirmationCodeService;
    private final UserService userService;

    public String register(RegistrationRequest request){
        if(!validator.test(request.getPhoneNumber())){
            return String.format("phone number %s is not valid", request.getPhoneNumber());
        }

        return userService.signUp(
                new Account(
                        request.getPhoneNumber(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPatronymic()
                )
        );

    }

    @Transactional
    public String confirmCode(String code) {
        ConfirmationCode confirmationCode = confirmationCodeService.getCode(code)
                .orElseThrow(() ->
                        new IllegalStateException("code not found"));

        if (confirmationCode.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationCode.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationCodeService.setConfirmedAt(code);

       userService.enableUser(
                confirmationCode.getAccount().getPhoneNumber());

        return "confirmed";
    }
}
