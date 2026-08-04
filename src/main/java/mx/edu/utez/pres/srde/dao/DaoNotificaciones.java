package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificaciones {

    // Documentos pendientes de los estudiantes asignados a un docente, con detalle
    public List<BeanNotificacion> listarPendientesDocente(int idDocente) {
        List<BeanNotificacion> lista = new ArrayList<>();
        String sql = "SELECT e.matricula, e.nombre, e.apellido, td.nombre_doc, cd.id_asignacion, cd.id_tipo_doc " +
                "FROM CONTROL_DOC cd " +
                "INNER JOIN ASIGNACION_ESTADIAS ae ON cd.ID_ASIGNACION = ae.ID_ASIGNACION " +
                "INNER JOIN ESTUDIANTE e ON ae.MATRICULA = e.MATRICULA " +
                "INNER JOIN TIPO_DOC td ON cd.ID_TIPO_DOC = td.ID_TIPO_DOC " +
                "WHERE ae.ID_USUARIO_DOCENTE = ? AND cd.ESTADO = 'Pendiente' " +
                "ORDER BY e.nombre, e.apellido";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idDocente);
            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearNotificacion(rs, false));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pendientes del docente: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Documentos pendientes de todo el sistema, con el docente responsable (vista Admin)
    public List<BeanNotificacion> listarPendientesGlobal() {
        List<BeanNotificacion> lista = new ArrayList<>();
        String sql = "SELECT e.matricula, e.nombre, e.apellido, td.nombre_doc, cd.id_asignacion, cd.id_tipo_doc, " +
                "d.nombre AS docente_nombre, d.apellido AS docente_apellido " +
                "FROM CONTROL_DOC cd " +
                "INNER JOIN ASIGNACION_ESTADIAS ae ON cd.ID_ASIGNACION = ae.ID_ASIGNACION " +
                "INNER JOIN ESTUDIANTE e ON ae.MATRICULA = e.MATRICULA " +
                "INNER JOIN TIPO_DOC td ON cd.ID_TIPO_DOC = td.ID_TIPO_DOC " +
                "LEFT JOIN DOCENTE d ON ae.ID_USUARIO_DOCENTE = d.ID_USUARIO " +
                "WHERE cd.ESTADO = 'Pendiente' " +
                "ORDER BY d.nombre, e.nombre, e.apellido";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearNotificacion(rs, true));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar pendientes globales: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    private BeanNotificacion mapearNotificacion(ResultSet rs, boolean incluirDocente) throws SQLException {
        BeanNotificacion notificacion = new BeanNotificacion();
        notificacion.setMatricula(rs.getString("matricula"));
        notificacion.setEstudianteNombre(rs.getString("nombre"));
        notificacion.setEstudianteApellido(rs.getString("apellido"));
        notificacion.setNombreDocumento(rs.getString("nombre_doc"));
        notificacion.setIdAsignacion(rs.getInt("id_asignacion"));
        notificacion.setIdTipoDoc(rs.getInt("id_tipo_doc"));
        if (incluirDocente) {
            String docenteNombre = rs.getString("docente_nombre");
            String docenteApellido = rs.getString("docente_apellido");
            notificacion.setDocenteNombre(docenteNombre != null ? docenteNombre + " " + docenteApellido : "Sin asignar");
        }
        return notificacion;
    }

    // Documentos pendientes de los estudiantes asignados a un docente (cualquier periodo)
    public int contarDocumentosPendientesDocente(int idDocente) {
        String sql = "SELECT COUNT(*) FROM CONTROL_DOC cd " +
                "INNER JOIN ASIGNACION_ESTADIAS ae ON cd.ID_ASIGNACION = ae.ID_ASIGNACION " +
                "WHERE ae.ID_USUARIO_DOCENTE = ? AND cd.ESTADO = 'Pendiente'";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idDocente);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar documentos pendientes del docente: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // Documentos pendientes en todo el sistema (vista administrativa)
    public int contarDocumentosPendientesGlobal() {
        String sql = "SELECT COUNT(*) FROM CONTROL_DOC WHERE ESTADO = 'Pendiente'";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al contar documentos pendientes globales: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
