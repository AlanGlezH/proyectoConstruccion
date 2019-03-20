/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmysql;

import horariomaterias.HorarioMateria;
import interfacedao.HorarioMateriaDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import materias.Materia;

/**
 *
 * @author alanglezh
 */
public class HorarioMateriaDaoImp implements HorarioMateriaDao {

    @Override
    public List<HorarioMateria> getHorariosMaterias() throws SQLException {
        List<HorarioMateria> lista = new ArrayList<HorarioMateria>();
        Statement s;
        Connection conn = null;
        conn = new Conectar().getConnection();
        ResultSet rs = null;
        String sQuery = "SELECT * from horarioMaterias";
        try {
            s = conn.createStatement();
            rs = s.executeQuery(sQuery);
            System.out.println("Ejecutó la consulta");
            while (rs != null && rs.next()) {
                HorarioMateria mat = new HorarioMateria();
                mat.setNrc(rs.getInt("nrc"));
                mat.setAcademico(rs.getString("academico"));
                mat.setSalon(rs.getString("salon"));
                mat.setCupo(rs.getInt("cupo"));
                mat.setHorasLunes(rs.getString("horasLunes"));
                mat.setHorasMartes(rs.getString("horasMartes"));
                mat.setHorasMiercoles(rs.getString("horasMiercoles"));
                mat.setHorasJueves(rs.getString("horasJueves"));
                mat.setHorasViernes(rs.getString("horasViernes"));
                mat.setHorasSabados(rs.getString("horasSabados"));
                mat.setId(rs.getInt("idHorario"));
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
    public boolean nuevoHorarioMateria(int nrc, String academico, String salon, int cupo, String horasLunes, String horasMartes, String horasMiercoles, String horasJueves, String horasViernes, String horasSabados) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;

        String query = "INSERT INTO horarioMaterias (nrc,academico,salon,cupo,horasLunes,horasMartes,horasMiercoles,horasJueves,horasViernes,horasSabados) VALUES (" + nrc + ",'"
                + academico + "','" + salon + "'," + cupo + "," + horasLunes
                + "," + horasMartes + "," + horasMiercoles + "," + horasJueves + "," + horasViernes + ","
                + horasSabados + ");";

        System.out.println(query);
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);

        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(HorarioMateriaDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        }
    }

    @Override
    public boolean eliminarHorarioMateria(int id) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        String query = "DELETE FROM horarioMaterias WHERE idHorario = " + id + ";";
        try {
            consulta = conexion.createStatement();
            consulta.executeUpdate(query);
        } catch (SQLException errorSQL) {
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(HorarioMateriaDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public boolean editarHorarioMateria(int nrc, String academico, String salon, int cupo, String horasLunes, String horasMartes, String horasMiercoles, String horasJueves, String horasViernes, String horasSabados) {
        Connection conexion;
        conexion = new Conectar().getConnection();
        Statement consulta;
        System.out.println("Editar materia BD");
        String query = "UPDATE horarioMaterias SET academico = '" + academico + "', salon = '"
                + salon + "', cupo = " + cupo + ", horasLunes = " + horasLunes + ", horasMartes ='" + horasMartes
                + ", horasMiercoles = " + horasMiercoles + ", horasJueves = " + horasJueves + ",horasViernes= " + horasViernes
                + ",horasSabados =" + horasSabados + "' WHERE nrc = " + nrc + ";";
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
