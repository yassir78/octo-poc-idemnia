package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.dto.DocumentsVerificationRequestDto;
import ma.octo.poc.service.DocumentVerificationService;
import ma.octo.poc.service.UploadService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentVerificationServiceImpl implements DocumentVerificationService {
    private final UploadService uploadService;

    @Override
    public String verify(DocumentsVerificationRequestDto documentsVerificationRequestDto) {
        // upload documents

        // save the documents in the database

        // publish the event to the queue to start the verification process
        return null;
    }
}
