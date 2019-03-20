/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmysql;

import interfacedao.MateriaDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import materias.Materia;

/**
 *
 * @author alanglezh
 */
public class MateriaDaoImp implements MateriaDao {
    
    @Override
    public List<Materia> getMaterias() throws SQLException {
        List<Materia> lista = new ArrayList<Materia>();
        Statement s;
        Connection conn = null;
        conn = new Conectar().getConnection();
        ResultSet rs = null;
        String sQuery = "SELECT * from materias";
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sQuery);
            System.out.println("Ejecutó la consulta");
            while (rs != null && rs.next()) {
                Materia mat = new Materia();
                mat.setNRC(rs.getInt("nrc"));
                mat.setNombre(rs.getString("nombre"));
                mat.setCreditos(rs.getInt("creditos"));
                mat.setHoras_practicas(rs.getString("horasPracticas"));
                mat.setHoras_teoricas(rs.getString("horasTeoricas"));
                System.out.println(mat);
                
                lista.add(mat);

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

    @Override
    public boolean nuevaMateria(int NRC, String nombre, int creditos, String horas_practicas, String horas_teoricas) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;

        String query = "INSERT INTO Materias(nrc,nombre,creditos,horasPracticas,horasTeoricas) VALUES ('" + NRC + "','"
                + nombre + "','" + creditos + "','" + horas_practicas + "','" + horas_teoricas + "');";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
           

        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                //Logger.getLogger(AlumnoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return true;
    } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminarMateria(String NRC) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        String query = "DELETE FROM materias WHERE nrc ='" + NRC + "';";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
               //Logger.getLogger(AlumnoDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
   
    }

    @Override
    public boolean editarMateria(int NRC, String nombre, int creditos, String horas_practicas, String horas_teoricas, int nrcNuevo) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        System.out.println("Editar materia BD");
        String query = "UPDATE materias SET nombre = '" + nombre + "', creditos = '"
                + creditos + "', horasPracticas = '" + horas_practicas + "' , horasTeoricas = '" +horas_practicas+ "'nrc = '"+NRC+
                "' WHERE nrc = '" + nrcNuevo + "';";
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
                //Logger.getLogger(MateriaDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
   

    
    
}
