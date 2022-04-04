package cs.hse.telesfor.medicaments;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientMedicamentResponse {

    private String idRow;
    private String patientId;
    private String name;
    private String startDate;
    private String endDate;
    private String numDays;
    private String type;
    private String dosage;
}
