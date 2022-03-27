package cs.hse.telesfor.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationCodeRepository extends JpaRepository<ConfirmationCode, Long> {

    Optional<ConfirmationCode> findByCode(String code);

    /**
     * Изменить время подтверждения кода в БД
     * @param code Код
     * @param confirmedAt Время подтверждения
     * @return Код ответа
     */
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationCode c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.code = ?1")
    int updateConfirmedAt(String code,
                          LocalDateTime confirmedAt);
}
