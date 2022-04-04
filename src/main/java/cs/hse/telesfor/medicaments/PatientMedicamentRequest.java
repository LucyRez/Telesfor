package cs.hse.telesfor.medicaments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientMedicamentRequest {
    private String name;
    private String startDate;
    private String endDate;
    private String numDays;
    private String type;
    private String dosage;
    private String patientId;
}
