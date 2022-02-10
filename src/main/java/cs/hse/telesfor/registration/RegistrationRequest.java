package cs.hse.telesfor.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
}
