package kg.lovz.server.service;

import kg.lovz.server.dto.request.FeedbackRequest;
import kg.lovz.server.dto.response.FeedbackResponse;
import kg.lovz.server.entity.Feedback;

public interface FeedbackService {
    FeedbackResponse create(int clinicId, FeedbackRequest request);
}
