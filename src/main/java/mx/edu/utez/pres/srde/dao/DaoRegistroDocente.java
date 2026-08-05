package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.*;

public class DaoRegistroDocente {
    public int registrarUsuario(BeanUsuario docenteUsuario,BeanDocente docente) {
        String sqlUsuario = "INSERT INTO usuario (correo, contrasena, rol) VALUES (?, STANDARD_HASH(?, 'SHA256'), 'Docente')";
        int idGenerado = 0;


        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sqlUsuario, new String[]{"id_usuario"})) {
                prs.setString(1,docente.getCorreo());
                prs.setString(2,docenteUsuario.getPassword());
                int filasAfectadasUsuario = prs.executeUpdate();
            try (ResultSet rs = prs.getGeneratedKeys()) {
                if (rs.next()) {
                    idGenerado = rs.getInt(1); // Guardamos el número de ID
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return idGenerado;
    }
    public boolean registrarDocente (BeanDocente nuevoDocente, int idUsuarioGenerado) {
        String sqlDocente = "INSERT INTO docente (id_usuario, nombre, apellido, telefono, academia, carrera, estado) VALUES (?, ?, ?, ?, ?, ?, 'activo')";
        boolean registrado = false;
        try(Connection conexion =Conexion.getConexion();
        PreparedStatement prs =conexion.prepareStatement(sqlDocente)) {
            prs.setInt(1, idUsuarioGenerado);
            prs.setString(2, nuevoDocente.getNombre());
            prs.setString(3, nuevoDocente.getApellido());
            prs.setString(4, nuevoDocente.getTelefono());
            prs.setString(5, nuevoDocente.getAcademia());
            prs.setString(6, nuevoDocente.getCarrera());
            int filasAfectadasDocente = prs.executeUpdate();
            if (filasAfectadasDocente > 0) {
                registrado = true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return registrado;
    }
public boolean existeCorreo(String correo) {
    String sql = "SELECT COUNT(*) FROM usuario WHERE UPPER(TRIM(correo))= UPPER(TRIM(?))";
    try (Connection conexion = Conexion.getConexion();
         PreparedStatement prs = conexion.prepareStatement(sql)) {

        prs.setString(1, correo);
        try (ResultSet rs = prs.executeQuery()) {
            if (rs.next()) {
                // Si el conteo es mayor a 0, significa que el correo ya existe
                return rs.getInt(1) > 0;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}