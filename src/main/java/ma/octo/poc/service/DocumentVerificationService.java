package ma.octo.poc.service;

import ma.octo.poc.payload.DocumentsVerificationRequest;

public interface DocumentVerificationService {

    String verify(DocumentsVerificationRequest documentsVerificationRequest);
}
