package cs.hse.telesfor.questionnaire;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/test-questions")
public class QuestionnaireController {

    @GetMapping
    public @ResponseBody Questionnaire getTestQuestions(){
        return new Questionnaire();
    }


}
