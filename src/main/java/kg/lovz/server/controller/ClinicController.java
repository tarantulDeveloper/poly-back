package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.ClinicRequest;
import kg.lovz.server.dto.response.ClinicResponse;
import kg.lovz.server.entity.Clinic;
import kg.lovz.server.service.ClinicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/clinic")
public class ClinicController {
    final ClinicService clinicService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Clinic create(@ModelAttribute ClinicRequest request) {
        return clinicService.create(request);
    }

    @GetMapping
    @SecurityRequirements
    public Page<ClinicResponse> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return clinicService.getAll(pageNo, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public ClinicResponse getById(@PathVariable("id") int id) {
        return clinicService.getById(id);
    }

    @GetMapping("/oblast")
    @SecurityRequirements
    public Page<ClinicResponse> getAllByOblast(@RequestParam(defaultValue = "0") int pageNo,
                                       @RequestParam(defaultValue = "3") int pageSize,
                                       @RequestParam(defaultValue = "id") String sortBy,
                                       @RequestParam(defaultValue = "Бишкек") String oblast) {
        return clinicService.findByOblast(pageNo, pageSize, sortBy, oblast);
    }

    @GetMapping("/all")
    public List<ClinicResponse> getAll(){
        return clinicService.getAllList();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ClinicResponse updateClinicById(@PathVariable("id") int id, @ModelAttribute ClinicRequest request) {
        return clinicService.updateById(id, request);
    }

    @PutMapping("/{clinicId}/service/{serviceId}")
    public ClinicResponse addServiceToClinic(@PathVariable("clinicId") int clinicId, @PathVariable("serviceId") int serviceId) {
        return clinicService.addServiceToClinic(clinicId, serviceId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        clinicService.deleteById(id);
    }

    @DeleteMapping("/{clinicId}/service/{serviceId}")
    public void deleteServiceOfClinic(@PathVariable("clinicId") int clinicId, @PathVariable("serviceId") int serviceId) {
        clinicService.deleteService(clinicId, serviceId);
    }

}
