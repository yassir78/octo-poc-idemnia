package ma.octo.poc.service.serviceImpl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import ma.octo.poc.service.UploadService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryUploadServiceImpl implements UploadService {
    private final Cloudinary cloudinary;

    @Override
    public String upload(File file) {
        Map uploadResult = null;
        if (file == null) return "";
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.asMap("resource_type", "auto"));
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
