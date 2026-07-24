package mx.edu.utez.pres.srde.model;

public class BeanUsuario extends BeanPersona{
    private String password;
    private int id;
    private String rol;

    private BeanAdmin datosAdmin;
    private BeanDocente datosDocente;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public BeanAdmin getDatosAdmin() {
        return datosAdmin;
    }

    public void setDatosAdmin(BeanAdmin datosAdmin) {
        this.datosAdmin = datosAdmin;
    }

    public BeanDocente getDatosDocente() {
        return datosDocente;
    }

    public void setDatosDocente(BeanDocente datosDocente) {
        this.datosDocente = datosDocente;
    }
}
