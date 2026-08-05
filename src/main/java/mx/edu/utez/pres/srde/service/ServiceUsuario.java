package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoUsuario;
import mx.edu.utez.pres.srde.model.BeanUsuario;

public class ServiceUsuario {
    
    private final DaoUsuario daoUsuario = new DaoUsuario();
    
    public BeanUsuario verificarUsuario(String correo, String password) {
        if (correo == null || password == null || correo.trim().isEmpty() || password.trim().isEmpty()) {
            return null;
        }
        return daoUsuario.verificarUsuario(correo, password);
    }

    public boolean cambiarContrasena(int idUsuario, String nuevaContrasena) {
        return daoUsuario.cambiarContrasena(idUsuario, nuevaContrasena);
    }

    // Métodos añadidos de la rama feature/union
    public boolean existeCorreo(String correo) {
        return daoUsuario.existeCorreo(correo);
    }

    public boolean restablecerContrasena(String correo, String nuevaContrasena) {
        return daoUsuario.restablecerContrasena(correo, nuevaContrasena);
    }
}