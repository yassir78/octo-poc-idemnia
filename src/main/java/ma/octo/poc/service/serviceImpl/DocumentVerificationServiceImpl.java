package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.payload.DocumentsVerificationRequest;
import ma.octo.poc.service.DocumentVerificationService;
import ma.octo.poc.service.UploadService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentVerificationServiceImpl implements DocumentVerificationService {
    private final UploadService uploadService;

    @Override
    public String verify(DocumentsVerificationRequest documentsVerificationRequest) {

        // TODO: implement
        return null;
    }
}
