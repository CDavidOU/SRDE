package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.util.Conexion; // Ajusta el import de tu clase Conexion

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoListaDocentes {

    // Método para listar todos los docentes activos
    public List<BeanDocente> listaDocentes(int id_periodo) {
        List<BeanDocente> listaDocentes = new ArrayList<>();

        String sql = "SELECT d.id_usuario, d.nombre, d.telefono, d.academia, d.carrera, d.estado, " +
                "(SELECT COUNT(*) FROM asignacion_estadias aes WHERE aes.id_usuario_docente = d.id_usuario AND aes.id_periodo = ?) AS total_alumnos " +
                "FROM docente d " +
                "WHERE d.estado = 'Activo'";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, id_periodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanDocente docente = new BeanDocente();
                    docente.setId(rs.getInt("id_usuario"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setAcademia(rs.getString("academia"));
                    docente.setCarrera(rs.getString("carrera"));
                    docente.setEstado(rs.getString("estado"));
                    docente.setTotalAlumnos(rs.getInt("total_alumnos"));

                    listaDocentes.add(docente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar docentes: " + e.getMessage());
            e.printStackTrace();
        }
        return listaDocentes;
    }

    // Método para buscar docentes por criterio de nombre
    public List<BeanDocente> buscarDocentes(String criterio, int id_periodo) {
        List<BeanDocente> listaDocentes = new ArrayList<>();

        String sql = "SELECT d.id_usuario, d.nombre, d.telefono, d.academia, d.carrera, d.estado, " +
                "(SELECT COUNT(*) FROM asignacion_estadias aes WHERE aes.id_usuario_docente = d.id_usuario AND aes.id_periodo = ?) AS total_alumnos " +
                "FROM docente d " +
                "WHERE d.estado = 'Activo' AND UPPER(d.nombre) LIKE UPPER(?)";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, id_periodo);
            prs.setString(2, "%" + criterio + "%");

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanDocente docente = new BeanDocente();
                    docente.setId(rs.getInt("id_usuario"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setAcademia(rs.getString("academia"));
                    docente.setCarrera(rs.getString("carrera"));
                    docente.setEstado(rs.getString("estado"));
                    docente.setTotalAlumnos(rs.getInt("total_alumnos"));

                    listaDocentes.add(docente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar docentes: " + e.getMessage());
            e.printStackTrace();
        }
        return listaDocentes;
    }
}