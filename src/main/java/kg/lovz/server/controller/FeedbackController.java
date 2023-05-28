package kg.lovz.server.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import kg.lovz.server.dto.request.FeedbackRequest;
import kg.lovz.server.dto.response.FeedbackResponse;
import kg.lovz.server.entity.Feedback;
import kg.lovz.server.service.FeedbackService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/feedback")
public class FeedbackController {
    final FeedbackService feedbackService;
    @PostMapping("/{clinicId}")
    @SecurityRequirements
    public FeedbackResponse create(@PathVariable("clinicId") int clinicId, @RequestBody FeedbackRequest request) {
        return feedbackService.create(clinicId,request);
    }
}
