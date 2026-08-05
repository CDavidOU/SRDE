package mx.edu.utez.pres.srde.model;

import java.sql.Date;
import java.util.List; // Agregamos la importación para usar Listas

public class BeanPeriodo {
    private String nombre_periodo;
    private Date fecha_inicio;
    private Date fecha_fin;
    private int id_periodo;

    // NUEVA VARIABLE: Aquí guardaremos a los alumnos que pertenecen a este periodo
    private List<BeanEstudiante> listaEstudiantes;
    private int totalEstudiantes;

    public int getTotalEstudiantes() {
        return totalEstudiantes;
    }

    public void setTotalEstudiantes(int totalEstudiantes) {
        this.totalEstudiantes = totalEstudiantes;
    }

    public String getNombre_periodo() {
        return nombre_periodo;
    }

    public void setNombre_periodo(String nombre_periodo) {
        this.nombre_periodo = nombre_periodo;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    // NUEVOS MÉTODOS: Getter y Setter para poder meter y sacar la lista de estudiantes
    public List<BeanEstudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<BeanEstudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }
}