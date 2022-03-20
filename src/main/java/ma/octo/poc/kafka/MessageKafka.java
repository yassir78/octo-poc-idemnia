package ma.octo.poc.kafka;

import java.util.Date;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@RequiredArgsConstructor
public class MessageKafka {
    private String payload;
    private Date date;

}
