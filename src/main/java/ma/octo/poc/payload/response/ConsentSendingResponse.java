package ma.octo.poc.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsentSendingResponse {
    private String consentId;
    private boolean approved;
    private String type;
}
