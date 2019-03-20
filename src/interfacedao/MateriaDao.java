/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacedao;

import java.sql.SQLException;
import java.util.List;
import materias.Materia;

/**
 *
 * @author alanglezh
 */
public interface MateriaDao {
    public List<Materia> getMaterias() throws SQLException;
    public boolean nuevaMateria(int NRC, String nombre, int creditos, String horas_practicas, String horas_teoricas);
    public boolean eliminarMateria(String NRC);
    boolean editarMateria(int NRC, String nombre, int creditos, String horas_practicas, String horas_teoricas,int nrcNuevo);
   
}
