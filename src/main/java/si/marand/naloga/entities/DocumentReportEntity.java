package si.marand.naloga.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "document_report")
public class DocumentReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime executionStartTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    DoctorEntity doctor;

    @Size(max = 1000)
    String errorMessage;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DocumentReportEntity that = (DocumentReportEntity) o;

        if (!id.equals(that.id))
            return false;
        if (!executionStartTime.equals(that.executionStartTime))
            return false;
        if (!doctor.equals(that.doctor))
            return false;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + executionStartTime.hashCode();
        result = 31 * result + doctor.hashCode();
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }
}
