package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanPersona;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {

    public BeanUsuario verificarUsuario(String correo, String password) {
        BeanUsuario usuarioLogueado = null;
        //upper para detectar minusculas o mayusculas, raw por el tipo de formato que se vuelve la contra con el standar/hash
        String sql = "SELECT u.correo, u.contrasena, u.rol, u.id_usuario, d.estado FROM usuario u LEFT JOIN docente d ON u.id_usuario = d.id_usuario WHERE UPPER(TRIM(u.correo)) = UPPER(TRIM(?)) AND UPPER(u.contrasena) = RAWTOHEX(STANDARD_HASH(?, 'SHA256')) AND (u.rol = 'Administrador' OR (u.rol = 'Docente' AND UPPER(d.estado) = 'ACTIVO'))";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {
            prs.setString(1, correo);
            prs.setString(2, password);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    usuarioLogueado = new BeanUsuario();
                    usuarioLogueado.setPassword(rs.getString("contrasena"));
                    usuarioLogueado.setRol(rs.getString("rol"));
                    usuarioLogueado.setId(rs.getInt("id_usuario"));
                    BeanPersona persona = new BeanPersona();
                    persona.setId(rs.getInt("id_usuario"));
                    persona.setCorreo(rs.getString("correo"));

                    usuarioLogueado.setDatosPersona(persona);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioLogueado;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE CORREO = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, correo);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // NUEVO MÉTODO: Guarda el PIN de 4 dígitos en la BD
    public boolean guardarTokenRecuperacion(String correo, String token) {
        String sql = "UPDATE USUARIO SET TOKEN_RESTABLECIMIENTO = ? WHERE CORREO = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, token);
            prs.setString(2, correo);
            return prs.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // NUEVO MÉTODO: Verifica que el PIN introducido sea el correcto
    public boolean validarToken(String correo, String token) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE CORREO = ? AND TOKEN_RESTABLECIMIENTO = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, correo);
            prs.setString(2, token);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // MÉTODO MODIFICADO: Ahora limpia el token después de usarlo
    public boolean restablecerContrasena(String correo, String nuevaContrasena) {
        boolean actualizado = false;
        // Se agregó TOKEN_RESTABLECIMIENTO = NULL para invalidar el PIN viejo
        String sql = "UPDATE USUARIO SET contrasena = STANDARD_HASH(?, 'SHA256'), TOKEN_RESTABLECIMIENTO = NULL WHERE correo = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, nuevaContrasena);
            prs.setString(2, correo);

            int filasAfectadas = prs.executeUpdate();
            if (filasAfectadas > 0) {
                actualizado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }

    public boolean cambiarContrasena(int idUsuario, String nuevaContrasena) {
        boolean actualizado = false;
        String sql = "UPDATE USUARIO SET contrasena = RAWTOHEX(STANDARD_HASH(?, 'SHA256')) where id_Usuario=?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, nuevaContrasena);
            prs.setInt(2, idUsuario);

            int filasAfectadas = prs.executeUpdate();
            if (filasAfectadas > 0) {
                actualizado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
    }
}