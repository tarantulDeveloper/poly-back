package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.WhoAreLovzKgRequest;
import kg.lovz.server.entity.WhoAreLovzKg;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.repo.WhoAreLovzKgRepository;
import kg.lovz.server.service.CloudinaryService;
import kg.lovz.server.service.WhoAreLovzKgService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Slf4j
public class WhoAreLovzKgServiceImpl implements WhoAreLovzKgService {
    final WhoAreLovzKgRepository whoAreLovzKgRepository;
    final CloudinaryService cloudinaryService;

    @Override
    public WhoAreLovzKg create(WhoAreLovzKgRequest request) {
        WhoAreLovzKg whoAreLovzKg = new WhoAreLovzKg();
        whoAreLovzKg.setHeader(request.header());
        whoAreLovzKg.setText(request.text());
        whoAreLovzKg.setPhotoUrl(cloudinaryService.upload(request.photo()));
        whoAreLovzKg.setPhotoAltText(request.photoAltText());
        return whoAreLovzKgRepository.save(whoAreLovzKg);
    }

    @Override
    public List<WhoAreLovzKg> getEntity() {
        return whoAreLovzKgRepository.findAll();
    }

    @Override
    public WhoAreLovzKg updateEntity(int id, WhoAreLovzKgRequest request) {
        WhoAreLovzKg updatedWhoAre = whoAreLovzKgRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Entity not found!"));
        if(request.photo() != null) {
            updatedWhoAre.setPhotoUrl(cloudinaryService.upload(request.photo()));
        }
        updatedWhoAre.setHeader(request.header());
        updatedWhoAre.setText(request.text());
        updatedWhoAre.setPhotoAltText(request.photoAltText());
        return whoAreLovzKgRepository.save(updatedWhoAre);
    }

    @Override
    public WhoAreLovzKg getEntityById(int id) {
        return whoAreLovzKgRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Entity not found!")
        );
    }

    @Override
    public void deleteById(int id) {
        whoAreLovzKgRepository.deleteById(id);
    }
}
