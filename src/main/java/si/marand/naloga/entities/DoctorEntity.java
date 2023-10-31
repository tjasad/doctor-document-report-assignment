package si.marand.naloga.entities;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "doctor")
public class DoctorEntity {

    @Id
    private Long id;

    private String department;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<PatientEntity> patients;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DoctorEntity that = (DoctorEntity) o;

        if (!Objects.equals(id, that.id))
            return false;
        if (!Objects.equals(department, that.department))
            return false;
        return Objects.equals(patients, that.patients);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        result = 31 * result + (patients != null ? patients.hashCode() : 0);
        return result;
    }
}
