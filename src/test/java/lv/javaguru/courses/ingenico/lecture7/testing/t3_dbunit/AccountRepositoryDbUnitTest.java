package lv.javaguru.courses.ingenico.lecture7.testing.t3_dbunit;

import lv.javaguru.courses.ingenico.lecture7.testing.t2_repository.Account;
import lv.javaguru.courses.ingenico.lecture7.testing.t2_repository.AccountRepository;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountRepositoryDbUnitTest {

    private static Server server;

    protected static JdbcDataSource dataSource;
    protected static IDatabaseTester databaseTester;

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
        databaseTester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:~/test", "sa", "");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Connection connection = dataSource.getConnection();
        connection.prepareStatement("DELETE FROM accounts;").executeUpdate();
        connection.commit();
        connection.close();
        server.stop();
    }

    private AccountRepository repository = new AccountRepository(dataSource);

    @Test
    public void shouldSaveNewAccount() throws Exception {
        repository.saveAccount(new Account("john", "email@email.com"));

        //load current state
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("accounts");

        //load expected state
        ClassLoader classLoader = getClass().getClassLoader();
        File datasetFile = new File(classLoader.getResource("dbunit/accounts_expected.xml").getFile());
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(datasetFile);
        ITable expectedTable = expectedDataSet.getTable("accounts");

        // Assert actual database table match expected table
        Assertion.assertEquals(expectedTable, actualTable);
    }
}