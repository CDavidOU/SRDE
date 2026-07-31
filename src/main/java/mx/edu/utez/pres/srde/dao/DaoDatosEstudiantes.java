package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDatosEstudiantes {

    public BeanEstudiante datosEstudiante(String matricula){
        BeanEstudiante estudiante = null;
        // Unimos la tabla ESTUDIANTE con ASIGNACION_ESTADIAS para obtener el ID_ASIGNACION
        String sql = "SELECT e.*, a.ID_ASIGNACION FROM ESTUDIANTE e " +
                "LEFT JOIN ASIGNACION_ESTADIAS a ON e.MATRICULA = a.MATRICULA " +
                "WHERE e.MATRICULA = ?";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)){
            prs.setString(1, matricula);
            try (ResultSet rs = prs.executeQuery()){
                if (rs.next()){
                    estudiante = new BeanEstudiante();
                    estudiante.setMatricula(rs.getString("matricula"));
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setCarrera(rs.getString("carrera"));
                    estudiante.setCuatrimestre(rs.getInt("cuatrimestre"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setGrupo(rs.getString("grupo"));
                    estudiante.setEstado(rs.getString("estado"));

                    // ¡Aquí mapeamos el ID de asignación que faltaba!
                    estudiante.setIdAsignacion(rs.getInt("ID_ASIGNACION"));
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }
}