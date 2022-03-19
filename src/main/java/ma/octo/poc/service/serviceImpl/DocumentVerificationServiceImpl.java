package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.bean.Document;
import ma.octo.poc.bean.Portrait;
import ma.octo.poc.dto.DocumentDto;
import ma.octo.poc.dto.PortraitDto;
import ma.octo.poc.payload.request.DocumentsVerificationRequest;
import ma.octo.poc.response.Result;
import ma.octo.poc.service.DocumentVerificationService;
import ma.octo.poc.service.IdentityService;
import ma.octo.poc.service.UploadService;
import ma.octo.poc.utils.FileConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentVerificationServiceImpl implements DocumentVerificationService {
    private final UploadService uploadService;
    private final IdentityService identityService;

    @Override
    public Result<Void> verify(DocumentsVerificationRequest documentsVerificationRequest) {
        Result<Void> result = new Result();
        validate(result, documentsVerificationRequest);
        if (!result.hasErrors()) {
            run(result, documentsVerificationRequest);
        }
        return result;
    }

    private void validate(Result<Void> result, DocumentsVerificationRequest documentsVerificationRequest) {
        if (documentsVerificationRequest.getDocuments().size() == 0) {
            result.addErrorMessage("The number of documents is not correct");
        }
        if (documentsVerificationRequest.getPortrait().getEncodedPortraitImage() == null) {
            result.addErrorMessage("The portrait is not correct");
        }
        if (documentsVerificationRequest.getDocuments().stream().anyMatch(document -> document.getEncodedDocumentFrontImage() == null || document.getEncodedDocumentBackImage() == null)) {
            result.addErrorMessage("The document front image is not correct");
        }
    }

    private void run(Result<Void> result, DocumentsVerificationRequest documentsVerificationRequest) {
        documentsVerificationRequest.getDocuments().stream().map(this::saveDocuments).collect(Collectors.toList());
        savePortrait(documentsVerificationRequest.getPortrait());


        // publish the event to the queue to start the verification process
    }

    private Portrait savePortrait(PortraitDto portrait) {
        String portraitFileName = uploadService.upload(FileConverter.decode(portrait.getEncodedPortraitImage()));
        Portrait portraitEntity = new Portrait();
        portraitEntity.setUrl(portraitFileName);
        return portraitEntity;

    }

    private Document saveDocuments(DocumentDto documentDto) {
        try {
            String frontFileName = uploadService.upload(FileConverter.decode(documentDto.getEncodedDocumentFrontImage()));
            String backFileName = uploadService.upload(FileConverter.decode(documentDto.getEncodedDocumentBackImage()));
            Document documentEntity = new Document();
            documentEntity.setBackUrl(backFileName);
            documentEntity.setFrontUrl(frontFileName);
            return documentEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
