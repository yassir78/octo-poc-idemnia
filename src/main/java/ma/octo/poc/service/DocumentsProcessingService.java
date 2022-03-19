package ma.octo.poc.service;

import java.util.List;

public interface DocumentsProcessingService {
    String process(List<String> documentsUrl, String portraitUrl, int confidenceId);
}
