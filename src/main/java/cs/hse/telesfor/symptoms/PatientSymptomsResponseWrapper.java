package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientSymptomsResponseWrapper {
    List<PatientSymptomResponse> symptoms;
}
