package ma.octo.poc.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Portrait extends AbstractId {

    private String status;
    private String url;
    @OneToOne(mappedBy = "portrait")
    private Identity identity;
}
