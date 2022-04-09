package cs.hse.telesfor.questionnaire;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class Answer{
    private String questionNr;
    private String value;
}

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AnswerRequest {
    private String patientId;
    private List<Answer> answers;
}
