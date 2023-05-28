package kg.lovz.server.dto.response;

import java.util.List;

public record ClinicWorkResponse(
        int id,
        String name,
        List<ClinicName> clinics
) {
    public record ClinicName(
            int id,
            String header,
            String photoUrl,
            String photoAltText,
            String text,
            String workDays,
            String webSiteUrl,
            String workTime,
            String address,
            String phone

    ){}
}
