package kg.lovz.server.service;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import org.springframework.data.domain.Page;

public interface HomeContentService {
    HomeContent createHomeContent(HomeContentRequest request);

    Page<HomeContent> getAll(int pageNo, int pageSize, String sortBy);
}
