package pl.codebridge.ormtests.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codebridge.ormtests.model.CustomerDetails;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, UUID> {

    List<CustomerDetails> findByAgeBetween(Integer startAge, Integer endAge);

}
