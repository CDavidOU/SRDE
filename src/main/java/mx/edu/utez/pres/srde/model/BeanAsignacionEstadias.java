package mx.edu.utez.pres.srde.model;

public class BeanAsignacionEstadias {
    private int id_docente;
    private int id_periodo;
    private String matricula;
    private int id_asignacion;

    private BeanEstudiante estudiante;

    public BeanEstudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(BeanEstudiante estudiante) {
        this.estudiante = estudiante;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getId_asignacion() {
        return id_asignacion;
    }

    public void setId_asignacion(int id_asignacion) {
        this.id_asignacion = id_asignacion;
    }
}
