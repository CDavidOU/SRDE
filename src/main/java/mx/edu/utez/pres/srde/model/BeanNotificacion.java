package mx.edu.utez.pres.srde.model;

import java.sql.Date;

public class BeanNotificacion {
    
    // ==========================================
    // Campos de la rama Carlos (Calendario)
    // ==========================================
    private int idCalendario;
    private int usuario;
    private int id_usuario_docente;
    private int id_periodo;
    private int tipo_doc;
    private String descripcion;
    private Date fechaLimite;

    // ==========================================
    // Campos de la rama union (Documentos pendientes)
    // ==========================================
    private String matricula;
    private String estudianteNombre;
    private String estudianteApellido;
    private String nombreDocumento;
    private String docenteNombre;
    private int idAsignacion;
    private int idTipoDoc;

    // ==========================================
    // Getters y Setters - Rama Carlos
    // ==========================================
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

    // ==========================================
    // Getters y Setters - Rama union
    // ==========================================
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public String getEstudianteApellido() {
        return estudianteApellido;
    }

    public void setEstudianteApellido(String estudianteApellido) {
        this.estudianteApellido = estudianteApellido;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getDocenteNombre() {
        return docenteNombre;
    }

    public void setDocenteNombre(String docenteNombre) {
        this.docenteNombre = docenteNombre;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(int idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }
}