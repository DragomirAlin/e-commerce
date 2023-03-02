package ro.dragomiralin.ecommerce.boot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Testable
public class PostgreSqlContainerLiveTest {

    @Rule
    public PostgreSQLContainer postgresContainer = new PostgreSQLContainer("postgres:9.4");

    static {
        // Postgres JDBC driver uses JUL; disable it to avoid annoying, irrelevant, stderr logs during connection testing
        LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
    }

    @Test
    public void whenSelectQueryExecuted_thenResultsReturned() throws Exception {
        ResultSet resultSet = performQuery(postgresContainer, "SELECT 1");
        resultSet.next();
        int result = resultSet.getInt(1);
        assertEquals(1, result);
    }

    private ResultSet performQuery(PostgreSQLContainer postgreSQLContainer, String query) throws SQLException {
        String jdbcUrl = postgreSQLContainer.getJdbcUrl();
        String username = postgreSQLContainer.getUsername();
        String password = postgreSQLContainer.getPassword();

        Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
        return conn.createStatement().executeQuery(query);
    }
}