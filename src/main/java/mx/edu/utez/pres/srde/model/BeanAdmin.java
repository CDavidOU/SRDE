package mx.edu.utez.pres.srde.model;

public class BeanAdmin extends BeanPersona{
    private String telefono;
    private int id;

    public String getTelefono() {
        return telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
