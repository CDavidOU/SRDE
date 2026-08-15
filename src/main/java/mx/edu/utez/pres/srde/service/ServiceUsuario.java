package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoUsuario;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import java.util.Random;

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

    public boolean existeCorreo(String correo) {
        return daoUsuario.existeCorreo(correo);
    }

    // NUEVO MÉTODO: Genera el PIN aleatorio de 4 dígitos
    public String generarPinRecuperacion() {
        Random random = new Random();
        int pin = 1000 + random.nextInt(9000); // Garantiza un número entre 1000 y 9999
        return String.valueOf(pin);
    }

    // NUEVO MÉTODO: Guarda el PIN generado en la BD
    public boolean asignarTokenUsuario(String correo, String token) {
        return daoUsuario.guardarTokenRecuperacion(correo, token);
    }

    // NUEVO MÉTODO: Valida el PIN antes de permitir el cambio
    public boolean validarToken(String correo, String token) {
        return daoUsuario.validarToken(correo, token);
    }

    public boolean restablecerContrasena(String correo, String nuevaContrasena) {
        return daoUsuario.restablecerContrasena(correo, nuevaContrasena);
    }
}