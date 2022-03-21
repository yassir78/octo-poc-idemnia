package ma.octo.poc.web;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.payload.request.DocumentsVerificationRequest;
import ma.octo.poc.response.Result;
import ma.octo.poc.service.DocumentVerificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/document-verification")
@RequiredArgsConstructor
public class DocumentVerificationRest {

    private final DocumentVerificationService documentVerificationService;

    @PostMapping("/verify")
    public Result<Void> verify(DocumentsVerificationRequest documentsVerificationRequest) throws IOException {
        return documentVerificationService.verify(documentsVerificationRequest);
    }
}
