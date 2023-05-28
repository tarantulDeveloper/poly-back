package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.CarouselItemRequest;
import kg.lovz.server.entity.CarouselItem;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.repo.CarouselItemRepository;
import kg.lovz.server.service.CarouselItemService;
import kg.lovz.server.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CarouselItemServiceImpl implements CarouselItemService {
    final CloudinaryService cloudinaryService;
    final CarouselItemRepository carouselItemRepository;
    @Override
    public CarouselItem create(CarouselItemRequest request) {
        CarouselItem carouselItem = new CarouselItem();
        carouselItem.setHeader(request.header());
        carouselItem.setText(request.text());
        carouselItem.setPhotoAltText(request.photoAltText());
        carouselItem.setPhotoUrl(cloudinaryService.upload(request.photo()));
        return carouselItemRepository.save(carouselItem);
    }

    @Override
    public List<CarouselItem> getAll() {
        return carouselItemRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(CarouselItem::getId).reversed())
                .toList();
    }

    @Override
    public CarouselItem getById(int id) {
        return carouselItemRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Carousel not found!")
        );
    }

    @Override
    public CarouselItem updateById(int id, CarouselItemRequest request) {
        CarouselItem updatedItem = carouselItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Carousel not found!"));
        updatedItem.setHeader(request.header());
        updatedItem.setText(request.text());
        updatedItem.setPhotoAltText(request.photoAltText());
        if(request.photo() != null) {
            updatedItem.setPhotoUrl(cloudinaryService.upload(request.photo()));
        }
        return carouselItemRepository.save(updatedItem);

    }

    @Override
    public void deleteById(int id) {
        carouselItemRepository.deleteById(id);
    }
}
