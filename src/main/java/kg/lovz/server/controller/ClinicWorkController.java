package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.ClinicWorkRequest;
import kg.lovz.server.dto.response.ClinicWorkResponse;
import kg.lovz.server.entity.ClinicWork;
import kg.lovz.server.service.ClinicWorkService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/clinic-work")
public class ClinicWorkController {
    final ClinicWorkService clinicWorkService;

    @PostMapping
    public ClinicWorkResponse create(@RequestBody ClinicWorkRequest request) {
        return clinicWorkService.create(request);
    }

    @GetMapping
    @SecurityRequirements
    public List<ClinicWorkResponse> getAll() {
        return clinicWorkService.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public ClinicWorkResponse getClinic(@PathVariable("id") int id) {
        return clinicWorkService.getById(id);
    }

    @PutMapping("/{id}")
    public ClinicWorkResponse updateService(@PathVariable("id") int id, @RequestBody ClinicWorkRequest request) {
        return clinicWorkService.updateById(id, request);
    }
}
