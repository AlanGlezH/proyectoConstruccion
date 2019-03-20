/**
 *
 * @author alanglezh
 * Descripcion: Clase para realizar la conexion con la base de datos
 */
package conexionmysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conectar {

    private Connection conn;
    private String host = "localhost";
    private String db = "registroAlumnos";
    private String username = "root";
    private String password = "mojoneitor";
    private String url = "jdbc:mysql://" + host + "/" + db;
    String error;
    private static Conectar conexion;

    /**
     * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver"
     * y se conecta a traves de la url especificada en el atributo, un usuario
     * que se encuentra en el atributo username y la contraseña de la base de
     * datos que se encuentra en el atributo password. El constructor crea una
     * "copia" que es asignada al atributo con.
     */
    public Conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("Conectando a la base de datos...");
            try {
                conn = DriverManager.getConnection(url, username, password);
                if (conn != null) {
                    System.out.println("Conexión a la base de datos establecida.");
                }
            } catch (SQLException ex) {

                error = ex.getMessage();
                System.out.println("Error de mysql" + error + getMensajeError());
                JOptionPane.showMessageDialog(null, getMensajeError());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Se produjo un error inesperado " + e.getMessage());
        }

        conexion = this;

    }

    /**
     * El constructor crea una instancia de la clase "com.mysql.cj.jdbc.Driver"
     * y se conecta a traves de la url especificada en el parametro url, un
     * usuario que se encuentra en el parametro username y la contraseña de la
     * base de datos que se encuentra en el parametro password. El constructor
     * crea una "copia" que es asignada al atributo con.
     *
     * @param host Dirrecion en la cual se encuentra alojado el servidor de la
     * base de datos a la cual se quiere acceder
     * @param db Nombre de la base de datos especifica a la cual se quiere
     * acceder
     * @param username Nombre del usurio con el cual se va a loggear en la base
     * de datos
     * @param password Contraseña perteneciente a la cuenta de usuario.
     */
    public Conectar(String host, String db, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.host = host;
        this.db = db;
        this.username = username;
        this.password = password;
        conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + db, username, password);
        conexion = this;
    }

    /*
    
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Cierra la conexion hacia la base de datos
     */
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
        }
    }

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public String getMensajeError() {

        return error;

    }
}
