package kg.lovz.server.dto.response;

public record FeedbackResponse(
        int id,
        String name,
        String text,
        String date
) {
}
