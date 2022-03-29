package cs.hse.telesfor.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * То, что будеь возвращаться по endpoint-у /users.
 * Также, то, что будет получать сервер от клиента для редактирования аккаунта.
 */

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountResponse {

    private final Long id;
    private final String phoneNumber;
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String age;
    private final String height;
    private final String education;
    private final String workExperience;
    private final String specialization;
    private final String role;
    private final Boolean locked;
    private  final Boolean enabled;


}
