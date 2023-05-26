package kg.lovz.server.service;

import kg.lovz.server.dto.request.WhoAreLovzKgRequest;
import kg.lovz.server.entity.WhoAreLovzKg;

import java.util.List;

public interface WhoAreLovzKgService {
    WhoAreLovzKg create(WhoAreLovzKgRequest request);

    List<WhoAreLovzKg> getEntity();

    WhoAreLovzKg updateEntity(int id, WhoAreLovzKgRequest request);

    WhoAreLovzKg getEntityById(int id);

    void deleteById(int id);
}
