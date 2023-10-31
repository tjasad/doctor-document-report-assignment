package si.marand.naloga;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import si.marand.naloga.entities.DoctorEntity;
import si.marand.naloga.entities.DocumentReportEntity;
import si.marand.naloga.repositories.DocumentReportRepository;
import si.marand.naloga.services.DoctorService;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DoctorServiceTest {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DocumentReportRepository documentReportRepository;

    private DoctorEntity doctorEntity;

    @BeforeEach
    void setUp() {
        doctorEntity = new DoctorEntity();
        doctorEntity.setId(232L);
        doctorEntity.setDepartment("test1");
        doctorEntity.setPatients(new ArrayList<>());
    }

    @Test
    @Transactional
    void saveDoctorTest() {
        DoctorEntity createdDoctor = doctorService.save(doctorEntity);

        assertThat(createdDoctor).isNotNull();
        assertThat(createdDoctor.getDepartment()).isEqualTo("test1");
    }

    @Test
    @Transactional
    void getDoctorTest() {
        doctorService.save(doctorEntity);

        Optional<DoctorEntity> doctor = doctorService.getById(232L);

        assertThat(doctor.isPresent()).isTrue();
    }

    @Test
    @Transactional
    void getDoctorsTest() {
        doctorService.save(doctorEntity);

        List<DoctorEntity> doctors = doctorService.getAll();

        assertThat(doctors).isNotEmpty();
        assertThat(doctors.size()).isEqualTo(2);
    }

    @Test
    @Transactional
    void reportCreatedTest() {
        doctorService.save(doctorEntity);

        List<DocumentReportEntity> documentReport = documentReportRepository.findByDoctorId(232L);
        assertThat(documentReport).isNotEmpty();
    }
}
