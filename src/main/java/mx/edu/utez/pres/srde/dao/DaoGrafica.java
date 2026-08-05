package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstadistica;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoGrafica {

    public int contarEstudiantesActivos() {
        String sql = "SELECT COUNT(*) FROM estudiante WHERE estado = 'Activo'";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar estudiantes activos: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int contarDocentes() {
        String sql = "SELECT COUNT(*) FROM docente";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar docentes: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public List<BeanEstadistica> estudiantesPorCarrera() {
        List<BeanEstadistica> lista = new ArrayList<>();
        String sql = "SELECT carrera, COUNT(*) AS total FROM estudiante WHERE estado = 'Activo' " +
                "GROUP BY carrera ORDER BY total DESC";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            while (rs.next()) {
                BeanEstadistica estadistica = new BeanEstadistica();
                estadistica.setEtiqueta(rs.getString("carrera"));
                estadistica.setCantidad(rs.getInt("total"));
                lista.add(estadistica);
            }
        } catch (SQLException e) {
            System.err.println("Error al agrupar estudiantes por carrera: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
