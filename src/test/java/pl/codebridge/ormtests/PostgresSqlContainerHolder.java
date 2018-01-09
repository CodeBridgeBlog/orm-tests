package pl.codebridge.ormtests;

import org.testcontainers.containers.PostgreSQLContainer;

public enum PostgresSqlContainerHolder {

    INSTANCE;

    public static PostgresSqlContainerHolder getInstance() {
        return INSTANCE;
    }

    private final PostgreSQLContainer container = new PostgreSQLContainer("postgres:9.6.3");

    public PostgreSQLContainer get() {
        return container;
    }

}
