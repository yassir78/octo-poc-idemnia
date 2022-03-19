package ma.octo.poc.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<K> {
    private List<Message> errors;
    private List<Message> infos;
    private K data;

    public void addErrorMessage(String message) {
        addMessage(message, MessageType.ERROR);
    }

    public void addInfoMessage(String message) {
        addMessage(message, MessageType.INFO);
    }

    public boolean hasErrors() {
        return !getErrors().isEmpty();
    }

    private void addMessage(String messageText, MessageType type) {
        Message message = new Message(messageText);
        if (type == MessageType.ERROR) {
            getErrors().add(message);
        } else if (type == MessageType.INFO) {
            getInfos().add(message);
        }

    }

    public List<Message> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public List<Message> getInfos() {
        if (infos == null) {
            infos = new ArrayList<>();
        }
        return infos;
    }

    public K getData() {
        return data;
    }
}
