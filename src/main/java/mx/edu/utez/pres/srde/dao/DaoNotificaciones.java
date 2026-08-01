package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoNotificaciones {
    public BeanNotificacion notificacionCreada(BeanNotificacion beanNotificacion) {
        String sql="insert into calendario_estadias (id_periodo, id_usuario, comentario,id_tipo_doc,fecha_limite) values (?,?,?,?,?)";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)){

            prs.setInt(1,beanNotificacion.getId_periodo());
            prs.setInt(2,beanNotificacion.getId_usuario_docente());
            prs.setString(3,beanNotificacion.getDescripcion());
            prs.setInt(4,beanNotificacion.getTipo_doc());
            prs.setDate(5,beanNotificacion.getFechaLimite());
            int filasAfectadas = prs.executeUpdate();
            if(filasAfectadas>0){
                return beanNotificacion;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
