package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientSymptomResponse {
    private String id;
    private String patientId;
    private String name;
    private String description;
    private String severity;
    private String date;
}
