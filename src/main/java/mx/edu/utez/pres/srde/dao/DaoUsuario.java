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

        String sql = "select correo,contrasena,rol,id_usuario from usuario where correo=? and contrasena=STANDARD_HASH(?, 'SHA256') ";

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

            } catch (Exception e) {
                e.printStackTrace();
            }

            return usuarioLogueado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public boolean restablecerContrasena(String correo, String nuevaContrasena) {
        boolean actualizado = false;
        String sql = "UPDATE USUARIO SET contrasena = STANDARD_HASH(?, 'SHA256') WHERE correo = ?";
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
        String sql = "UPDATE USUARIO SET contrasena = STANDARD_HASH(?, 'SHA256') where id_Usuario=?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, nuevaContrasena);
            prs.setInt(2, idUsuario);

            // executeUpdate() devuelve el número de filas afectadas
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
