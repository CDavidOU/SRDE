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
        List<BeanEstudiante> lista = new ArrayList<>();
        String sql = "SELECT matricula, nombre, apellido, carrera, estado, correo, cuatrimestre, grupo " +
                "FROM estudiante " +
                "WHERE LOWER(TRANSLATE(nombre, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) LIKE LOWER(TRANSLATE(?, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) " +
                "OR LOWER(TRANSLATE(apellido, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) LIKE LOWER(TRANSLATE(?, 'ÁÉÍÓÚáéíóú', 'AEIOUaeiou')) " +
                "OR LOWER(matricula) LIKE LOWER(?) " +
                "ORDER BY apellido ASC, nombre ASC";

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