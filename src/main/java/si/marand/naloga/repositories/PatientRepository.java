package si.marand.naloga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.marand.naloga.entities.PatientEntity;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
}
