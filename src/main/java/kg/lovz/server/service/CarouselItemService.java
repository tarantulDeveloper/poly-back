package kg.lovz.server.service;

import kg.lovz.server.dto.request.CarouselItemRequest;
import kg.lovz.server.entity.CarouselItem;

import java.util.List;

public interface CarouselItemService {
    CarouselItem create(CarouselItemRequest request);

    List<CarouselItem> getAll();

    CarouselItem getById(int id);

    CarouselItem updateById(int id, CarouselItemRequest request);

    void deleteById(int id);
}
