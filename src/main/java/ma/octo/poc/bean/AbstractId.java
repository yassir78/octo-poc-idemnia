package ma.octo.poc.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public class AbstractId {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
