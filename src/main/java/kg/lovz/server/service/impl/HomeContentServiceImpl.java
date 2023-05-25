package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.repo.HomeContentRepository;
import kg.lovz.server.service.CloudinaryService;
import kg.lovz.server.service.HomeContentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<HomeContent> getAll() {
        return homeContentRepository.findAllByOrderByIdDesc();
    }

    @Override
    public HomeContent updateHomeContent(int homeContentId, HomeContentRequest request) {
        HomeContent updatedHomeContent = homeContentRepository.findById(homeContentId)
                .orElseThrow(() -> new ResourceNotFound("Home Content not found!"));
        if(request.photo() != null) {
            String photoUrl = cloudinaryService.upload(request.photo());
            updatedHomeContent.setPhotoUrl(photoUrl);
        }
        updatedHomeContent.setHeader(request.header());
        updatedHomeContent.setText(request.text());
        updatedHomeContent.setPhotoAltText(request.photoAltText());
        return homeContentRepository.save(updatedHomeContent);
    }

    @Override
    public HomeContent getHomeContentById(int homeContentId) {
        return homeContentRepository.findById(homeContentId).orElseThrow(
                () -> new ResourceNotFound("Home content not found!")
        );
    }

    @Override
    public void deleteById(int homeContentId) {
        homeContentRepository.deleteById(homeContentId);
    }
}
