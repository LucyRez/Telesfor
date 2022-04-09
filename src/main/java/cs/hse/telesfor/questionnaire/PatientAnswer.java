package cs.hse.telesfor.questionnaire;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class PatientAnswer {

    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )
    private Long id;
    private String patientId;

    @ElementCollection
    private List<Integer> answerList;

    private Integer wellBeing;
    private Integer mood;
    private Integer activity;
    private LocalDate date;

    public PatientAnswer(String patientId, List<Integer> answerList, Integer wellBeing, Integer mood,
                         Integer activity, LocalDate date) {
        this.patientId = patientId;
        this.answerList = answerList;
        this.wellBeing = wellBeing;
        this.mood = mood;
        this.activity = activity;
        this.date = date;
    }
}
