package pl.codebridge.ormtests;

import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class PostgresSqlDataSourceInitializer implements
        ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        final PostgreSQLContainer postgres = PostgresSqlContainerHolder.getInstance().get();
        EnvironmentTestUtils.addEnvironment("testcontainers", context.getEnvironment(),
                "spring.datasource.url=" + postgres.getJdbcUrl(),
                "spring.datasource.username=" + postgres.getUsername(),
                "spring.datasource.password=" + postgres.getPassword()
        );
    }

}
