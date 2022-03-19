package ma.octo.poc.service;

import ma.octo.poc.payload.request.DocumentsVerificationRequest;
import ma.octo.poc.response.Result;

public interface DocumentVerificationService {

    Result<Void> verify(DocumentsVerificationRequest documentsVerificationRequest);
}
