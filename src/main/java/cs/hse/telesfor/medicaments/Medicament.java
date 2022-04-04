package cs.hse.telesfor.medicaments;

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
public class Medicament {

    @SequenceGenerator(
            name = "medicine_sequence",
            sequenceName = "medicine_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "medicine_sequence"
    )
    private Long id;
    private String name;
    private String type;
    private String dosage;

    public Medicament(String name, String type, String dosage) {
        this.name = name;
        this.type = type;
        this.dosage = dosage;
    }
}
