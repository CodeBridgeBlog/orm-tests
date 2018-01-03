package pl.codebridge.ormtests.repository;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsNot.not;

import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import pl.codebridge.ormtests.PostgresSqlDataSourceInitializer;
import pl.codebridge.ormtests.model.CustomerDetails;

@ContextConfiguration(initializers = CustomerDetailsRepositoryTest.Initializer.class)
public class CustomerDetailsRepositoryTest extends BaseRepositoryTest {

    public static class Initializer extends PostgresSqlDataSourceInitializer {}

    @Autowired
    private CustomerDetailsRepository repository;

    @Test
    public void should_find_customers_details_by_age_between_20_and_30() {
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
                        .build(),
                CustomerDetails.builder()
                        .firstName("Michael")
                        .lastName("Johnson")
                        .age(25)
                        .build(),
                CustomerDetails.builder()
                        .firstName("Chris")
                        .lastName("Lee")
                        .age(30)
                        .build(),
                CustomerDetails.builder()
                        .firstName("Mike")
                        .lastName("Brown")
                        .age(33)
                        .build())
                .map(entityManager::persist)
                .collect(toList());
        entityManager.flush();
        entityManager.clear();

        final List<CustomerDetails> result = repository.findByAgeBetween(20, 30);

        assertThat(result, hasSize(3));
        assertThat(result, hasItem(customersDetails.get(1)));
        assertThat(result, hasItem(customersDetails.get(2)));
        assertThat(result, hasItem(customersDetails.get(3)));
        assertThat(result, not(hasItem(customersDetails.get(0))));
        assertThat(result, not(hasItem(customersDetails.get(4))));
    }

}
