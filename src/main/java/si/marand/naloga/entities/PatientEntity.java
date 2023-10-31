package si.marand.naloga.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "patient")
public class PatientEntity {

    @Id
    private Long id;

    private String firstName;

    private String lastName;

    @ElementCollection
    private List<String> diseases = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    private DoctorEntity doctor;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PatientEntity that = (PatientEntity) o;

        if (!id.equals(that.id))
            return false;
        if (!firstName.equals(that.firstName))
            return false;
        if (!lastName.equals(that.lastName))
            return false;
        return Objects.equals(diseases, that.diseases);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (diseases != null ? diseases.hashCode() : 0);
        return result;
    }
}
