package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoUsuario;
import mx.edu.utez.pres.srde.model.BeanUsuario;

import java.sql.SQLException;

public class ServiceUsuario {
    public BeanUsuario verificarUsuario(BeanUsuario usuario) {
        DaoUsuario daoUsuario = new DaoUsuario();
        String correo = usuario.getCorreo();
        String password = usuario.getPassword();
        BeanUsuario usuarioLogueado = daoUsuario.verificarUsuario(correo,password);
        return usuarioLogueado;
    }
}
