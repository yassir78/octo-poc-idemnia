package ma.octo.poc.payload.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CreateIdentityResponse {
    private String id;
    private String status;
    private String levelOfAssurance;
}
