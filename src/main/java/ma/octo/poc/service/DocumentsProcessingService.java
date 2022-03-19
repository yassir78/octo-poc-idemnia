package ma.octo.poc.service;

import java.util.List;

public interface DocumentsProcessingService {
    void process(List<String> documentsUrl, String portraitUrl, int confidenceId);
}
