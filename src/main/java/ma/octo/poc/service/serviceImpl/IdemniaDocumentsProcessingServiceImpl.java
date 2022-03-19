package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.service.DocumentsProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class IdemniaDocumentsProcessingServiceImpl implements DocumentsProcessingService {
    private static Logger logger = Logger.getLogger(IdemniaDocumentsProcessingServiceImpl.class.getName());
    private final RestTemplate restTemplate;

    @Value("${API_KEY}")
    private final String API_KEY;

    @Value("${URL_MAIN_PART}")
    private final String URL_MAIN_PART;

    private final String identityId;


    public String process(List<String> documentsUrl, String portraitUrl, int confidenceId) {
        logger.info("Processing documents");
        createIdentity();
        sendConsent();
        return "";
    }

    private void createIdentity() {
        logger.info("Creating identity");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey", API_KEY);
        restTemplate.postForObject(URL_MAIN_PART + "/identity", headers, String.class);
        logger.info("Identity created with id: " + identityId);
    }

    private void sendConsent() {
        logger.info("Sending consent");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Consent sent");
    }

    private void verifyDocument() {
        logger.info("Verifying documents");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Documents verified");
    }

    private void checkDocumentStatus() {
        logger.info("Checking documents status");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Documents status checked");
    }

    private void verifyPortrait() {
        logger.info("Verifying portrait");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Portrait verified");
    }

    private void checkPortraitStatus() {
        logger.info("Checking portrait status");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Portrait status checked");
    }

    private void retriveProof() {
        logger.info("Retrieving proof");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        logger.info("Proof retrieved");
    }


}
