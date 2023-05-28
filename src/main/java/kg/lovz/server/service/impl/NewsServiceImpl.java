package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.NewsRequest;
import kg.lovz.server.entity.News;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.repo.NewsRepository;
import kg.lovz.server.service.CloudinaryService;
import kg.lovz.server.service.NewsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    final NewsRepository newsRepository;
    final CloudinaryService cloudinaryService;

    @Override
    public News create(NewsRequest request) {
        News news = new News();
        news.setHeader(request.header());
        news.setText(request.text());
        news.setPhotoAltText(request.photoAltText());
        news.setMainText(request.mainText());
        news.setPhotoUrl(cloudinaryService.upload(request.photo()));
        return newsRepository.save(news);
    }

    @Override
    public Page<News> getAllPage(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        return newsRepository.findAll(pageable);
    }

    @Override
    public List<News> getAll() {
        return newsRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(News::getId).reversed())
                .toList();
    }

    @Override
    public News getById(int id) {
        return newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("News not found")
        );
    }

    @Override
    public News updateById(int id, NewsRequest request) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("News not found")
        );
        if(request.photo() != null) {
            news.setPhotoUrl(cloudinaryService.upload(request.photo()));
        }
        news.setHeader(request.header());
        news.setText(request.text());
        news.setPhotoAltText(request.photoAltText());
        news.setMainText(request.mainText());
        return newsRepository.save(news);
    }

    @Override
    public void deleteById(int id) {
        newsRepository.deleteById(id);
    }
}
