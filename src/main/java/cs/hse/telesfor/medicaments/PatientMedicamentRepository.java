package cs.hse.telesfor.medicaments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientMedicamentRepository extends JpaRepository<PatientMedicament, Long> {

    Optional<PatientMedicament> findByPatientId(String id);

    @Query("SELECT med FROM PatientMedicament med WHERE med.patientId = ?1")
    List<PatientMedicament> getPatientsMedicaments(String patientId);


    @Transactional
    @Modifying
    int deletePatientMedicamentByIdAndPatientId(Long id, String patientId);
}
