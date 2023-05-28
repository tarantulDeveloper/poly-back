package kg.lovz.server.mapper;

import kg.lovz.server.dto.response.FeedbackResponse;
import kg.lovz.server.entity.Feedback;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {
    FeedbackResponse toFeedbackResponse(Feedback feedback);
}
