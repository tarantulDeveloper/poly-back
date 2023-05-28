package kg.lovz.server.mapper;

import kg.lovz.server.dto.response.ClinicResponse;
import kg.lovz.server.entity.Clinic;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClinicMapper {
    ClinicResponse toClinicResponse (Clinic clinic);
}
