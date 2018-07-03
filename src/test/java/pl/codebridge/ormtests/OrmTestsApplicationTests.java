package pl.codebridge.ormtests;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = OrmTestsApplicationTests.Initializer.class)
@ActiveProfiles("test")
public class OrmTestsApplicationTests {

	public static class Initializer extends PostgresSqlDataSourceInitializer {}

	@ClassRule
	public static PostgreSQLContainer postgres = TestContext.postgreSqlContainer();

	@Test
	public void context_loads() {

	}

}
