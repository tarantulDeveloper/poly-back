package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.CarouselItemRequest;
import kg.lovz.server.entity.CarouselItem;
import kg.lovz.server.service.CarouselItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/carousel")
public class CarouselItemController {
    final CarouselItemService carouselItemService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CarouselItem create(@ModelAttribute CarouselItemRequest request) {
        return carouselItemService.create(request);
    }

    @GetMapping
    @SecurityRequirements
    public List<CarouselItem> getAll() {
        return carouselItemService.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public CarouselItem getById(@PathVariable("id") int id) {
        return carouselItemService.getById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CarouselItem updateById(@PathVariable("id") int id, @ModelAttribute CarouselItemRequest request) {
        return carouselItemService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        carouselItemService.deleteById(id);
    }
}
