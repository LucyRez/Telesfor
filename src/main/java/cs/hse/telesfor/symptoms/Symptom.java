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
public class Symptom {
    @SequenceGenerator(
            name = "symptom_sequence",
            sequenceName = "symptom_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "symptom_sequence"
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;

    public Symptom(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
