package kg.lovz.server.service;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;

public interface HomeContentService {
    HomeContent createHomeContent(HomeContentRequest request);
}
