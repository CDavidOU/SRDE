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

<<<<<<< HEAD
    // Eliminamos el filtro estricto de es.estado = 'Activo'
    private static final String SQL_BASE =
            "SELECT es.matricula, es.nombre, es.apellido, es.carrera, es.estado, " +
                    "d.nombre AS docente_nombre, d.apellido AS docente_apellido " +
                    "FROM estudiante es " +
                    "LEFT JOIN asignacion_estadias ae ON es.matricula = ae.matricula AND ae.id_periodo = ? " +
                    "LEFT JOIN docente d ON ae.id_usuario_docente = d.id_usuario ";

=======
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    public List<BeanEstudiante> listaEstudiantes(int idPeriodo) {
        List<BeanEstudiante> lista = new ArrayList<>();
        String sql = "SELECT matricula, nombre, apellido, carrera, estado, correo, cuatrimestre, grupo " +
                "FROM estudiante ORDER BY apellido ASC, nombre ASC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearEstudiante(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes (admin): " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public List<BeanEstudiante> buscarEstudiantes(int idPeriodo, String condicion) {
<<<<<<< HEAD
        List<BeanEstudiante> listaEstudiantes = new ArrayList<>();
        // Agregamos WHERE para el buscador
        String sql = SQL_BASE + "WHERE (LOWER(es.nombre) LIKE LOWER(?) OR LOWER(es.apellido) LIKE LOWER(?) OR LOWER(es.matricula) LIKE LOWER(?)) " +
                "ORDER BY es.nombre, es.apellido";
=======
        List<BeanEstudiante> lista = new ArrayList<>();
        String sql = "SELECT matricula, nombre, apellido, carrera, estado, correo, cuatrimestre, grupo " +
                "FROM estudiante " +
                "WHERE LOWER(TRANSLATE(nombre, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) LIKE LOWER(TRANSLATE(?, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) " +
                "OR LOWER(TRANSLATE(apellido, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) LIKE LOWER(TRANSLATE(?, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) " +
                "OR LOWER(matricula) LIKE LOWER(?) " +
                "ORDER BY apellido ASC, nombre ASC";
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String texto = "%" + (condicion != null ? condicion.trim() : "") + "%";
            ps.setString(1, texto);
            ps.setString(2, texto);
            ps.setString(3, texto);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearEstudiante(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar estudiantes (admin): " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    private BeanEstudiante mapearEstudiante(ResultSet rs) throws SQLException {
        BeanEstudiante e = new BeanEstudiante();
        e.setMatricula(rs.getString("matricula"));
        e.setNombre(rs.getString("nombre"));
        e.setApellido(rs.getString("apellido"));
        e.setCarrera(rs.getString("carrera"));
        e.setEstado(rs.getString("estado"));
        e.setCorreo(rs.getString("correo"));
        e.setCuatrimestre(rs.getInt("cuatrimestre"));
        e.setGrupo(rs.getString("grupo"));
        return e;
    }
}