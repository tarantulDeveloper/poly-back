package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import kg.lovz.server.service.HomeContentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/home-content")
public class HomeContentController {
    final HomeContentService homeContentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HomeContent createHomeContent(@ModelAttribute HomeContentRequest request) {
        return homeContentService.createHomeContent(request);
    }

    @GetMapping
    @SecurityRequirements
    public List<HomeContent> getAllHomeContent() {
        return homeContentService.getAll();
    }

    @GetMapping("/{homeContentId}")
    @SecurityRequirements
    public HomeContent getHomeContentById(@PathVariable("homeContentId") int homeContentId) {
        return homeContentService.getHomeContentById(homeContentId);
    }

    @PutMapping(value = "/{homeContentId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public HomeContent updateHomeContent(@PathVariable("homeContentId") int homeContentId, @ModelAttribute HomeContentRequest request) {
        return homeContentService.updateHomeContent(homeContentId, request);
    }

    @DeleteMapping("/{homeContentId}")
    public void deleteHomeContentById(@PathVariable("homeContentId") int homeContentId) {
        homeContentService.deleteById(homeContentId);
    }

}
