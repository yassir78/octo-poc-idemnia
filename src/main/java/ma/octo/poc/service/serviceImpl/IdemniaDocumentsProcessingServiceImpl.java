package ma.octo.poc.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.octo.poc.payload.response.CheckStatusResponse;
import ma.octo.poc.payload.response.ConsentSendingResponse;
import ma.octo.poc.payload.response.CreateIdentityResponse;
import ma.octo.poc.service.DocumentsProcessingService;
import ma.octo.poc.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class IdemniaDocumentsProcessingServiceImpl implements DocumentsProcessingService {
    private final RestTemplate restTemplate;
    private final HttpUtils httpUtils;
    private final Map<String, String> processedDocuments = new HashMap<>();
    private final Map<String, String> processedProtrait = new HashMap<>();

    @Value("${URL_MAIN_PART}")
    private final String URL_MAIN_PART;

    private String identityId;


    public void process(List<String> documentsUrl, String portraitUrl, int confidenceId) {
        log.info("Processing documents");
        createIdentity();
        sendConsent();
        // documents
        documentsUrl.stream()
                .parallel()
                .map(this::verifyDocument)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList())
                .forEach(checkStatusResponse -> processedDocuments.put(checkStatusResponse.getId(), checkStatusResponse.getStatus()));
        // portrait
        Optional<CheckStatusResponse> checkStatusResponse = verifyPortrait(portraitUrl);
        checkStatusResponse.ifPresent(statusResponse -> processedProtrait.put(statusResponse.getId(), statusResponse.getStatus()));
    }

    @Scheduled(fixedDelay = 600000)
    public void scheduleCheckingStatus() {
        if (identityId.isEmpty()) {
            return;
        }
        processedDocuments.forEach((key, value) -> {
            if (value.equals("PROCESSING")) {
                Optional<String> checkStatusResponse = checkDocumentStatus(value);
                checkStatusResponse.ifPresent(status -> processedDocuments.put(key, status));
            }
        });
        processedProtrait.forEach((key, value) -> {
            if (value.equals("PROCESSING")) {
                Optional<String> checkStatusResponse = checkPortraitStatus(value);
                checkStatusResponse.ifPresent(status -> processedProtrait.put(key, status));
            }
        });
        // TODO: check if all documents are processed And if so, retrieve proof And send event to the client


    }


    private void createIdentity() {
        log.info("Creating identity");
        CreateIdentityResponse result = restTemplate.postForObject(URL_MAIN_PART,
                httpUtils.getHeaders(MediaType.MULTIPART_FORM_DATA, true),
                CreateIdentityResponse.class);
        identityId = result != null ? result.getId() : "";
    }

    private void sendConsent() {
        log.info("Sending consent");
        ConsentSendingResponse[] c = restTemplate.postForObject(URL_MAIN_PART + identityId + "/consents",
                httpUtils.getHeaders(MediaType.APPLICATION_JSON, true),
                ConsentSendingResponse[].class);
        log.info("Consent sent" + c[0].getConsentId());
    }

    private Optional<CheckStatusResponse> verifyDocument(String documentUrl) {
        log.info("Verifying documents");
        HttpHeaders headers = httpUtils.getHeaders(MediaType.MULTIPART_FORM_DATA, true);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("DocumentFront", documentUrl);
        body.add("DocumentBack", documentUrl);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + identityId + "/id-documents/capture", requestEntity, CheckStatusResponse.class);
        return Optional.ofNullable(response);
    }

    private Optional<String> checkDocumentStatus(String documentId) {
        log.info("Checking documents status");
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + identityId + "/status/" + documentId,
                httpUtils.getHeaders(MediaType.MULTIPART_FORM_DATA, true),
                CheckStatusResponse.class);
        if (!Objects.isNull(response))
            return Optional.ofNullable(response.getStatus());
        return Optional.empty();
    }

    private Optional<CheckStatusResponse> verifyPortrait(String portraitUrl) {
        log.info("Verifying Portrait");
        HttpHeaders headers = httpUtils.getHeaders(MediaType.MULTIPART_FORM_DATA, true);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("DocumentFront", portraitUrl);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + identityId + "/attributes/portrait/capture", requestEntity, CheckStatusResponse.class);
        return Optional.ofNullable(response);
    }

    private Optional<String> checkPortraitStatus(String portraitId) {
        log.info("Checking Portrait status");
        HttpHeaders headers = httpUtils.getHeaders(MediaType.APPLICATION_JSON, true);
        CheckStatusResponse response = restTemplate.postForObject(URL_MAIN_PART + identityId + "/status/" + portraitId, headers, CheckStatusResponse.class);
        if (!Objects.isNull(response))
            return Optional.ofNullable(response.getStatus());
        return Optional.empty();
    }

    private void retriveProof() {
        log.info("Retrieving proof");
        log.info("Proof retrieved");
    }


}
