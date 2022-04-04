package cs.hse.telesfor.medicaments;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MedicamentRequest {
    private String name;
    private String type;
    private String dosage;

}
