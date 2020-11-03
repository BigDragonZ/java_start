package cloud.bigdragon.crazyjava.sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ExecuteDDL {
    private String driver;

    private String url;
    private String username;
    private String password;

    public void init(String initParam) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(initParam));
        this.driver = props.getProperty("driver");
        this.url = props.getProperty("url");
        this.username = props.getProperty("username");
        this.password = props.getProperty("password");
    }

    public void createTable(String sql) throws ClassNotFoundException {
        Class.forName(driver);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExecuteDDL ddl = new ExecuteDDL();
        ddl.init("src/main/resources/mysql.ini");
        ddl.createTable("create table create_test" + "( test_id int auto_increment primary key, test_name varchar(32));");
        System.out.println("创建成功");
    }
}
