package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<BeanDocente> listaDocente(){
        List<BeanDocente> listaDocente=new ArrayList<>();
        String sqlDocentes="SELECT * FROM docente";
        try (Connection conexion = Conexion.getConexion();
        PreparedStatement prs = conexion.prepareStatement(sqlDocentes);
        ResultSet rs=prs.executeQuery();) {
            while (rs.next()) {
                BeanDocente docente=new BeanDocente();
                prs.setInt(1, rs.getInt("id_usuario"));
                prs.setString(2, rs.getString("nombre"));
                prs.setString(3, rs.getString("apellido"));
                prs.setString(4, rs.getString("correo"));
                prs.setString(5, rs.getString("carrera"));
                prs.setString(6, rs.getString("telefono"));
                prs.setString(7, rs.getString("academia"));
                listaDocente.add(docente);
            }


        }catch (SQLException e) {
            ; e.printStackTrace();
        }
        return listaDocente;
    }
}
