package pl.codebridge.ormtests;

import lombok.Getter;
import org.testcontainers.containers.PostgreSQLContainer;

public enum TestContext {

    INSTANCE;

    public static TestContext getInstance() {
        return INSTANCE;
    }

    @Getter(lazy = true)
    private final PostgreSQLContainer postgreSqlContainer = createPostgreSQLContainer();

    public static PostgreSQLContainer postgreSqlContainer() {
        return INSTANCE.getPostgreSqlContainer();
    }

    private static PostgreSQLContainer createPostgreSQLContainer() {
        return new PostgreSQLContainer("postgres:9.6.3");
    }

}
