package kg.lovz.server.dto.request;

public record FeedbackRequest(
        String name,
        String text,
        int rating
) {
}
