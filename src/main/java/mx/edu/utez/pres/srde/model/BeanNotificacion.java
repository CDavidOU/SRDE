package mx.edu.utez.pres.srde.model;

import java.sql.Date;

public class BeanNotificacion {
    private int idCalendario;
    private int usuario;
    private int id_usuario_docente;
    private int id_periodo;
    private int tipo_doc;
    private String descripcion;
    private Date fechaLimite;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(int idCalendario) {
        this.idCalendario = idCalendario;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getId_usuario_docente() {
        return id_usuario_docente;
    }

    public void setId_usuario_docente(int id_usuario_docente) {
        this.id_usuario_docente = id_usuario_docente;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public int getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(int tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
