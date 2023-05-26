package kg.lovz.server.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import kg.lovz.server.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    final Cloudinary cloudinary;

    @Override
    public String upload(MultipartFile multipartFile) {
        try {


            return String.valueOf(
                    cloudinary.uploader()
                            .upload(multipartFile.getBytes(), ObjectUtils.emptyMap())
                            .get("secure_url")
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
