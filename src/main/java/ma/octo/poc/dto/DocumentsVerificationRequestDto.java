package ma.octo.poc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentsVerificationRequestDto {

    private List<DocumentDto> documents;
    private PortraitDto portrait;
    private int confidenceId;
}
