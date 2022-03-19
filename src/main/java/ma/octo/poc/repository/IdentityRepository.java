package ma.octo.poc.repository;

import ma.octo.poc.bean.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends JpaRepository<Identity, Long> {
}
