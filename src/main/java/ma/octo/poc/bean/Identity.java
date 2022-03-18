package ma.octo.poc.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Identity extends AbstractId {

    String levelOfAssurance;
    String status;
    @OneToMany(mappedBy = "identity")
    List<Document> documents;
    @OneToOne
    Portrait portrait;


}
