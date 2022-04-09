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
public class PatientWeight {
    @SequenceGenerator(
            name = "weight_sequence",
            sequenceName = "weight_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "weight_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String patientId;
    private String weight;

    public PatientWeight(String date, String patientId, String weight) {
        this.date = date;
        this.patientId = patientId;
        this.weight = weight;
    }
}
