package kg.lovz.server.service;

import kg.lovz.server.dto.request.NewsRequest;
import kg.lovz.server.entity.News;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NewsService {
    News create(NewsRequest request);

    Page<News> getAllPage(int pageNo, int pageSize, String sortBy);

    List<News> getAll();

    News getById(int id);

    News updateById(int id, NewsRequest request);

    void deleteById(int id);
}
