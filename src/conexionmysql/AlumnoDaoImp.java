/**
 *
 *@author alanglezh
 *Descripcion: Clase para realizar las consultas a la base de datos
 */
package conexionmysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import alumnos.*;
import interfacedao.AlumnoDao;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDaoImp implements AlumnoDao {

    /**
     * Obtiene los datos de los alumnos y lo guarda en una lista de alumnos
     *
     * @return regresa la lista con los alumnos de la base datos
     * @throws SQLException si existe algun probelma de Sql
     */
   
    @Override
    public List<Alumno> getAlumnos() throws SQLException {
        List<Alumno> lista = new ArrayList<Alumno>();
        Statement s;
        Connection conn = null;
        conn = new Conectar().getConnection();
        ResultSet rs = null;
        String sQuery = "SELECT * from alumnos";
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sQuery);
            //System.out.println("Ejecutó la consulta");
            while (rs != null && rs.next()) {
                Alumno al = new Alumno();
                al.setMatricula(rs.getString("matricula"));
                al.setNombre(rs.getString("nombre"));
                al.setApellidoPaterno(rs.getString("apellidoPaterno"));
                al.setApellidoMaterno(rs.getString("apellidoMaterno"));
                lista.add(al);

            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
        } finally {
            conn.close();
        }
        if (lista != null) {
            System.out.println("Lista llena");
        } else {
            System.out.println("Lista vacia");
        }
        return lista;
    }

    /**
     * Guarda un nuevo alumno recibiendo como parametro sus datos
     *
     * @param nombre El nombre del alumno
     * @param materno El apellido materno del alumno
     * @param paterno El apellido paterno del alumno
     * @param matricula Matricula del alumno
     * @return true si se completo la insercion con exito o false en caso
     * contrario
     */
    @Override
    public boolean nuevoAlumno(String nombre, String materno, String paterno, String matricula) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;

        String query = "INSERT INTO alumnos(nombre,apellidoPaterno,apellidoMaterno,matricula) VALUES ('" + nombre + "','"
                + paterno + "','" + materno + "','" + matricula + "');";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
           

        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     *
     * @param matricula La matriucla del alumno que se desea eliminar de la BD
     * @return true si se completo la eliminacion con existe y false si hay
     * algun error
     */
    @Override
    public boolean eliminarAlumno(String matricula) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        String query = "DELETE FROM alumnos WHERE matricula ='" + matricula + "';";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
               Logger.getLogger(AlumnoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    /**
     *
     * @param matricula La matricula del alumno
     * @param nombre El nombre del alumno
     * @param paterno El apellido paterno del alumno
     * @param materno El apellido materno del alumno
     * @param matriculaNueva
     * @return true si se completo consultar con exito y false en caso contrario
     */
    @Override
    public boolean editarAlumno(String matricula, String nombre, String paterno, String materno,String 
            matriculaNueva) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        System.out.println("Editar alumno BD");
        String query = "UPDATE alumnos SET nombre = '" + nombre + "', apellidoPaterno = '"
                + paterno + "', apellidoMaterno = '" + materno + "' , matricula = '" +matriculaNueva+ 
                "' WHERE matricula = '" + matricula + "';";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
            // System.out.println("Ejecutó la consulta");

        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(AlumnoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

}
