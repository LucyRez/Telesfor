package cs.hse.telesfor.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    // TODO: Change responses in methods

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("code") String code ){
        return registrationService.confirmCode(code);
    }

}
