package cs.hse.telesfor.admin;

import cs.hse.telesfor.registration.RegistrationRequest;
import cs.hse.telesfor.user.Account;
import cs.hse.telesfor.user.AccountResponse;
import cs.hse.telesfor.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Контроллер, отвечающий за endpoint-ы доступные только администраторам
 */
@RestController
@AllArgsConstructor
@RequestMapping(path="api/admin")
public class AdminController {

    private AdminService adminService;

    @PostMapping(path="register-doctor")
    public String registerDoctor(@RequestBody RegistrationRequest request){
        return adminService.registerDoctor(request);
    }

    @PostMapping(path = "edit-user")
    public String editUser(@RequestParam("id") String userId ){
        return adminService.editUser(userId);
    }

    @GetMapping(path = "delete-user")
    public String deleteUser(@RequestParam("id") String userId) {return adminService.deleteUser(userId);}



}
