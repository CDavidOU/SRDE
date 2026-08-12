package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanArchivo;
import mx.edu.utez.pres.srde.model.BeanTipoDocumento;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoArchivo {

    public int registrarDocumento(BeanArchivo archivo) {
        // Retornaremos el ID generado. Si retorna 0, significa que falló.
        int idGenerado = 0;
        String sql = "INSERT INTO ARCHIVO (ARCHIVO, NOMBRE_ORIGINAL_ARCHIVO, TAMANO) values (?, ?, ?)";

        try (Connection conexion = Conexion.getConexion();
             // Le indicamos a Oracle que queremos recuperar la columna ID_ARCHIVO
             PreparedStatement prs = conexion.prepareStatement(sql, new String[]{"ID_ARCHIVO"})) {

            prs.setBinaryStream(1, archivo.getContenido_achivo(), archivo.getTamano());
            prs.setString(2, archivo.getNombre_archivo());
            prs.setLong(3, archivo.getTamano());

            int filasInsertadas = prs.executeUpdate();

            if (filasInsertadas > 0) {
                // Recuperamos las llaves generadas
                try (java.sql.ResultSet rs = prs.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1); // Guardamos el ID que creó Oracle
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar el documento en DaoArchivo");
            e.printStackTrace();
        }

        return idGenerado;
    }

    public boolean registrarDetallesDocumento(int idAsignacion, int idTipoDoc, int idArchivoGenerado, String estado, String observaciones, int idUsuarioModificador) {

        String sql = "UPDATE CONTROL_DOC SET ID_ARCHIVO = ?, ESTADO = ?, OBSERVACIONES = ?, MODIFICADO_POR = ? WHERE ID_ASIGNACION = ? AND ID_TIPO_DOC = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idArchivoGenerado); // El ID que se generó en la tabla ARCHIVO
            prs.setString(2, estado);
            prs.setString(3, observaciones);
            prs.setInt(4, idUsuarioModificador);
            prs.setInt(5, idAsignacion);
            prs.setInt(6, idTipoDoc);

            int filas = prs.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar detalles del documento en CONTROL_DOC");
            e.printStackTrace();
        }
        return false;
    }

    public boolean modificarObservaciones(int idAsignacion, int idTipoDoc, String observaciones, int idUsuarioModificador) {
        // Un UPDATE enfocado solo en guardar lo que el docente escribió en la caja de texto
        String sql = "UPDATE CONTROL_DOC SET OBSERVACIONES = ?, MODIFICADO_POR = ? WHERE ID_ASIGNACION = ? AND ID_TIPO_DOC = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, observaciones);
            prs.setInt(2, idUsuarioModificador);
            prs.setInt(3, idAsignacion);
            prs.setInt(4, idTipoDoc);

            return prs.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al modificar las observaciones en CONTROL_DOC");
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarDocumento(int idAsignacion, int idTipoDoc, int idUsuarioModificador) {
        // En lugar de DELETE, hacemos un UPDATE para "limpiar" el registro y regresarlo a Pendiente
        String sql = "UPDATE CONTROL_DOC SET ID_ARCHIVO = NULL, ESTADO = 'Pendiente', OBSERVACIONES = NULL, MODIFICADO_POR = ? WHERE ID_ASIGNACION = ? AND ID_TIPO_DOC = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idUsuarioModificador);
            prs.setInt(2, idAsignacion);
            prs.setInt(3, idTipoDoc);

            return prs.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al 'eliminar' (resetear) el documento en CONTROL_DOC");
            e.printStackTrace();
        }
        return false;
    }


    public List<BeanArchivo> consultarDocumentosPorMatricula(String matricula) {
        List<BeanArchivo> listaDocumentos = new ArrayList<>();

        String sql = "SELECT td.ID_TIPO_DOC, td.NOMBRE_DOC, cd.ESTADO, cd.OBSERVACIONES, cd.REVISADO, ar.ID_ARCHIVO, cal.FECHA_LIM FROM ASIGNACION_ESTADIAS ae INNER JOIN CONTROL_DOC cd ON ae.ID_ASIGNACION = cd.ID_ASIGNACION INNER JOIN TIPO_DOC td ON cd.ID_TIPO_DOC = td.ID_TIPO_DOC LEFT JOIN ARCHIVO ar ON cd.ID_ARCHIVO = ar.ID_ARCHIVO LEFT JOIN CALENDARIO_ESTADIAS cal ON cal.ID_TIPO_DOC = cd.ID_TIPO_DOC AND cal.ID_PERIODO = ae.ID_PERIODO WHERE ae.MATRICULA = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, matricula);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BeanArchivo doc = new BeanArchivo();

                    doc.setId_tipo_doc(rs.getInt("ID_TIPO_DOC"));
                    doc.setNombre_archivo(rs.getString("NOMBRE_DOC"));
                    doc.setEstado(rs.getString("ESTADO"));

                    String obs = rs.getString("OBSERVACIONES");
                    doc.setObservaciones(obs != null ? obs : "Sin observaciones");

                    int idArchivo = rs.getInt("ID_ARCHIVO");
                    if (!rs.wasNull()) {
                        doc.setId_archivo(idArchivo);
                    }

                    doc.setRevisado(rs.getInt("REVISADO") == 1);

                    java.sql.Date fechaLim = rs.getDate("FECHA_LIM");
                    if (fechaLim != null) {
                        doc.setFecha_limite(fechaLim.toString());
                        doc.setTieneCalendario(true);

                        java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());
                        doc.setPuedeSubir(!hoy.after(fechaLim));
                    } else {
                        doc.setFecha_limite("Sin asignar");
                        doc.setTieneCalendario(false);
                        doc.setPuedeSubir(false);
                    }

                    listaDocumentos.add(doc);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar documentos por matrícula: " + e.getMessage());
            e.printStackTrace();
        }

        return listaDocumentos;
    }

    public boolean insertarControlDocPendiente(int idAsignacion, int idTipoDoc, int idDocente) {
        String sql = "INSERT INTO CONTROL_DOC (ID_ASIGNACION, ID_TIPO_DOC, ESTADO, MODIFICADO_POR) VALUES (?, ?, 'Pendiente', ?)";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {
            prs.setInt(1, idAsignacion);
            prs.setInt(2, idTipoDoc);
            prs.setInt(3, idDocente);
            return prs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Integer> obtenerTodosIds() {
        List<Integer> idsTiposDocumento = new ArrayList<>();
        String sql = "SELECT ID_TIPO_DOC FROM TIPO_DOC";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            while (rs.next()) {
                // Extraemos el ID de cada tipo de documento y lo agregamos a la lista
                int idTipoDoc = rs.getInt("ID_TIPO_DOC");
                idsTiposDocumento.add(idTipoDoc);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los IDs de los tipos de documento");
            e.printStackTrace();
        }

        return idsTiposDocumento;
    }

    public BeanArchivo obtenerArchivoPorId(int idArchivo) {
        BeanArchivo archivo = null;
        String sql = "SELECT NOMBRE_ORIGINAL_ARCHIVO, ARCHIVO FROM ARCHIVO WHERE ID_ARCHIVO = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idArchivo);

            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    archivo = new BeanArchivo();
                    archivo.setId_archivo(idArchivo);
                    archivo.setNombre_archivo(rs.getString("NOMBRE_ORIGINAL_ARCHIVO"));

                    // CORRECCIÓN: Extraer los bytes antes de que se cierre la conexión
                    archivo.setArchivoBytes(rs.getBytes("ARCHIVO"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al extraer el archivo en DaoArchivo: " + e.getMessage());
            e.printStackTrace();
        }

        return archivo;
    }

    public boolean marcarComoRevisado(int idAsignacion, int idTipoDoc) {
        String sql = "UPDATE CONTROL_DOC SET REVISADO = 1 WHERE ID_ASIGNACION = ? AND ID_TIPO_DOC = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAsignacion);
            ps.setInt(2, idTipoDoc);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al marcar como revisado: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}