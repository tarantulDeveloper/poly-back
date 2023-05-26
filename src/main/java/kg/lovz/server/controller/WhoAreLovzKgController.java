package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.WhoAreLovzKgRequest;
import kg.lovz.server.entity.WhoAreLovzKg;
import kg.lovz.server.service.WhoAreLovzKgService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/who-are-lovz-kg")
public class WhoAreLovzKgController {
    final WhoAreLovzKgService whoAreLovzKgService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhoAreLovzKg create(@ModelAttribute WhoAreLovzKgRequest request) {
        return whoAreLovzKgService.create(request);
    }

    @GetMapping
    @SecurityRequirements
    public List<WhoAreLovzKg> getEntity(){
        return whoAreLovzKgService.getEntity();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public WhoAreLovzKg getEntityById(@PathVariable("id") int id) {
        return whoAreLovzKgService.getEntityById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public WhoAreLovzKg updateEntity(@PathVariable("id") int id, @ModelAttribute WhoAreLovzKgRequest request) {
        return whoAreLovzKgService.updateEntity(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        whoAreLovzKgService.deleteById(id);
    }

}
