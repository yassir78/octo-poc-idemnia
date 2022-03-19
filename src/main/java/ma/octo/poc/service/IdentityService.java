package ma.octo.poc.service;
import ma.octo.poc.bean.Document;
import ma.octo.poc.bean.Portrait;

import java.util.List;
public interface IdentityService {

    void save(List<Document> documents, Portrait portrait);

}
