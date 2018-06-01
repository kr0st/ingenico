package lv.javaguru.courses.ingenico.lecture7.testing.t2_repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountRepository {

    public static final String INSERT = "INSERT INTO accounts VALUES ('%s', '%s');";

    private DataSource dataSource;

    public AccountRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveAccount(Account account){
        try(Connection connection = dataSource.getConnection()){
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(INSERT, account.getNickname(), account.getEmail()));
            connection.commit();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
