package cs.hse.telesfor.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationCodeService {

    private final ConfirmationCodeRepository confirmationCodeRepository;

    /**
     * Метод для сохранения кода подтверждения в БД
     * @param code Код подтверждения
     */
    public void saveConfirmationCode(ConfirmationCode code){
        confirmationCodeRepository.save(code);
    }

    /**
     * Метод для получения объекта кода из БД
     * @param code Текстовое значение кода
     * @return Объект ConfirmationCode
     */
    public Optional<ConfirmationCode> getCode(String code){
        return confirmationCodeRepository.findByCode(code);
    }

    /**
     * Метод устанавливает код из БД как подтверждённый
     * @param code Текстовое значение кода
     * @return Код ответа
     */
    public int setConfirmedAt(String code) {
        return confirmationCodeRepository.updateConfirmedAt(
                code, LocalDateTime.now());
    }

}
