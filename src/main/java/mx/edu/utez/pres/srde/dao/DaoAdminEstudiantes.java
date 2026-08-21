package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoAdminEstudiantes {

    // Eliminamos el filtro estricto de es.estado = 'Activo'
    private static final String SQL_BASE =
            "SELECT es.matricula, es.nombre, es.apellido, es.carrera, es.estado, " +
                    "d.nombre AS docente_nombre, d.apellido AS docente_apellido " +
                    "FROM estudiante es " +
                    "LEFT JOIN asignacion_estadias ae ON es.matricula = ae.matricula AND ae.id_periodo = ? " +
                    "LEFT JOIN docente d ON ae.id_usuario_docente = d.id_usuario ";

    public List<BeanEstudiante> listaEstudiantes(int idPeriodo) {
        List<BeanEstudiante> listaEstudiantes = new ArrayList<>();
        String sql = SQL_BASE + "ORDER BY es.nombre, es.apellido";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idPeriodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    listaEstudiantes.add(mapearEstudiante(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes (admin): " + e.getMessage());
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    public List<BeanEstudiante> buscarEstudiantes(int idPeriodo, String condicion) {
        List<BeanEstudiante> listaEstudiantes = new ArrayList<>();
        // Agregamos WHERE para el buscador
        String sql = SQL_BASE + "WHERE (LOWER(es.nombre) LIKE LOWER(?) OR LOWER(es.apellido) LIKE LOWER(?) OR LOWER(es.matricula) LIKE LOWER(?)) " +
                "ORDER BY es.nombre, es.apellido";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            String textoBuscar = "%" + condicion.toLowerCase() + "%";

            prs.setInt(1, idPeriodo);
            prs.setString(2, textoBuscar);
            prs.setString(3, textoBuscar);
            prs.setString(4, textoBuscar);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    listaEstudiantes.add(mapearEstudiante(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiantes (admin): " + e.getMessage());
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    private BeanEstudiante mapearEstudiante(ResultSet rs) throws SQLException {
        BeanEstudiante estudiante = new BeanEstudiante();
        estudiante.setMatricula(rs.getString("matricula"));
        estudiante.setNombre(rs.getString("nombre"));
        estudiante.setApellido(rs.getString("apellido"));
        estudiante.setCarrera(rs.getString("carrera"));
        estudiante.setEstado(rs.getString("estado"));

        String docenteNombre = rs.getString("docente_nombre");
        String docenteApellido = rs.getString("docente_apellido");
        estudiante.setDocenteAsignado(docenteNombre != null ? docenteNombre + " " + docenteApellido : "Sin asignar");

        return estudiante;
    }
}