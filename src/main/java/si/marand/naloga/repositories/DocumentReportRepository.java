package si.marand.naloga.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import si.marand.naloga.entities.DocumentReportEntity;

@Repository
public interface DocumentReportRepository extends JpaRepository<DocumentReportEntity, Long> {

    @Query("SELECT d from document_report d where d.doctor.id= :doctorId")
    List<DocumentReportEntity> findByDoctorId(@Param("doctorId") Long doctorId);
}
