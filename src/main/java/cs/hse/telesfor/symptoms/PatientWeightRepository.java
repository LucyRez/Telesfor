package cs.hse.telesfor.symptoms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientWeightRepository extends JpaRepository<PatientWeight, Long> {
    List<PatientWeight> findPatientWeightsByPatientId(String patientId);
}
