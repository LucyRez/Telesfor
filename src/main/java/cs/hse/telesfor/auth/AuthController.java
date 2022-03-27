package cs.hse.telesfor.auth;


import cs.hse.telesfor.registration.RegistrationRequest;
import cs.hse.telesfor.registration.RegistrationService;
import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(path="api/auth")
@AllArgsConstructor
public class AuthController {

    private UserService service;


    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Account getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        Account user = (principal instanceof Account) ? (Account) principal : null;
        return Objects.nonNull(user) ? this.service.getAccountByLogin(user.getUsername()) : null;
    }

}
