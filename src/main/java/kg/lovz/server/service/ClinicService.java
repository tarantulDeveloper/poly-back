package kg.lovz.server.service;

import kg.lovz.server.dto.request.ClinicRequest;
import kg.lovz.server.dto.response.ClinicResponse;
import kg.lovz.server.entity.Clinic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClinicService {
    Clinic create(ClinicRequest request);

    Page<ClinicResponse> getAll(int pageNo, int pageSize, String sortBy);

    ClinicResponse getById(int id);

    Page<ClinicResponse> findByOblast(int pageNo, int pageSize, String sortBy, String oblast);

    ClinicResponse updateById(int id, ClinicRequest request);

    void deleteById(int id);

    void deleteService(int clinicId, int serviceId);

    ClinicResponse addServiceToClinic(int clinicId, int serviceId);

    List<ClinicResponse> getAllList();
}
