package cs.hse.telesfor.symptoms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientSymptomRepository extends JpaRepository<PatientSymptom, Long> {
    List<PatientSymptom> findPatientSymptomByPatientId(String patientId);

    @Transactional
    @Modifying
    int deletePatientSymptomByIdAndPatientId(Long id, String patientId);
}
