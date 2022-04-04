package cs.hse.telesfor.medicaments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    Optional<Medicament> findByName(String name);
}
