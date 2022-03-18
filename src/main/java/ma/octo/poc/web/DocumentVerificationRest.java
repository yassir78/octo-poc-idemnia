package ma.octo.poc.web;

import ma.octo.poc.dto.DocumentsVerificationRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/document-verification")
public class DocumentVerificationRest {

    @PostMapping("/verify")
    public String verify(DocumentsVerificationRequestDto documentsVerificationRequestDto) {
        return "document-verification";
    }
}
