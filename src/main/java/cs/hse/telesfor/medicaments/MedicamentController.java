package cs.hse.telesfor.medicaments;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/medicaments")
public class MedicamentController {

    private MedicamentService medicamentService;

    @GetMapping
    public @ResponseBody List<MedicamentRequest> getAllMedicaments(){
        return medicamentService.getAllMedicaments();
    }

    @PostMapping(path = "add")
    public String addMedicament(@RequestBody MedicamentRequest request){
        return medicamentService.addNewMedicament(request);
    }

    //TODO: Populate everything
    @GetMapping(path ="delete")
    public String deleteMedicament(@RequestParam("medicamentId") String medicamentId){
        return "deleted successfully";
    }

    @GetMapping(path = "patient")
    public @ResponseBody List<PatientMedicamentResponse> getPatientMeds(@RequestParam("id") String patientId){
        return medicamentService.getPatientMedicaments(patientId);
    }

    @PostMapping(path = "patient/add")
    public String addMedicamentForPatient(@RequestBody PatientMedicamentRequest request){
        return medicamentService.addMedicamentToPatient(request);
    }

    @GetMapping(path ="patient/delete")
    public String deleteMedicamentFromPatient(@RequestParam("id") String id,
                                              @RequestParam("patientId") String patientId){
        return medicamentService.deleteFromPatient(id, patientId);
    }


}
