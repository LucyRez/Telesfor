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
    public @ResponseBody SymptomsResponseWrapper getAllSymptoms(){
        return symptomService.getAllSymptoms();
    }

    @PostMapping(path = "add")
    public String addSymptom(@RequestBody SymptomRequest request){
        return symptomService.addSymptom(request);
    }

    @GetMapping(path = "patient")
    public @ResponseBody PatientSymptomsResponseWrapper getPatientSymptoms(@RequestParam("id") String patientId){
        return symptomService.getPatientSymptoms(patientId);
    }

    @PostMapping(path = "patient/add")
    public String addPatientSymptom(@RequestBody PatientHealthRequest patientHealthRequest){
        return symptomService.addPatientSymptom(patientHealthRequest);
    }

    @GetMapping(path = "patient/delete")
    public String deletePatientSymptom(@RequestParam("id") String rowId, @RequestParam("patientId") String patientId){
        return symptomService.deletePatientSymptom(rowId, patientId);
    }

    @GetMapping("health-stats/patient")
    public @ResponseBody List<PatientHealthResponse> getPatientHealthStats(@RequestParam("id") String patientId){
        return symptomService.getPatientHealthStats(patientId);
    }
}
