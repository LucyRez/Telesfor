package cs.hse.telesfor.questionnaire;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<PatientAnswer, Long> {

    List<PatientAnswer> getPatientAnswersByPatientId(String patientId);
}
