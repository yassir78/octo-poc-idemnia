package ma.octo.poc.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    String levelOfAssurance;
    String status;
    @OneToMany(mappedBy = "identity")
    List<Document> documents;
    @OneToOne
    Portrait portrait;


}
