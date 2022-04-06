package cs.hse.telesfor.symptoms;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SymptomRequest {
    private String name;
    private String description;
}
