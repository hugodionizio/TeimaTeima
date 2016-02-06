package bancodados.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {

    private static final String URL = 
		"jdbc:derby:myDerby;create=true;user=derby;password=derby";
		
    private static final String DRIVER =
		"org.apache.derby.jdbc.EmbeddedDriver";

    public static Connection getConnection() {
//        System.out.println("Conectando ao Banco de Dados");
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}