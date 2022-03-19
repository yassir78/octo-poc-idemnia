package ma.octo.poc.web;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.dto.DocumentsVerificationRequestDto;
import ma.octo.poc.service.DocumentVerificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/document-verification")
@RequiredArgsConstructor
public class DocumentVerificationRest {

    private final DocumentVerificationService documentVerificationService;

    @PostMapping("/verify")
    public String verify(DocumentsVerificationRequestDto documentsVerificationRequestDto) {
        return "document-verification";
    }
}
