package ma.octo.poc.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckStatusResponse {
    private String status;
    private String type;
    private String id;
}
