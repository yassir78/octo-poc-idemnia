package ma.octo.poc.payload.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CheckStatusResponse {
    private String status;
    private String type;
    private String id;
}
