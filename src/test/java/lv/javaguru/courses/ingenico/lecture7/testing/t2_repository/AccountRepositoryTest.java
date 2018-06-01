package lv.javaguru.courses.ingenico.lecture7.testing.t2_repository;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRepositoryTest {

    private static Server server;
    protected static JdbcDataSource dataSource;

    @BeforeClass
    public static void setUpClass() throws Exception {
        server = Server.createTcpServer();
        server.start();
        dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:~/test");
        dataSource.setPassword("");
        dataSource.setUser("sa");
        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS accounts (nickname VARCHAR(50), email VARCHAR(50));");
        connection.close();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM accounts;");
        statement.executeUpdate();
        connection.commit();
        connection.close();
        server.stop();
    }

    private AccountRepository repository = new AccountRepository(dataSource);

    @Test
    public void shouldSaveNewAccount() throws Exception {
        repository.saveAccount(new Account("john", "email@email.com"));
        ResultSet resultSet = dataSource.getConnection()
                                        .prepareStatement("SELECT * FROM accounts;")
                                        .executeQuery();
        assertTrue(resultSet.next());
        assertEquals("john", resultSet.getString("nickname"));
        assertEquals("email@email.com", resultSet.getString("email"));
    }

}