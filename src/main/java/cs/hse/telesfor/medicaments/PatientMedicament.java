package cs.hse.telesfor.medicaments;

import cs.hse.telesfor.user.Account;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
public class PatientMedicament {
    @SequenceGenerator(
            name = "medicament_sequence",
            sequenceName = "medicament_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicament_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String startDate;
    @Column(nullable = false)
    private String endDate;
    private String numDays;

    @Column(nullable = false)
    private String name;
    private String type;
    private String dosage;


    @Column(nullable = false)
    private String patientId;


    public PatientMedicament(String startDate, String endDate, String numDays, String name, String type,
                             String dosage, String patientId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numDays = numDays;
        this.name = name;
        this.type = type;
        this.dosage = dosage;
        this.patientId = patientId;
    }
}
