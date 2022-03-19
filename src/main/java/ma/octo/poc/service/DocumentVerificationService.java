package ma.octo.poc.service;

import ma.octo.poc.payload.request.DocumentsVerificationRequest;

public interface DocumentVerificationService {

    String verify(DocumentsVerificationRequest documentsVerificationRequest);
}
