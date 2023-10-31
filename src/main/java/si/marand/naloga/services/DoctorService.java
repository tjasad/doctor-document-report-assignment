package si.marand.naloga.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import si.marand.naloga.entities.DoctorEntity;
import si.marand.naloga.entities.DocumentReportEntity;
import si.marand.naloga.repositories.DoctorRepository;
import si.marand.naloga.repositories.DocumentReportRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DocumentReportRepository documentReportRepository;
    private final HttpServletRequest request;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, DocumentReportRepository documentReportRepository, HttpServletRequest request) {
        this.doctorRepository = doctorRepository;
        this.documentReportRepository = documentReportRepository;
        this.request = request;
    }

    public List<DoctorEntity> getAll() {
        return doctorRepository.findAll();
    }

    public Optional<DoctorEntity> getById(Long id) {
        return doctorRepository.findById(id);
    }

    public DoctorEntity save(DoctorEntity doctor) {

        doctor.getPatients().forEach(p -> p.setDoctor(doctor));

        if (this.getById(doctor.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Doctor with id already exists");
        }

        DoctorEntity createdDoctor = doctorRepository.save(doctor);

        DocumentReportEntity documentReport = new DocumentReportEntity();
        documentReport.setDoctor(createdDoctor);
        // note:
        // this is an imperfect solution that could provide false data
        // in the case where there is a race condition for requests associated with the same session
        LocalDateTime executionStartTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(request.getSession().getLastAccessedTime()), ZoneId.systemDefault());
        documentReport.setExecutionStartTime(executionStartTime);
        documentReportRepository.save(documentReport);

        return createdDoctor;
    }
}
