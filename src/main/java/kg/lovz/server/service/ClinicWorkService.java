package kg.lovz.server.service;

import kg.lovz.server.dto.request.ClinicWorkRequest;
import kg.lovz.server.dto.response.ClinicWorkResponse;
import kg.lovz.server.entity.ClinicWork;

import java.util.List;

public interface ClinicWorkService {
    ClinicWorkResponse create(ClinicWorkRequest request);

    List<ClinicWorkResponse> getAll();

    ClinicWorkResponse getById(int id);

    ClinicWorkResponse updateById(int id, ClinicWorkRequest request);
}
