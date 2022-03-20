package ma.octo.poc.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class SendMessage {
    private final Gson jsonConverter;

    public String buildMessage(Object payload) {
        MessageKafka messageKafka = new MessageKafka();
        messageKafka.setDate(new Date());
        messageKafka.setPayload(jsonConverter.toJson(payload));
        return jsonConverter.toJson(messageKafka);
    }
}
