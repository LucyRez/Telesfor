package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SymptomService {

    private final SymptomRepository symptomRepository;
    private final PatientSymptomRepository patientSymptomRepository;
    private final PatientWeightRepository patientWeightRepository;
    private final PatientTemperatureRepository patientTemperatureRepository;

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

    public List<PatientHealthResponse> getPatientHealthStats(String patientId){
        List<PatientWeight> weight = patientWeightRepository.findPatientWeightsByPatientId(patientId);
        List<PatientTemperature> temperature = patientTemperatureRepository.findPatientTemperaturesByPatientId(patientId);
        List<PatientHealthResponse> response = new ArrayList<>();
        for (int i = 0; i < weight.size(); i++) {
            response.add(new PatientHealthResponse(patientId, temperature.get(i).getDate(), temperature.get(i).getTemperature(),
                    weight.get(i).getWeight()));
        }

        return response;
    }

    public String addPatientSymptom(PatientHealthRequest request){

        for (PatientSymptomRequest symptomRequest: request.getSymptoms()) {
            Symptom newSymptom = symptomRepository.getById(Long.parseLong(symptomRequest.getSymptomId()));
            PatientSymptom patientSymptom = new PatientSymptom(symptomRequest.getSeverity(), LocalDate.now().toString(),
                    newSymptom, request.getPatientId());
            patientSymptomRepository.save(patientSymptom);
        }

        PatientWeight weight = new PatientWeight(LocalDate.now().toString(), request.getPatientId(), request.getWeight());
        PatientTemperature temperature = new PatientTemperature(LocalDate.now().toString(), request.getPatientId(),
                request.getBodyTemperature());

        patientTemperatureRepository.save(temperature);
        patientWeightRepository.save(weight);

        return String.format("Symptoms have been saved for user");
    }

    public String deletePatientSymptom(String rowId, String patientId){
        patientSymptomRepository.deletePatientSymptomByIdAndPatientId(Long.parseLong(rowId), patientId);
        return "symptom has been deleted from user";
    }


}
