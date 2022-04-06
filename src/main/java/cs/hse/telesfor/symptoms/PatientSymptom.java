package cs.hse.telesfor.symptoms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class PatientSymptom {
    @SequenceGenerator(
            name = "patientSymptom_sequence",
            sequenceName = "patientSymptom_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patientSymptom_sequence"
    )
    private Long id;
    private String severity;
    @Column(nullable = false)
    private String date;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "symptom_id")
    private Symptom symptom;
    @Column(nullable = false)
    private String patientId;

    public PatientSymptom(String severity, String date, Symptom symptom, String patientId) {
        this.severity = severity;
        this.date = date;
        this.symptom = symptom;
        this.patientId = patientId;
    }
}
