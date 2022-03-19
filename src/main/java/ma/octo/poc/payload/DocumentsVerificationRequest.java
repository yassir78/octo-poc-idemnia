package ma.octo.poc.payload;

import lombok.Getter;
import lombok.Setter;
import ma.octo.poc.dto.DocumentDto;
import ma.octo.poc.dto.PortraitDto;

import java.util.List;

@Getter
@Setter
public class DocumentsVerificationRequest {

    private List<DocumentDto> documents;
    private PortraitDto portrait;
    private int confidenceId;
}
