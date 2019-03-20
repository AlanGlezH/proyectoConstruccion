/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacedao;

import alumnos.Alumno;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alanglezh
 */
public interface AlumnoDao {

    public boolean nuevoAlumno(String nombre, String materno, String paterno, String matricula);

    public boolean eliminarAlumno(String matricula);

    boolean editarAlumno(String matricula, String nombre, String paterno, String materno, String matriculaNueva);

    public List<Alumno> getAlumnos() throws SQLException;
}
