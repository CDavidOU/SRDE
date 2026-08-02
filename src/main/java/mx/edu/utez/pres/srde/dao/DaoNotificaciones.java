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
    public BeanNotificacion notificacionCreada(BeanNotificacion beanNotificacion) {
        String sql="insert into calendario_estadias (id_periodo, id_usuario, comentario,id_tipo_doc,fecha_lim,visible) values (?,?,?,?,?,1)";

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
        String sql="select * from calendario_estadias where id_usuario=? AND visible = 1";
        try(Connection conexion = Conexion.getConexion();
        PreparedStatement prs=conexion.prepareStatement(sql)){
            prs.setInt(1,idDocente);
            try(ResultSet rs=prs.executeQuery()){
                while(rs.next()){
                    BeanNotificacion buscarNotificacion=new BeanNotificacion();
                    buscarNotificacion.setIdCalendario(rs.getInt("id_calendario"));
                    buscarNotificacion.setTipo_doc(rs.getInt("id_tipo_doc"));
                    buscarNotificacion.setId_periodo(rs.getInt("id_periodo"));
                    buscarNotificacion.setId_usuario_docente(rs.getInt("id_usuario"));
                    buscarNotificacion.setFechaLimite(rs.getDate("fecha_lim"));
                    buscarNotificacion.setDescripcion(rs.getString("comentario"));

                    mostrarNotificaciones.add(buscarNotificacion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostrarNotificaciones;
    }

    public boolean ocultarNotificacion(int idNotificacion) {
        String sqlOcultar = "UPDATE calendario_estadias SET visible = 0 WHERE id_calendario = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sqlOcultar)) {
            prs.setInt(1, idNotificacion);
            return prs.executeUpdate()> 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
