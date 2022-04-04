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
@RequestMapping(path="api/users")
public class UserController {

    private UserService userService;

    @GetMapping(path = "all")
    public @ResponseBody List<AccountResponse> getAllUsers(){
        return userService.getAllAccounts();
    }

    @GetMapping(path = "current")
    public @ResponseBody AccountResponse getCurrentUser(){
        // TODO: Это можно переписать, опрокинув в параметры метода Principal (смотри туториал от GeekBrains 25 минута)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        Account user = (principal instanceof Account) ? (Account) principal : null;
        return Objects.nonNull(user) ? this.userService.getAccountByLogin(user.getUsername()) : null;
    }

    @GetMapping(path = "doctors")
    public @ResponseBody List<AccountResponse> getAllDoctors(){
        return userService.getAllDoctors();
    }

    @GetMapping(path = "patients")
    public @ResponseBody List<AccountResponse> getAllPatients(){
        return userService.getAllPatients();
    }

}
