package ma.octo.poc.service.serviceImpl;

import ma.octo.poc.service.DocumentsProcessingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdemniaDocumentsProcessingServiceImpl implements DocumentsProcessingService {

    @Override
    public String process(List<String> documentsUrl, String portraitUrl, int confidenceId) {
        System.out.println("Processing documents");
        return "";
    }



}
