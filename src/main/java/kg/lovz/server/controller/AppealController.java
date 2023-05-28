package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.AppealRequest;
import kg.lovz.server.entity.Appeal;
import kg.lovz.server.service.AppealService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/appeal")
public class AppealController {
    final AppealService appealService;
    @PostMapping
    @SecurityRequirements
    public Appeal create(@RequestBody AppealRequest request) {
        return appealService.create(request);
    }

    @GetMapping
    public List<Appeal> getAll() {
        return appealService.getAll();
    }

    @GetMapping("/{id}")
    public Appeal getById(@PathVariable("id") int id) {
        return appealService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        appealService.deleteById(id);
    }
}
