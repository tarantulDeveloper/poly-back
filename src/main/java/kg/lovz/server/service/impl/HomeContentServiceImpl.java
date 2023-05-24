package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.HomeContentRequest;
import kg.lovz.server.entity.HomeContent;
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
    public Page<HomeContent> getAll(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return homeContentRepository.findAll(pageable);
    }
}
