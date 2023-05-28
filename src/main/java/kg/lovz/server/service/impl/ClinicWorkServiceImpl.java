package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.ClinicWorkRequest;
import kg.lovz.server.dto.response.ClinicWorkResponse;
import kg.lovz.server.entity.Clinic;
import kg.lovz.server.entity.ClinicWork;
import kg.lovz.server.exceptions.BadRequestException;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.mapper.ClinicWorkMapper;
import kg.lovz.server.repo.ClinicWorkRepository;
import kg.lovz.server.service.ClinicWorkService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ClinicWorkServiceImpl implements ClinicWorkService {
    final ClinicWorkRepository clinicWorkRepository;
    final ClinicWorkMapper clinicWorkMapper;

    @Override
    public ClinicWorkResponse create(ClinicWorkRequest request) {
        if(clinicWorkRepository.existsByName(request.name())) {
            throw new BadRequestException("Service already exist");
        }
        ClinicWork clinicWork = new ClinicWork();
        clinicWork.setName(request.name());
        return clinicWorkMapper.toClinicWorkResponse(clinicWorkRepository.save(clinicWork));
    }

    @Override
    public List<ClinicWorkResponse> getAll() {
        return clinicWorkRepository.findAll().stream()
                .sorted(Comparator.comparingInt(ClinicWork::getId).reversed())
                .map(clinicWorkMapper::toClinicWorkResponse).toList();
    }

    @Override
    public ClinicWorkResponse getById(int id) {
        ClinicWork clinicWork = clinicWorkRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("service not found")
        );
        return clinicWorkMapper.toClinicWorkResponse(clinicWork);
    }

    @Override
    public ClinicWorkResponse updateById(int id, ClinicWorkRequest request) {
        ClinicWork clinicWork = clinicWorkRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("service not found")
        );
        clinicWork.setName(request.name());
        return clinicWorkMapper.toClinicWorkResponse(clinicWorkRepository.save(clinicWork));
    }
}
