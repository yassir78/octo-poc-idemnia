package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.bean.Document;
import ma.octo.poc.bean.Identity;
import ma.octo.poc.bean.Portrait;
import ma.octo.poc.repository.DocumentRepository;
import ma.octo.poc.repository.IdentityRepository;
import ma.octo.poc.repository.PortraitRepository;
import ma.octo.poc.service.IdentityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IdentityServiceImpl implements IdentityService {
    private final PortraitRepository portraitRepository;
    private final DocumentRepository documentRepository;
    private final IdentityRepository identityRepository;

    @Override
    public void save(List<Document> documents, Portrait portrait) {
        Identity identity = new Identity();
        documents.forEach(document -> {
            document.setIdentity(identity);
            documentRepository.save(document);
        });
        portrait.setIdentity(identity);
        portraitRepository.save(portrait);
        identityRepository.save(identity);
    }
}
