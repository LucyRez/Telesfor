package cs.hse.telesfor.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/registration")
@AllArgsConstructor
public class RegistrationController {

    //https://lrezunic@gmail.com:6DxPXS3CSPjDOPsCVIFkbZ8WjPgY@gate.smsaero.ru/v2/sms/send?numbers[]=79296304906&text=your+text&sign=Telesfor
    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("code") String code ){
        return registrationService.confirmCode(code);
    }

}
