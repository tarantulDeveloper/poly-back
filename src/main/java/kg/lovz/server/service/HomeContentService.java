package kg.lovz.server.service;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HomeContentService {
    HomeContent createHomeContent(HomeContentRequest request);

    List<HomeContent> getAll();

    HomeContent updateHomeContent(int homeContentId, HomeContentRequest request);

    HomeContent getHomeContentById(int homeContentId);

    void deleteById(int homeContentId);
}
