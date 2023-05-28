package kg.lovz.server.dto.request;

import kg.lovz.server.dto.response.ClinicResponse;
import kg.lovz.server.dto.response.ClinicWorkResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ClinicRequest(
        String header,
        String text,
        String oblast,
        String address,
        String photoAltText,
        String webSiteUrl,
        @Nullable MultipartFile photo,
        String phone,
        String workTime,
        String workDays,
        List<Integer> worksIds
) {

}
