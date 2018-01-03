package pl.codebridge.ormtests.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.codebridge.ormtests.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("SELECT c FROM Customer c WHERE c.details.id = ?1")
    Customer findByCustomerDetailsId(UUID customerDetailsId);

}
