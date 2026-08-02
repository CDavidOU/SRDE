package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoNotificaciones {
    public BeanNotificacion notificacionCreada(BeanNotificacion beanNotificacion) {
        String sql="insert into calendario_estadias (id_periodo, id_usuario, comentario,id_tipo_doc,fecha_lim) values (?,?,?,?,?)";

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

    public List<BeanNotificacion> mostrarNotificaciones (int idDocente){
        List<BeanNotificacion> mostrarNotificaciones = new ArrayList<>();
        String sql="select id_tipo_doc,id_periodo,id_usuario,fecha_lim,comentario from calendario_estadias where ca.id_usuario=?";
        try(Connection conexion = Conexion.getConexion();
        PreparedStatement prs=conexion.prepareStatement(sql)){
            prs.setInt(1,idDocente);
            try(ResultSet rs=prs.executeQuery()){
                while(rs.next()){
                    BeanNotificacion buscarNotificacion=new BeanNotificacion();
                    buscarNotificacion.setTipo_doc(rs.getInt("id_tipo_doc"));
                    buscarNotificacion.setId_periodo(rs.getInt("id_periodo"));
                    buscarNotificacion.setId_usuario_docente(rs.getInt("id_usuario"));
                    buscarNotificacion.setDescripcion(rs.getString("descripcion"));
                    buscarNotificacion.setTipo_doc(rs.getInt("comentario"));

                    mostrarNotificaciones.add(buscarNotificacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostrarNotificaciones;
    }
}
