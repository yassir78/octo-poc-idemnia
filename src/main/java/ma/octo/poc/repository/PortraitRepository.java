package ma.octo.poc.repository;

import ma.octo.poc.bean.Portrait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortraitRepository extends JpaRepository<Portrait, Long> {
}
