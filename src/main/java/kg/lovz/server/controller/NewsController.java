package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.NewsRequest;
import kg.lovz.server.entity.News;
import kg.lovz.server.service.NewsService;
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
@RequestMapping("/api/news")
public class NewsController {
    final NewsService newsService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public News create(@ModelAttribute NewsRequest request) {
        return newsService.create(request);
    }

    @GetMapping
    @SecurityRequirements
    public Page<News> getAllPage(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "3") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return newsService.getAllPage(pageNo, pageSize, sortBy);
    }

    @GetMapping("/all")
    @SecurityRequirements
    public List<News> getAll() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    @SecurityRequirements
    public News getNewsById(@PathVariable("id") int id) {
        return newsService.getById(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public News updateById(@PathVariable("id") int id, @ModelAttribute NewsRequest request) {
        return newsService.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id){
        newsService.deleteById(id);
    }
}
