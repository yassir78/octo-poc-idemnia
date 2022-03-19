package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import ma.octo.poc.payload.response.CheckStatusResponse;
import ma.octo.poc.payload.response.ConsentSendingResponse;
import ma.octo.poc.service.DocumentsProcessingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
        restTemplate.postForObject(URL_MAIN_PART + "/identities", headers, String.class);
        logger.info("Identity created with id: " + identityId);
    }

    private void sendConsent() {
        logger.info("Sending consent");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ConsentSendingResponse[] c = restTemplate.postForObject(URL_MAIN_PART + "/identities/" + identityId + "/consents", headers, ConsentSendingResponse[].class);
        logger.info("Consent sent with id: " + c[0].getConsentId());
    }

    private void verifyDocument(String documentUrl) {
        logger.info("Verifying documents");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey", API_KEY);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("DocumentFront", documentUrl);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + "/identities/" + identityId + "/id-documents/capture", requestEntity, CheckStatusResponse.class);
        logger.info("Documents verified");
    }

    private String checkDocumentStatus(String documentId) {
        logger.info("Checking documents status");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", API_KEY);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + "/identities/" + identityId + "/status/" + documentId, headers, CheckStatusResponse.class);
        logger.info("Documents status checked");
        return response.getStatus();
    }

    private void verifyPortrait(String portraitUrl) {
        logger.info("Verifying Portrait");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("apikey", API_KEY);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("DocumentFront", portraitUrl);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + "/identities/" + identityId + "/attributes/portrait/capture", requestEntity, CheckStatusResponse.class);
        logger.info("Documents verified");
    }

    private String checkPortraitStatus(String portraitId) {
        logger.info("Checking Portrait status");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", API_KEY);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + "/identities/" + identityId + "/status/" + portraitId, headers, CheckStatusResponse.class);
        logger.info("Documents status checked");
        return response.getStatus();
    }

    private void retriveProof() {
        logger.info("Retrieving proof");
        logger.info("Proof retrieved");
    }


}
