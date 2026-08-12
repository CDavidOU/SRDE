package mx.edu.utez.pres.srde.model;

import java.io.InputStream;

public class BeanArchivo {
    private int id_archivo;
    private String nombre_archivo;
    private int id_tipo_doc;
    private long tamano;
    private String observaciones;
    private String estado;
    private String fecha_limite;
    private InputStream contenido_archivo; //Aqui se guardan los bytes del pdf
    private String matricula;
    private byte[] archivoBytes;
    private boolean puedeSubir;
    private boolean revisado;
    private boolean tieneCalendario;

    public boolean isRevisado() {
        return revisado;
    }

    public void setRevisado(boolean revisado) {
        this.revisado = revisado;
    }

    public boolean isTieneCalendario() {
        return tieneCalendario;
    }

    public void setTieneCalendario(boolean tieneCalendario) {
        this.tieneCalendario = tieneCalendario;
    }

    public boolean isPuedeSubir() {
        return puedeSubir;
    }

    public void setPuedeSubir(boolean puedeSubir) {
        this.puedeSubir = puedeSubir;
    }

    public byte[] getArchivoBytes() {
        return archivoBytes;
    }

    public void setArchivoBytes(byte[] archivoBytes) {
        this.archivoBytes = archivoBytes;
    }

    public int getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(int id_archivo) {
        this.id_archivo = id_archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public long getTamano() {
        return tamano;
    }

    public void setTamano(long tamano) {
        this.tamano = tamano;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public InputStream getContenido_achivo() {
        return contenido_archivo;
    }

    public void setContenido_achivo(InputStream contenido_achivo) {
        this.contenido_archivo = contenido_achivo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getId_tipo_doc() {
        return id_tipo_doc;
    }

    public void setId_tipo_doc(int id_tipo_doc) {
        this.id_tipo_doc = id_tipo_doc;
    }

    public InputStream getContenido_archivo() {
        return contenido_archivo;
    }

    public void setContenido_archivo(InputStream contenido_archivo) {
        this.contenido_archivo = contenido_archivo;
    }
}
