package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalendarioDao {
    public CalendarioBean obtenerCalendarioPorId(int idCalendario) {
        CalendarioBean calendario = null;
        String sql = "SELECT c.ID_CALENDARIO, c.ID_PERIODO, c.ID_TIPO_DOC, t.NOMBRE_DOC, c.FECHA_INICIO, c.FECHA_LIM, c.COMENTARIO FROM CALENDARIO_ESTADIAS c INNER JOIN TIPO_DOC t ON c.ID_TIPO_DOC = t.ID_TIPO_DOC WHERE c.ID_CALENDARIO = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCalendario);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // 💡 Usamos 'if' en lugar de 'while' porque solo esperamos 1 registro
                    calendario = new CalendarioBean();
                    calendario.setIdCalendario(rs.getInt("ID_CALENDARIO"));
                    calendario.setId_periodo(rs.getInt("ID_PERIODO"));
                    calendario.setTipo_doc(rs.getInt("ID_TIPO_DOC"));
                    calendario.setNombreDoc(rs.getString("NOMBRE_DOC"));
                    calendario.setFechaInicio(rs.getDate("FECHA_INICIO"));
                    calendario.setFechaLimite(rs.getDate("FECHA_LIM"));
                    calendario.setComentario(rs.getString("COMENTARIO"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar calendario por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return calendario;
    }
        public List<CalendarioBean> obtenerCalendarios(int idPeriodo) {
            List<CalendarioBean> listaCalendarios = new ArrayList<>();
            String sql = "SELECT c.ID_CALENDARIO, c.ID_PERIODO, c.ID_TIPO_DOC, t.NOMBRE_DOC, c.FECHA_INICIO, c.FECHA_LIM, c.COMENTARIO FROM CALENDARIO_ESTADIAS c INNER JOIN TIPO_DOC t ON c.ID_TIPO_DOC = t.ID_TIPO_DOC WHERE c.ID_PERIODO = ?";

            try (Connection conexion = Conexion.getConexion();
                 PreparedStatement prs = conexion.prepareStatement(sql)) {
                prs.setInt(1, idPeriodo);
                try (ResultSet rs = prs.executeQuery()) {
                    while (rs.next()) {
                        CalendarioBean calendario = new CalendarioBean();
                        calendario.setIdCalendario(rs.getInt("id_calendario"));
                        calendario.setId_periodo(rs.getInt("id_periodo"));
                        calendario.setTipo_doc(rs.getInt("id_tipo_doc"));
                        calendario.setNombreDoc(rs.getString("nombre_doc"));
                        calendario.setFechaInicio(rs.getDate("fecha_inicio"));
                        calendario.setFechaLimite(rs.getDate("fecha_lim"));

                        listaCalendarios.add(calendario);
                    }

                }

            } catch (SQLException e) {
                System.err.println("Error calendario no encontrado: " + e.getMessage());
                e.printStackTrace();
            }
            return listaCalendarios;
        }
        public boolean existeCalendarioEnPeriodo(int idPeriodo, int idTipoDoc) {
            String sql = "SELECT COUNT(*) FROM calendario_estadias WHERE id_periodo = ? AND id_tipo_doc = ?";

            try (Connection con = Conexion.getConexion();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idPeriodo);
                ps.setInt(2, idTipoDoc);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0;
                    }
                }
            } catch (SQLException e) {
                System.err.println("Error al validar existencia de calendario: " + e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
        public boolean registrarCalendario(CalendarioBean calendario) {
            String sql = "INSERT INTO CALENDARIO_ESTADIAS (ID_PERIODO, ID_TIPO_DOC, COMENTARIO, FECHA_INICIO, FECHA_LIM, VISIBLE) " +
                    "VALUES (?, ?, ?, ?, ?, 1)";

            try (Connection conexion = Conexion.getConexion();
                 PreparedStatement prs = conexion.prepareStatement(sql)) {

                prs.setInt(1, calendario.getId_periodo());
                prs.setInt(2, calendario.getTipo_doc());
                prs.setString(3, calendario.getComentario());
                prs.setDate(4, calendario.getFechaInicio());
                prs.setDate(5, calendario.getFechaLimite());

                int filasAfectadas = prs.executeUpdate();

                return filasAfectadas > 0;

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        public boolean actualizarCalendario(CalendarioBean calendario) {

            String sql="UPDATE CALENDARIO_ESTADIAS SET ID_TIPO_DOC=?,COMENTARIO=?,FECHA_INICIO=?, FECHA_LIM=? WHERE ID_CALENDARIO=?";

            try(Connection conexion =Conexion.getConexion();
            PreparedStatement prs =conexion.prepareStatement(sql)){
                prs.setInt(1,calendario.getTipo_doc());
                prs.setString(2,calendario.getComentario());
                prs.setDate(3,calendario.getFechaInicio());
                prs.setDate(4,calendario.getFechaLimite());
                prs.setInt(5,calendario.getIdCalendario());
                int filasAfectadas = prs.executeUpdate();
                return filasAfectadas > 0;

            } catch (SQLException e) {
                System.err.println("Error al actualizar calendario: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
}
