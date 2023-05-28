package kg.lovz.server.service.impl;

import kg.lovz.server.dto.request.FeedbackRequest;
import kg.lovz.server.dto.response.FeedbackResponse;
import kg.lovz.server.entity.Clinic;
import kg.lovz.server.entity.Feedback;
import kg.lovz.server.exceptions.ResourceNotFound;
import kg.lovz.server.mapper.FeedbackMapper;
import kg.lovz.server.repo.ClinicRepository;
import kg.lovz.server.repo.FeedbackRepository;
import kg.lovz.server.service.FeedbackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    final FeedbackRepository feedbackRepository;
    final ClinicRepository clinicRepository;
    final FeedbackMapper feedbackMapper;
    @Override
    public FeedbackResponse create(int clinicId, FeedbackRequest request) {
        Clinic clinic = clinicRepository.findById(clinicId).orElseThrow(
                () -> new ResourceNotFound("Clinic not found")
        );
        Feedback feedback = new Feedback();
        feedback.setName(request.name());
        feedback.setText(request.text());
        feedback.setRating(request.rating());
        feedback.setClinic(clinic);
        return feedbackMapper.toFeedbackResponse(feedbackRepository.save(feedback));

    }
}
