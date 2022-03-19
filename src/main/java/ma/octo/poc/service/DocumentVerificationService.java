package ma.octo.poc.service;

import ma.octo.poc.dto.DocumentsVerificationRequestDto;

public interface DocumentVerificationService {

    String verify(DocumentsVerificationRequestDto documentsVerificationRequestDto);
}
