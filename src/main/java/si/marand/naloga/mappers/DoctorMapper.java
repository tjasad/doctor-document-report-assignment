package si.marand.naloga.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import si.marand.naloga.dto.DoctorDto;
import si.marand.naloga.entities.DoctorEntity;

@Mapper(uses = {PatientMapper.class}, componentModel = "spring")
public interface DoctorMapper {

    DoctorDto toDto(DoctorEntity entity);
    List<DoctorDto> toDto(List<DoctorEntity> entities);
    DoctorEntity toEntity(DoctorDto dto);
    List<DoctorEntity> toEntity(List<DoctorDto> dtos);

}
