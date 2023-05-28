package kg.lovz.server.dto.response;

import kg.lovz.server.entity.Feedback;

import java.util.List;

public record ClinicResponse(
        int id,
        String header,
        String text,
        String photoUrl,
        String photoAltText,
        String phone,
        String workTime,
        String workDays,
        String webSiteUrl,
        String oblast,
        String address,
        List<FeedbackDTO> feedbackList,
        List<ClinicWorkDTO> clinicWorks
        ) {
    public record ClinicWorkDTO(
            int id,
            String name
    ){}

    public record FeedbackDTO(
            int id,
            String name,
            int rating,
            String date,
            String text
    ) {}
}
