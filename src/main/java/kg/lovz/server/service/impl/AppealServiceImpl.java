package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.AppealRequest;
import kg.lovz.server.entity.Appeal;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.repo.AppealRepository;
import kg.lovz.server.service.AppealService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AppealServiceImpl implements AppealService {
    final AppealRepository appealRepository;

    @Override
    public Appeal create(AppealRequest request) {
        Appeal appeal = new Appeal();
        appeal.setName(request.name());
        appeal.setText(request.text());
        appeal.setPhone(request.phone());
        return appealRepository.save(appeal);
    }

    @Override
    public List<Appeal> getAll() {
        return appealRepository.findAll();
    }

    @Override
    public Appeal getById(int id) {
        return appealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Appeal not found!")
        );
    }

    @Override
    public void deleteById(int id) {
        appealRepository.deleteById(id);
    }
}
