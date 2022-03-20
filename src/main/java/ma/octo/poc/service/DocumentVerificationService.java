package ma.octo.poc.service;

import ma.octo.poc.payload.request.DocumentsVerificationRequest;
import ma.octo.poc.response.Result;

import java.io.IOException;

public interface DocumentVerificationService {

    Result<Void> verify(DocumentsVerificationRequest documentsVerificationRequest) throws IOException;
}
