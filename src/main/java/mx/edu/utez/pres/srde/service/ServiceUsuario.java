package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoUsuario;
import mx.edu.utez.pres.srde.model.BeanUsuario;

public class ServiceUsuario {

    public BeanUsuario verificarUsuario(BeanUsuario usuario) {
        DaoUsuario daoUsuario = new DaoUsuario();
        String correo = (usuario.getDatosPersona() != null) ? usuario.getDatosPersona().getCorreo() : "";
        String password = usuario.getPassword();
        return daoUsuario.verificarUsuario(correo, password);
    }

    public boolean cambiarContrasena(int idUsuario, String nuevaContrasena) {
        DaoUsuario daoUsuario = new DaoUsuario();
        return daoUsuario.cambiarContrasena(idUsuario, nuevaContrasena);
    }
}