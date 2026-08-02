package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoAsignacionEstadias {
    public int contarEstudiantesAsignados (int id_usuario_docente, int periodo){
        String sql="select count(*) from asignacion_estadias where id_usuario_docente=? and id_periodo=?";

        try(Connection conexion=Conexion.getConexion();
        PreparedStatement prs= conexion.prepareStatement(sql)){
            prs.setInt(1,id_usuario_docente);
            prs.setInt(2,periodo);

            try (ResultSet rs=prs.executeQuery()){
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int registroAsignacionEstadias(BeanAsignacionEstadias asignacion) {
        int idGenerado = 0;
        // Le indicamos a Oracle que queremos recuperar la columna ID_ASIGNACION
        String sql = "INSERT INTO ASIGNACION_ESTADIAS (ID_PERIODO, MATRICULA, ID_USUARIO_DOCENTE) VALUES (?, ?, ?)";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql, new String[]{"ID_ASIGNACION"})) {

            prs.setInt(1, asignacion.getId_periodo());
            prs.setString(2, asignacion.getMatricula());
            prs.setInt(3, asignacion.getId_docente());

            int filasInsertadas = prs.executeUpdate();

            if (filasInsertadas > 0) {
                // Recuperamos la llave generada por la base de datos
                try (ResultSet rs = prs.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1); // Guardamos el ID de la asignación
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar asignación de estadías");
            e.printStackTrace();
        }

        return idGenerado; // Retorna el ID generado (o 0 si falló)
    }
}
