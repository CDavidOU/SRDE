package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.model.NotificacionBean;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificaciones {

    // ==========================================
    // MÉTODOS DE LA RAMA CARLOS (Calendario)
    // ==========================================

    public List<CalendarioBean> mostrarCalendariosDocente(int idDocente, int idPeriodo) {
        List<CalendarioBean> listaCalendarios = new ArrayList<>();

        // Cambiamos el COALESCE a la lista de campos SELECT como 'ESTADO_VISTO' y quitamos el filtro WHERE
        String sql = "SELECT c.ID_CALENDARIO, c.ID_PERIODO, c.ID_TIPO_DOC, t.NOMBRE_DOC, c.FECHA_LIM, c.COMENTARIO, c.FECHA_INICIO, COALESCE(nd.VISTO, 1) AS ESTADO_VISTO FROM CALENDARIO_ESTADIAS c INNER JOIN TIPO_DOC t ON c.ID_TIPO_DOC = t.ID_TIPO_DOC LEFT JOIN NOTIFICACION_DOCENTE nd ON c.ID_CALENDARIO = nd.ID_CALENDARIO AND nd.ID_DOCENTE = ? WHERE c.ID_PERIODO = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idDocente);
            prs.setInt(2, idPeriodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    CalendarioBean cal = new CalendarioBean();
                    cal.setIdCalendario(rs.getInt("ID_CALENDARIO"));
                    cal.setId_periodo(rs.getInt("ID_PERIODO"));
                    cal.setTipo_doc(rs.getInt("ID_TIPO_DOC"));
                    cal.setNombreDoc(rs.getString("NOMBRE_DOC"));
                    cal.setFechaLimite(rs.getDate("FECHA_LIM"));
                    cal.setComentario(rs.getString("COMENTARIO"));
                    cal.setFechaInicio(rs.getDate("FECHA_INICIO"));

                    // Guardamos el estado (1 = Visible, 0 = Oculta) en el bean
                    cal.setVisibilidad(rs.getInt("ESTADO_VISTO"));

                    listaCalendarios.add(cal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener calendarios docente: " + e.getMessage());
            e.printStackTrace();
        }
        return listaCalendarios;
    }

    public boolean desocultarNotificacion(NotificacionBean noti) {
        String sqlUpdate = "UPDATE NOTIFICACION_DOCENTE SET VISTO = 1, FECHA_LECTURA = SYSDATE WHERE ID_CALENDARIO = ? AND ID_DOCENTE = ?";
        String sqlInsert = "INSERT INTO NOTIFICACION_DOCENTE (ID_CALENDARIO, ID_DOCENTE, VISTO, FECHA_LECTURA) VALUES (?, ?, 1, SYSDATE)";

        try (Connection conexion = Conexion.getConexion()) {
            try (PreparedStatement prsUpdate = conexion.prepareStatement(sqlUpdate)) {
                prsUpdate.setInt(1, noti.getIdCalendario());
                prsUpdate.setInt(2, noti.getId_docente());
                int filasAfectadas = prsUpdate.executeUpdate();
                if (filasAfectadas > 0) {
                    return true;
                }
            }
            try (PreparedStatement prsInsert = conexion.prepareStatement(sqlInsert)) {
                prsInsert.setInt(1, noti.getIdCalendario());
                prsInsert.setInt(2, noti.getId_docente());
                return prsInsert.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ocultarNotificacion(NotificacionBean notif) {
        String sqlUpdate = "UPDATE NOTIFICACION_DOCENTE SET VISTO = 0, FECHA_LECTURA = CURRENT_TIMESTAMP WHERE ID_DOCENTE = ? AND ID_CALENDARIO = ?";
        String sqlInsert = "INSERT INTO NOTIFICACION_DOCENTE (ID_DOCENTE, ID_CALENDARIO, VISTO, FECHA_LECTURA) VALUES (?, ?, 0, CURRENT_TIMESTAMP)";

        try (Connection conexion = Conexion.getConexion()) {

            // 1. Intentamos actualizar si ya existía la fila
            try (PreparedStatement prsUpdate = conexion.prepareStatement(sqlUpdate)) {
                prsUpdate.setInt(1, notif.getId_docente());
                prsUpdate.setInt(2, notif.getIdCalendario());
                if (prsUpdate.executeUpdate() > 0) {
                    return true;
                }
            }
            try (PreparedStatement prsInsert = conexion.prepareStatement(sqlInsert)) {
                prsInsert.setInt(1, notif.getId_docente());
                prsInsert.setInt(2, notif.getIdCalendario());
                return prsInsert.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al ocultar notificación: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // ==========================================
    // MÉTODOS DE LA RAMA UNION (Documentos Pendientes)
    // ==========================================

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

    // ==========================================
    // MÉTODO PARA INSERTAR NOTIFICACIÓN EN BD
    // ==========================================
    public boolean crearNotificacion(BeanNotificacion noti) {
        boolean registrado = false;

        // IMPORTANTE: Verifica que los nombres de la tabla y las columnas
        // coincidan exactamente con el script de tu base de datos
        String sql = "INSERT INTO NOTIFICACION (DESCRIPCION, FECHA_LIMITE, TIPO_DOC, ID_PERIODO, ID_DOCENTE) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, noti.getDescripcion());
            pstm.setDate(2, noti.getFechaLimite());
            pstm.setInt(3, noti.getTipo_doc());
            pstm.setInt(4, noti.getId_periodo());
            pstm.setInt(5, noti.getId_usuario_docente());

            registrado = pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al crear notificación: " + e.getMessage());
            e.printStackTrace();
        }
        return registrado;
    }
}