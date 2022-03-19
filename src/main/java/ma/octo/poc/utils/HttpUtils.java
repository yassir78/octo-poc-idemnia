package ma.octo.poc.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpUtils {
    @Value("${API_KEY}")
    private final String API_KEY;

    public HttpHeaders getHeaders(MediaType mediaType, boolean isAuthenticated) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        if (isAuthenticated) headers.set("apikey", API_KEY);
        return headers;
    }
}
