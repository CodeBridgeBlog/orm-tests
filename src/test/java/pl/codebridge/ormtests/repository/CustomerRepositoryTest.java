package pl.codebridge.ormtests.repository;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pl.codebridge.ormtests.model.Customer;
import pl.codebridge.ormtests.model.CustomerDetails;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ContextConfiguration(initializers = CustomerRepositoryTest.class)
public class CustomerRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    public void should_find_customer_by_customer_details_id() {
        final List<CustomerDetails> customersDetails = Stream.of(
                CustomerDetails.builder()
                        .firstName("John")
                        .lastName("Smith")
                        .age(18)
                        .build(),
                CustomerDetails.builder()
                        .firstName("David")
                        .lastName("Jones")
                        .age(20)
                        .build())
                .map(entityManager::persist)
                .collect(toList());
        final List<Customer> customers = Stream.of(
                Customer.builder()
                        .email("john.smith@codebridge.pl")
                        .details(customersDetails.get(0))
                        .build(),
                Customer.builder()
                        .email("david.jones@codebridge.pl")
                        .details(customersDetails.get(1))
                        .build(),
                Customer.builder()
                        .email("michael.johnson@codebridge.pl")
                        .details(null)
                        .build())
                .map(entityManager::persist)
                .collect(toList());
        entityManager.flush();
        entityManager.clear();

        final Customer result = repository.findByCustomerDetailsId(customersDetails.get(0).getId());

        assertThat(Hibernate.isInitialized(result.getDetails()), is(false));
        assertThat(result, is(customers.get(0)));
        assertThat(result.getDetails(), is(customersDetails.get(0)));
    }

}
