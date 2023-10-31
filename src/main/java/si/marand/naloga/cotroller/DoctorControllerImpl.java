package si.marand.naloga.cotroller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import si.marand.naloga.dto.DoctorDto;
import si.marand.naloga.entities.DoctorEntity;
import si.marand.naloga.mappers.DoctorMapper;
import si.marand.naloga.services.DoctorService;

@RestController
class DoctorControllerImpl implements DoctorController {

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;

    @Autowired
    DoctorControllerImpl(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @Override
    public List<DoctorDto> getAll() {
        return doctorMapper.toDto(doctorService.getAll());
    }

    @Override
    public ResponseEntity<DoctorDto> get(Long id) {

        Optional<DoctorEntity> doctor = doctorService.getById(id);
        DoctorDto fetchedEntity = doctorMapper.toDto(doctor.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(fetchedEntity);
    }

    @Override
    public ResponseEntity<DoctorDto> createDoctor(@Valid DoctorDto doctor) {

        DoctorDto createdEntity = doctorMapper.toDto(doctorService.save(doctorMapper.toEntity(doctor)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdEntity);
    }
}
