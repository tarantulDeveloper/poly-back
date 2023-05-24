package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import kg.lovz.server.repo.HomeContentRepository;
import kg.lovz.server.service.CloudinaryService;
import kg.lovz.server.service.HomeContentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class HomeContentServiceImpl implements HomeContentService {
    final HomeContentRepository homeContentRepository;
    final CloudinaryService cloudinaryService;

    @Override
    public HomeContent createHomeContent(HomeContentRequest request) {
        String photoUrl = cloudinaryService.upload(request.photo());
        HomeContent homeContent = new HomeContent();
        homeContent.setHeader(request.header());
        homeContent.setText(request.text());
        homeContent.setPhotoUrl(photoUrl);
        homeContent.setPhotoAltText(request.photoAltText());
        return homeContentRepository.save(homeContent);
    }
}
