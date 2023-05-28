package kg.lovz.server.service;

import kg.lovz.server.dto.request.AppealRequest;
import kg.lovz.server.entity.Appeal;

import java.util.List;

public interface AppealService {
    Appeal create(AppealRequest request);

    List<Appeal> getAll();

    Appeal getById(int id);

    void deleteById(int id);
}
