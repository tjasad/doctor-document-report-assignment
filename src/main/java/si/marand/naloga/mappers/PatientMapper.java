package si.marand.naloga.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import si.marand.naloga.dto.PatientDto;
import si.marand.naloga.entities.PatientEntity;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDto toDto(PatientEntity entity);
    List<PatientDto> toDto(List<PatientEntity> entities);
    PatientEntity toEntity(PatientDto dto);
    List<PatientEntity> toEntity(List<PatientDto> dtos);
}
