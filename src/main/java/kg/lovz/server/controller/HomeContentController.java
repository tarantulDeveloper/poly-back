package kg.lovz.server.controller;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import kg.lovz.server.service.HomeContentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/home-content")
public class HomeContentController {
    final HomeContentService homeContentService;

    @PostMapping
    public HomeContent createHomeContent(@ModelAttribute HomeContentRequest request) {
        return homeContentService.createHomeContent(request);
    }

    @GetMapping
    public Page<HomeContent> getAllHomeContent(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return homeContentService.getAll(pageNo, pageSize, sortBy);
    }

}
