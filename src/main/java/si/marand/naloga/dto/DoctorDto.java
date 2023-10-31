package si.marand.naloga.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    @NotNull
    private Long id;
    @NotNull
    private String department;
    private List<@Valid PatientDto> patients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<PatientDto> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientDto> patients) {
        this.patients = patients;
    }
}
