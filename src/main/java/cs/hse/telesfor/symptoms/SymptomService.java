package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;
    private final PatientSymptomRepository patientSymptomRepository;

    public List<SymptomResponse> getAllSymptoms(){
        return symptomRepository.findAll()
                .stream()
                .map(symptom -> new SymptomResponse(symptom.getId().toString(), symptom.getName(),
                        symptom.getDescription()))
                .collect(Collectors.toList());
    }

    public String addSymptom(SymptomRequest request){
        Symptom newSymptom = new Symptom(request.getName(), request.getDescription());
        symptomRepository.save(newSymptom);
        return String.format("Symptom %s has been saved", newSymptom.getName());
    }

    public List<PatientSymptomResponse> getPatientSymptoms(String patientId){
        return patientSymptomRepository.findPatientSymptomByPatientId(patientId)
                .stream()
                .map(patientSymptom -> new PatientSymptomResponse(patientSymptom.getId().toString(),
                        patientSymptom.getPatientId(), patientSymptom.getSymptom().getName(),
                        patientSymptom.getSymptom().getDescription(), patientSymptom.getSeverity(),
                        patientSymptom.getDate()))
                .collect(Collectors.toList());
    }

    public String addPatientSymptom(PatientSymptomRequest request){
        Symptom newSymptom = symptomRepository.getById(Long.parseLong(request.getSymptomId()));
        PatientSymptom patientSymptom = new PatientSymptom(request.getSeverity(), request.getDate(), newSymptom,
                request.getPatientId());
        patientSymptomRepository.save(patientSymptom);
        return String.format("Symptom %s has been saved for user", patientSymptom.getSymptom().getName());
    }

    public String deletePatientSymptom(String rowId, String patientId){
        patientSymptomRepository.deletePatientSymptomByIdAndPatientId(Long.parseLong(rowId), patientId);
        return "symptom has been deleted from user";
    }


}
