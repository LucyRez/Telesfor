package cs.hse.telesfor.medicaments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;
    private final PatientMedicamentRepository patientMedicamentRepository;


    public String addNewMedicament(MedicamentRequest request) {
        // TODO: Add some checking
        Medicament medicament = new Medicament(request.getName(), request.getType(), request.getDosage());
        medicamentRepository.save(medicament);
        return String.format("successfully saved medicament %s", medicament.getName());
    }

    public List<MedicamentRequest> getAllMedicaments() {
        return medicamentRepository.findAll()
                .stream()
                .map(medicament -> new MedicamentRequest(medicament.getName(), medicament.getType(), medicament.getDosage()))
                .collect(Collectors.toList());
    }

    public List<PatientMedicamentResponse> getPatientMedicaments(String patientId) {
        return patientMedicamentRepository.getPatientsMedicaments(patientId)
                .stream()
                .map(medicament -> new PatientMedicamentResponse(medicament.getId().toString(), medicament.getName(), medicament.getStartDate(),
                        medicament.getEndDate(), medicament.getNumDays(), medicament.getType(),
                        medicament.getDosage(), medicament.getPatientId()))
                .collect(Collectors.toList());
    }

    public String addMedicamentToPatient(PatientMedicamentRequest request) {
        PatientMedicament medicament = new PatientMedicament(request.getStartDate(), request.getEndDate(),
                request.getNumDays(), request.getName(), request.getType(), request.getDosage(), request.getPatientId());

        patientMedicamentRepository.save(medicament);
        return String.format("medicament %s has been added to patient", medicament.getName());
    }

    public String deleteFromPatient(String id, String patientId){
        patientMedicamentRepository.deletePatientMedicamentByIdAndPatientId(Long.parseLong(id),patientId);
        return "deleted successfully";
    }

}
