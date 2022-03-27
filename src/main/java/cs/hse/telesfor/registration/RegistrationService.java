package cs.hse.telesfor.registration;

import cs.hse.telesfor.registration.token.ConfirmationCode;
import cs.hse.telesfor.registration.token.ConfirmationCodeService;
import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final PhoneNumberValidator validator;
    private final ConfirmationCodeService confirmationCodeService;
    private final UserService userService;



    public String register(RegistrationRequest request) {
        if (!validator.test(request.getPhoneNumber())) {
            return String.format("phone number %s is not valid", request.getPhoneNumber());
        }

        String code = userService.signUp(
                new Account(
                        request.getPhoneNumber(),
                        request.getPassword(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getPatronymic()
                )
        );


        String createRequest = String.format(
                "https://lrezunic@gmail.com:6DxPXS3CSPjDOPsCVIFkbZ8WjPgY@gate.smsaero.ru/v2/sms/send?number=%s&text=%s&sign=SMS+Aero",
                request.getPhoneNumber(), code);


        // Client should send a get request with confirmation code

        return code;

    }

    @Transactional
    public String confirmCode(String code) {
        ConfirmationCode confirmationCode = confirmationCodeService.getCode(code)
                .orElseThrow(() ->
                        new IllegalStateException("code not found"));

        if (confirmationCode.getConfirmedAt() != null) {
            throw new IllegalStateException("code already confirmed");
        }

        LocalDateTime expiredAt = confirmationCode.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("code expired");
        }

        confirmationCodeService.setConfirmedAt(code);

        userService.enableUser(
                confirmationCode.getAccount().getPhoneNumber());

        return "confirmed";
    }
}
