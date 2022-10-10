package ro.dragomiralin.ecommerce.boot.setup;

import org.testcontainers.containers.PostgreSQLContainer;

import static org.testcontainers.utility.DockerImageName.parse;

public enum PostgresqlSingletonContainer {
  INSTANCE();

  private final PostgreSQLContainer container;

  PostgresqlSingletonContainer() {
    container = new PostgreSQLContainer(parse("postgres:14"));
    container.start();
  }

  public PostgreSQLContainer getContainer() {
    return container;
  }
}
