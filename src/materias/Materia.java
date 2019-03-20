/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package materias;

/**
 *
 * @author alanglezh
 */
public class Materia {
    private int NRC;
    private String nombre;
    private int creditos;
    private String horas_practicas;
    private String horas_teoricas;

    public Materia() {
         
    }

    public int getNRC() {
        return NRC;
    }

    public void setNRC(int NRC) {
        this.NRC = NRC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getHoras_practicas() {
        return horas_practicas;
    }

    public void setHoras_practicas(String horas_practicas) {
        this.horas_practicas = horas_practicas;
    }

    public String getHoras_teoricas() {
        return horas_teoricas;
    }

    public void setHoras_teoricas(String horas_teoricas) {
        this.horas_teoricas = horas_teoricas;
    }

    public Materia(int NRC, String nombre, int creditos, String horas_practicas, String horas_teoricas) {
        this.NRC = NRC;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horas_practicas = horas_practicas;
        this.horas_teoricas = horas_teoricas;
    }

    @Override
    public String toString() {
       return nombre + " - " + NRC;
    }
    
}
