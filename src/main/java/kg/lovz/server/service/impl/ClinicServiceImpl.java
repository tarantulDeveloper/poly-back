package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.ClinicRequest;
import kg.lovz.server.dto.response.ClinicResponse;
import kg.lovz.server.entity.Clinic;
import kg.lovz.server.entity.ClinicWork;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.mapper.ClinicMapper;
import kg.lovz.server.repo.ClinicRepository;
import kg.lovz.server.repo.ClinicWorkRepository;
import kg.lovz.server.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class ClinicServiceImpl implements kg.lovz.server.service.ClinicService {
    final CloudinaryService cloudinaryService;
    final ClinicRepository clinicRepository;
    final ClinicWorkRepository clinicWorkRepository;
    final ClinicMapper clinicMapper;

    @Override
    public Clinic create(ClinicRequest request) {
        Clinic clinic = new Clinic();
        clinic.setHeader(request.header());
        clinic.setText(request.text());
        clinic.setAddress(request.address());
        clinic.setPhotoAltText(request.photoAltText());
        clinic.setPhotoUrl(cloudinaryService.upload(request.photo()));
        clinic.setPhone(request.phone());
        clinic.setWorkTime(request.workTime());
        clinic.setWorkDays(request.workDays());
        clinic.setOblast(request.oblast());
        clinic.setWebSiteUrl(request.webSiteUrl());
        return clinicRepository.save(clinic);
    }

    @Override
    public Page<ClinicResponse> getAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return clinicRepository.findAll(pageable).map(clinicMapper::toClinicResponse);
    }

    @Override
    public ClinicResponse getById(int id) {
        Clinic clinic = clinicRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Clinic not found!")
        );
        return clinicMapper.toClinicResponse(clinic);
    }

    @Override
            public Page<ClinicResponse> findByOblast(int pageNo, int pageSize, String sortBy, String oblast) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return clinicRepository.findAllByOblast(oblast, pageable).map(clinicMapper::toClinicResponse);
    }

    @Override
    public ClinicResponse updateById(int id, ClinicRequest request) {
        log.info(String.valueOf(request));
        Clinic updateClinic = clinicRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Clinic not found!")
        );
        if (request.photo() != null) {
            updateClinic.setPhotoUrl(cloudinaryService.upload(request.photo()));
        }
        updateClinic.setHeader(request.header());
        updateClinic.setText(request.text());
        updateClinic.setPhotoAltText(request.photoAltText());
        updateClinic.setPhone(request.phone());
        updateClinic.setAddress(request.address());
        updateClinic.setWorkTime(request.workTime());
        updateClinic.setWorkDays(request.workDays());
        updateClinic.setOblast(request.oblast());
        updateClinic.setWebSiteUrl(request.webSiteUrl());

        List<ClinicWork> clinicWorks = new ArrayList<>();

        for(int i : request.worksIds()) {
            clinicWorks.add(clinicWorkRepository.findById(i).orElseThrow(() -> new ResourceNotFound("work not found")));
        }

        updateClinic.setClinicWorks(clinicWorks);

        return clinicMapper.toClinicResponse(clinicRepository.save(updateClinic));
    }

    @Override
    public void deleteById(int id) {
        clinicRepository.deleteById(id);
    }

    @Override
    public void deleteService(int clinicId, int serviceId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceNotFound("Clinic not found"));
        ClinicWork clinicWork = clinicWorkRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFound("Service not found"));

        clinic.getClinicWorks().remove(clinicWork);
        clinicRepository.save(clinic);
    }

    @Override
    public ClinicResponse addServiceToClinic(int clinicId, int serviceId) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceNotFound("Clinic not found"));
        ClinicWork clinicWork = clinicWorkRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFound("Service not found"));
        clinic.getClinicWorks().add(clinicWork);
        return clinicMapper.toClinicResponse(clinicRepository.save(clinic));
    }

    @Override
    public List<ClinicResponse> getAllList() {
        return clinicRepository.findAll().stream()
                .sorted(Comparator.comparing(Clinic::getId).reversed())
                .map(clinicMapper::toClinicResponse).toList();
    }
}
