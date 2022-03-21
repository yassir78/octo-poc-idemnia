package ma.octo.poc.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private LocalDateTime submitDateTime;
    private String frontUrl;
    private String backUrl;
    private String status;
    private String strength;
    private String score;
    @ManyToOne
    @JsonIgnore
    private Identity identity;
}
