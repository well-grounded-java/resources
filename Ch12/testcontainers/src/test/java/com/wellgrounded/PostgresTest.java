package com.wellgrounded;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class PostgresTest {
    public static DockerImageName imageName = DockerImageName.parse("postgres:9.6.12");

    @Container
    public static PostgreSQLContainer postgres = new PostgreSQLContainer<>(imageName)
            .withDatabaseName("theater_db")
            .withUsername("theater")
            .withPassword("password");

    @BeforeAll
    public static void setup() throws SQLException, IOException {
        var sql = Files.readString(Path.of("src/main/resources/init.sql"));
        try (Connection conn = getConnection()) {
            conn.createStatement().execute(sql);
        }
    }

    @Test
    public void emptyDatabase() throws SQLException {
        try (Connection conn = getConnection()) {
            ResultSet result = conn.createStatement().executeQuery("SELECT * FROM prices");
            assertEquals(0, result.getFetchSize());
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = String.format(
                "jdbc:postgresql://%s:%s/%s",
                postgres.getHost(),
                postgres.getFirstMappedPort(),
                postgres.getDatabaseName());

        return DriverManager.getConnection(url, postgres.getUsername(), postgres.getPassword());
    }

}
