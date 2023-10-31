package si.marand.naloga.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import si.marand.naloga.entities.DoctorEntity;

@Repository
public interface DoctorRepository  extends JpaRepository<DoctorEntity, Long> {
}
