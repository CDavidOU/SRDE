package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDatosEstudiantes {

    public BeanEstudiante datosEstudiante(String matricula) {
        BeanEstudiante estudiante = null;

        String sql = "SELECT e.*, ae.id_asignacion, ae.id_usuario_docente AS idDocente, (SELECT d.nombre || ' ' || d.apellido FROM docente d WHERE d.id_usuario = ae.id_usuario_docente) AS docenteAsignado FROM estudiante e LEFT JOIN asignacion_estadias ae ON e.matricula = ae.matricula WHERE e.matricula = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    estudiante = new BeanEstudiante();
                    estudiante.setMatricula(rs.getString("matricula"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setCarrera(rs.getString("carrera"));
                    estudiante.setCuatrimestre(rs.getInt("cuatrimestre"));
                    estudiante.setGrupo(rs.getString("grupo"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setEstado(rs.getString("estado"));

                    estudiante.setIdAsignacion(rs.getInt("id_asignacion"));
                    estudiante.setDocenteAsignado(rs.getString("docenteAsignado"));

                    // Mapear el ID del docente asignado al Bean
                    estudiante.setIdDocente(rs.getInt("idDocente"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar datos del estudiante: " + e.getMessage());
            e.printStackTrace();
        }

        return estudiante;
    }
<<<<<<< HEAD

    public boolean actualizarEstudiante(BeanEstudiante estudiante) {
        boolean actualizado = false;
        // Ajusta los nombres de las columnas si en tu BD se llaman diferente
        String sql = "UPDATE ESTUDIANTE SET NOMBRE = ?, APELLIDO = ?, CARRERA = ?, CUATRIMESTRE = ?, GRUPO = ?, CORREO = ?, ESTADO = ? WHERE MATRICULA = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, estudiante.getNombre());
            prs.setString(2, estudiante.getApellido());
            prs.setString(3, estudiante.getCarrera());
            prs.setInt(4, estudiante.getCuatrimestre());
            prs.setString(5, estudiante.getGrupo());
            prs.setString(6, estudiante.getCorreo());
            prs.setString(7, estudiante.getEstado());
            prs.setString(8, estudiante.getMatricula());

            actualizado = prs.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actualizado;
=======
    public boolean actualizarEstudiante(String matriculaOriginal, BeanEstudiante estudiante, int idDocente) {
        String sql = "UPDATE estudiante SET matricula = ?, nombre = ?, apellido = ?, carrera = ?, " +
                "cuatrimestre = ?, grupo = ?, correo = ?, estado = ?, id_docente = ? " +
                "WHERE matricula = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estudiante.getMatricula());
            ps.setString(2, estudiante.getNombre());
            ps.setString(3, estudiante.getApellido());
            ps.setString(4, estudiante.getCarrera());
            ps.setInt(5, estudiante.getCuatrimestre());
            ps.setString(6, estudiante.getGrupo());
            ps.setString(7, estudiante.getCorreo());
            ps.setString(8, estudiante.getEstado());
            ps.setInt(9, idDocente); // Aquí asignas la FK del docente
            ps.setString(10, matriculaOriginal); // WHERE por la matrícula que tenía antes

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    }
}