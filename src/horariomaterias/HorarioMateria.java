/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horariomaterias;

/**
 *
 * @author alanglezh
 */
public class HorarioMateria {
    private int nrc;
    private String salon;
    private int cupo;
    private String academico;
    private String horasLunes;
    private String horasMartes;
    private String horasMiercoles;
    private String horasJueves;
    private String horasViernes;
    private String horasSabados;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HorarioMateria(int nrc, String academico, String salon, int cupo,String horasLunes, String horasMartes, String horasMiercoles, String horasJueves, String horasViernes, String horasSabados) {
        this.nrc = nrc;
        this.salon = salon;
        this.cupo = cupo;
        this.academico = academico;
        this.horasLunes = horasLunes;
        this.horasMartes = horasMartes;
        this.horasMiercoles = horasMiercoles;
        this.horasJueves = horasJueves;
        this.horasViernes = horasViernes;
        this.horasSabados = horasSabados;
    }
    
    public HorarioMateria(){}

    public int getNrc() {
        return nrc;
    }

    public void setNrc(int nrc) {
        this.nrc = nrc;
    }


    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getAcademico() {
        return academico;
    }

    public void setAcademico(String academico) {
        this.academico = academico;
    }

    public String getHorasLunes() {
        return horasLunes;
    }

    public void setHorasLunes(String horasLunes) {
        this.horasLunes = horasLunes;
    }

    public String getHorasMartes() {
        return horasMartes;
    }

    public void setHorasMartes(String horasMartes) {
        this.horasMartes = horasMartes;
    }

    public String getHorasMiercoles() {
        return horasMiercoles;
    }

    public void setHorasMiercoles(String horasMiercoles) {
        this.horasMiercoles = horasMiercoles;
    }

    public String getHorasJueves() {
        return horasJueves;
    }

    public void setHorasJueves(String horasJueves) {
        this.horasJueves = horasJueves;
    }

    public String getHorasViernes() {
        return horasViernes;
    }

    public void setHorasViernes(String horasViernes) {
        this.horasViernes = horasViernes;
    }

    public String getHorasSabados() {
        return horasSabados;
    }

    public void setHorasSabados(String horasSabados) {
        this.horasSabados = horasSabados;
    }

    @Override
    public String toString() {
        return "HorarioMateriaDaoImp{" + "nrc=" + nrc + ", academico=" + academico + ", salon=" + salon + ", cupo=" + cupo + ", horasLunes=" + horasLunes + ", horasMartes=" + horasMartes + ", horasMiercoles=" + horasMiercoles + ", horasJueves=" + horasJueves + ", horasViernes=" + horasViernes + ", horasSabados=" + horasSabados + '}';
    }
    
    
    
    
    
}
