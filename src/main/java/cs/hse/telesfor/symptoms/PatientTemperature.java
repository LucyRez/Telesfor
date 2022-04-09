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
public class PatientTemperature {

    @SequenceGenerator(
            name = "temperature_sequence",
            sequenceName = "temperature_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temperature_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String patientId;
    private String temperature;

    public PatientTemperature(String date, String patientId, String temperature) {
        this.date = date;
        this.patientId = patientId;
        this.temperature = temperature;
    }
}
