package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoRegistroEstudiante {

    // Método 1: Verificar si la matrícula ya existe
    public boolean existeMatricula(String matricula) {
        String sql = "SELECT COUNT(*) FROM ESTUDIANTE WHERE MATRICULA = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, matricula);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("⚠️ ERROR AL VERIFICAR MATRÍCULA: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Método 2: Registrar al estudiante en la base de datos
    public BeanEstudiante registrarEstudiante(BeanEstudiante nuevoEstudiante) {
        // Validar si la matrícula ya está registrada antes de intentar el insert
        if (existeMatricula(nuevoEstudiante.getMatricula())) {
            System.out.println("⚠️ La matrícula " + nuevoEstudiante.getMatricula() + " ya existe.");
            return null; // Retorna null indicando que no se insertó por duplicado
        }

        BeanEstudiante estudianteRegistrado = null;
        String sql = "INSERT INTO ESTUDIANTE (MATRICULA, NOMBRE, APELLIDO, CARRERA, CUATRIMESTRE, ESTADO, CORREO, GRUPO) VALUES (?, ?, ?, ?, ?, 'Activo', ?,?)";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, nuevoEstudiante.getMatricula());
            prs.setString(2, nuevoEstudiante.getNombre());
            prs.setString(3, nuevoEstudiante.getApellido());
            prs.setString(4, nuevoEstudiante.getCarrera());
            prs.setInt(5, nuevoEstudiante.getCuatrimestre());
            prs.setString(6, nuevoEstudiante.getCorreo());
            prs.setString(7, nuevoEstudiante.getGrupo());

            int filasAfectadas = prs.executeUpdate();

            if (filasAfectadas > 0) {
                estudianteRegistrado = nuevoEstudiante;
            }
        } catch (SQLException e) {
            System.err.println("⚠️ ERROR EN EL DAO REGISTRO: " + e.getMessage());
            e.printStackTrace();
        }

        return estudianteRegistrado;
    }
}