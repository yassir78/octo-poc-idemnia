package ma.octo.poc.kafka;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.service.IdentityService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class consumer {

    private final IdentityService identityService;


    @KafkaListener(topics = "${kafka.documents-verification-topic}", groupId = "groupId")
    public void startDocumentsVerification(String message) {
    }
}
