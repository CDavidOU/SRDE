package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;
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

        String sql="SELECT u.id_usuario, d.nombre, d.apellido, d.carrera, d.telefono, d.academia, u.correo, d.estado FROM docente d INNER JOIN usuario u ON d.id_usuario = u.id_usuario WHERE u.id_usuario = ?";
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
                    datosDocente.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            ; e.printStackTrace();
        }
        return datosDocente;
    }

    public List<BeanDocente> listaDocente(){
        List<BeanDocente> listaDocente=new ArrayList<>();
        String sqlDocentes = "SELECT u.id_usuario, d.nombre, d.apellido, d.carrera, d.telefono, d.academia, u.correo,d.estado FROM docente d INNER JOIN usuario u ON d.id_usuario = u.id_usuario";
        try (Connection conexion = Conexion.getConexion();
        PreparedStatement prs = conexion.prepareStatement(sqlDocentes);
        ResultSet rs=prs.executeQuery();) {
            while (rs.next()) {
                BeanDocente docente=new BeanDocente();
                docente.setId(rs.getInt("id_usuario"));
                docente.setNombre(rs.getString("nombre"));
                docente.setApellido(rs.getString("apellido"));
                docente.setCorreo(rs.getString("correo"));
                docente.setCarrera(rs.getString("carrera"));
                docente.setTelefono(rs.getString("telefono"));
                docente.setAcademia(rs.getString("academia"));
                docente.setEstado(rs.getString("estado"));
                listaDocente.add(docente);
            }


        }catch (SQLException e) {
            ; e.printStackTrace();
        }
        return listaDocente;
    }

    public boolean editarDocente(BeanDocente docente){
        String sqlEditar = "UPDATE docente SET nombre = ?, apellido = ?, carrera = ?, academia = ?, telefono = ?, estado=? WHERE id_usuario = ?";
        String sqlUsuario = "UPDATE usuario SET correo = ? WHERE id_usuario = ?";
        try(Connection conexion = Conexion.getConexion();
        PreparedStatement prs =conexion.prepareStatement(sqlEditar);
        PreparedStatement prs2=conexion.prepareStatement(sqlUsuario)){
            prs.setString(1, docente.getNombre());
            prs.setString(2, docente.getApellido());
            prs.setString(3, docente.getCarrera());
            prs.setString(4, docente.getAcademia());
            prs.setString(5, docente.getTelefono());
            prs.setString(6, docente.getEstado());
            prs.setInt(7, docente.getId());
            prs2.setString(1, docente.getCorreo()); // Primer '?' de sqlUsuario
            prs2.setInt(2, docente.getId());
            int filasActualizadas = prs.executeUpdate();
            int filasActualizadasUsuario =prs2.executeUpdate();
            return filasActualizadas > 0 && filasActualizadasUsuario > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
