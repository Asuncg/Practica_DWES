package connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que proporciona métodos para obtener una conexión a la base de datos y cerrarla.
 */
public class ConexionDB {

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Objeto Connection que representa la conexión a la base de datos.
     * @throws SQLException Si hay un error al establecer la conexión.
     */
    public static Connection getConnection() throws SQLException {
        final String USER = "root";
        final String PASS = "admin"; // admin en clase - 123456 en casa
        final String DB_NAME = "empresa";
        final String CONN_URL = "jdbc:mariadb://localhost:3306/" + DB_NAME;
        Connection conn = null;

        conn = DriverManager.getConnection(CONN_URL, USER, PASS);
        return conn;
    }

    /**
     * Cierra una conexión a la base de datos.
     *
     * @param conn Objeto Connection que se va a cerrar.
     * @throws SQLException Si hay un error al cerrar la conexión.
     */
    public static void close(Connection conn) throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
