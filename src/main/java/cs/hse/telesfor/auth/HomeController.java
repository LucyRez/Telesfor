package cs.hse.telesfor.auth;

import cs.hse.telesfor.registration.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/")
@AllArgsConstructor
public class HomeController {
    @GetMapping
    public String sayHello(){
        return "Api is working! Hello ^^";
    }
}
