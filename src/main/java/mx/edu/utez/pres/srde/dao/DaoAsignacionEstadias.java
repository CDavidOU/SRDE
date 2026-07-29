package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoAsignacionEstadias {
    public int contarEstudiantesAsignados (int id_usuario_docente, int periodo){
        String sql="select count(*) from asignacion_estadias where id_usuario_docente=? and id_periodo=? and es.estado='Activo'";

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
    public BeanAsignacionEstadias registroAsignacionEstadias(BeanAsignacionEstadias asignacion) {
        BeanAsignacionEstadias nuevaAsignacion=null;
        String sql="insert into asignacion_estadias(id_periodo,matricula,id_usuario_docente) values(?,?,?)";

        try(Connection conexion= Conexion.getConexion();
            PreparedStatement prs=conexion.prepareStatement(sql)){
            prs.setInt(1,asignacion.getId_periodo());
            prs.setString(2,asignacion.getMatricula());
            prs.setInt(3,asignacion.getId_docente());

            int filasInsertadas = prs.executeUpdate();
            if(filasInsertadas>0){
                nuevaAsignacion= asignacion;
            }
        }catch (SQLException e) {
            e.printStackTrace();
                                }
        return nuevaAsignacion;
    }
}
