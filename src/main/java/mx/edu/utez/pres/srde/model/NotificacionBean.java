package mx.edu.utez.pres.srde.model;
import java.sql.Date;

public class NotificacionBean {
    private int idCalendario;
    private int id_docente;
    private Date fechaVisto;
    private int visible;

    public int getIdCalendario() {
        return idCalendario;
    }

    public void setIdCalendario(int idCalendario) {
        this.idCalendario = idCalendario;
    }

    public int getId_docente() {
        return id_docente;
    }

    public void setId_docente(int id_docente) {
        this.id_docente = id_docente;
    }

    public Date getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(Date fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }
}
