package cs.hse.telesfor.questionnaire;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientAnswerResponse {

    private String patientId;
    private List<Answer> answerList;
    private String wellBeing;
    private String mood;
    private String activity;
    private String date;

}
