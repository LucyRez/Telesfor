package cs.hse.telesfor.questionnaire;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/questionnaire")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @GetMapping(path = "test-questions")
    public @ResponseBody Questionnaire getTestQuestions(){
        return new Questionnaire();
    }

    @PostMapping(path = "answer")
    public String savePatientAnswers(@RequestBody AnswerRequest request){
        return questionnaireService.saveAnswers(request);
    }

    @GetMapping(path = "patient")
    public @ResponseBody List<PatientAnswerResponse> getPatientAnswers(@RequestParam("id") String id){
        return questionnaireService.getPatientAnswers(id);
    }

}
