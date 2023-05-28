package kg.lovz.server.mapper;

import kg.lovz.server.dto.response.ClinicWorkResponse;
import kg.lovz.server.entity.ClinicWork;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClinicWorkMapper {
    ClinicWorkResponse toClinicWorkResponse(ClinicWork clinicWork);
}
