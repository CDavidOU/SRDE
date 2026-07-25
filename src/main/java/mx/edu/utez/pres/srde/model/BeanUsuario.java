package mx.edu.utez.pres.srde.model;

public class BeanUsuario{
    private String password;
    private int id;
    private String rol;
    private BeanPersona persona;
    public BeanPersona getPersona()
    {
        return persona;
    }
    public void setPersona(BeanPersona persona)
    {
        this.persona = persona;
    }

    public String getPassword() {
        return password;
    }
    private BeanPersona datosPersona;
    public void setPassword(String password) {
        this.password = password;
    }

    public BeanPersona getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(BeanPersona datosPersona) {
        this.datosPersona = datosPersona;
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
}
