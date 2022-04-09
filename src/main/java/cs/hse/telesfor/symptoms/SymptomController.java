package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/symptoms")
public class SymptomController {

    private SymptomService symptomService;

    @GetMapping
    public @ResponseBody List<SymptomResponse> getAllSymptoms(){
        return symptomService.getAllSymptoms();
    }

    @PostMapping(path = "add")
    public String addSymptom(@RequestBody SymptomRequest request){
        return symptomService.addSymptom(request);
    }

    @GetMapping(path = "patient")
    public @ResponseBody List<PatientSymptomResponse> getPatientSymptoms(@RequestParam("id") String patientId){
        return symptomService.getPatientSymptoms(patientId);
    }

    @PostMapping(path = "patient/add")
    public String addPatientSymptom(@RequestBody PatientSymptomRequest patientSymptomRequest){
        return symptomService.addPatientSymptom(patientSymptomRequest);
    }

    @GetMapping(path = "patient/delete")
    public String deletePatientSymptom(@RequestParam("id") String rowId, @RequestParam("patientId") String patientId){
        return symptomService.deletePatientSymptom(rowId, patientId);
    }
}