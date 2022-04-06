package cs.hse.telesfor.symptoms;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SymptomResponse {
    private String id;
    private String name;
    private String description;
}
