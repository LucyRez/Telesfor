package cs.hse.telesfor.symptoms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientTemperatureRepository extends JpaRepository<PatientTemperature, Long> {
    List<PatientTemperature> findPatientTemperaturesByPatientId(String patientId);
}
