package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoDocente {

    public BeanDocente datosDocente(int id){
        BeanDocente datosDocente =null;

        String sql="SELECT u.id_usuario, d.nombre, d.apellido, d.carrera, d.telefono, d.academia, u.correo FROM docente d INNER JOIN usuario u ON d.id_usuario = u.id_usuario WHERE u.id_usuario = ?";
        try(Connection conexion= Conexion.getConexion();
            PreparedStatement prs=conexion.prepareStatement(sql);) {
            prs.setInt(1, id);
            try(ResultSet rs=prs.executeQuery();) {
                if(rs.next()){
                    datosDocente=new BeanDocente();
                    datosDocente.setId(rs.getInt("id_usuario"));
                    datosDocente.setNombre(rs.getString("nombre"));
                    datosDocente.setApellido(rs.getString("apellido"));
                    datosDocente.setCorreo(rs.getString("correo"));
                    datosDocente.setCarrera(rs.getString("carrera"));
                    datosDocente.setTelefono(rs.getString("telefono"));
                    datosDocente.setAcademia(rs.getString("academia"));
                }
            }
        } catch (SQLException e) {
            ; e.printStackTrace();
        }
        return datosDocente;
    }
}
