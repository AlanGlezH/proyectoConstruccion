/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacedao;

import horariomaterias.HorarioMateria;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alanglezh
 */
public interface HorarioMateriaDao {
    
    public List<HorarioMateria> getHorariosMaterias() throws SQLException;
    
    public boolean nuevoHorarioMateria(int nrc,String academico,String salon,int cupo,
    String horasLunes,String horasMartes,String horasMiercoles,String horasJueves, 
    String horasViernes, String horasSabados);
    
     public boolean eliminarHorarioMateria(int nrc);
     
    boolean editarHorarioMateria(int nrc,String academico,String salon,int cupo,
    String horasLunes,String horasMartes,String horasMiercoles,String horasJueves, 
    String horasViernes, String horasSabados);
     
     
     

    
    
    //public void 
    
}
