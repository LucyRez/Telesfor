package cs.hse.telesfor.registration.token;

import cs.hse.telesfor.user.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ConfirmationCode {

    @SequenceGenerator(
            name = "confirmation_code_sequence",
            sequenceName = "confirmation_code_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_code_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "account_id")
    private Account account;


    public ConfirmationCode(String code, LocalDateTime createdAt, LocalDateTime expiresAt, Account account) {
        this.code = code;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.account = account;
    }
}
