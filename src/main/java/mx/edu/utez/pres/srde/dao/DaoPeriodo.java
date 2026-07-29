package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.*;

public class DaoPeriodo {
    public BeanPeriodo buscarPeriodo(String periodo){
        String sql ="select * from periodo where nombre_periodo=?";
        try(Connection conexion= Conexion.getConexion();
            PreparedStatement prs= conexion.prepareStatement(sql)){
            prs.setString(1,periodo);
            try (ResultSet rs=prs.executeQuery()){
                if(rs.next()){
                    BeanPeriodo periodoEncontrado=new BeanPeriodo();
                    periodoEncontrado.setId_periodo(rs.getInt("id_periodo"));
                    periodoEncontrado.setNombre_periodo(rs.getString("nombre_periodo"));
                    periodoEncontrado.setFecha_inicio(rs.getDate("fecha_inicio"));
                    periodoEncontrado.setFecha_fin(rs.getDate("fecha_fin"));
                    return periodoEncontrado;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BeanPeriodo registrarNuevoPeriodo(BeanPeriodo periodo){
        BeanPeriodo nuevoPeriodo=null;
        String sql="insert into periodo (nombre_periodo,fecha_inicio,fecha_fin) values(?,?,?)";
        try(Connection conexion = Conexion.getConexion();
        PreparedStatement prs=conexion.prepareStatement(sql)){
            prs.setString(1,periodo.getNombre_periodo());
            prs.setDate(2,periodo.getFecha_inicio());
            prs.setDate(3,periodo.getFecha_fin());
            int filasAfectadas = prs.executeUpdate();

            if (filasAfectadas > 0) {
                return buscarPeriodo(periodo.getNombre_periodo());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
