package cs.hse.telesfor.user;


import cs.hse.telesfor.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(path="api/users")
    public @ResponseBody List<Account> getAllUsers(){
        return userService.getAllAccounts();
    }

    @GetMapping(path = "api/user")
    public @ResponseBody Account getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        Account user = (principal instanceof Account) ? (Account) principal : null;
        return Objects.nonNull(user) ? this.userService.getAccountByLogin(user.getUsername()) : null;
    }

}
