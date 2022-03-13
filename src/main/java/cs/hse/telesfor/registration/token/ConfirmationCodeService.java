package cs.hse.telesfor.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationCodeService {

    private final ConfirmationCodeRepository confirmationCodeRepository;

    public void saveConfirmationCode(ConfirmationCode code){
        confirmationCodeRepository.save(code);
    }

    public Optional<ConfirmationCode> getCode(String code){
        return confirmationCodeRepository.findByCode(code);
    }

    public int setConfirmedAt(String code) {
        return confirmationCodeRepository.updateConfirmedAt(
                code, LocalDateTime.now());
    }
}
