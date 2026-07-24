package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DaoUsuario {

    public BeanUsuario verificarUsuario(String correo,String password) {
        BeanUsuario usuarioLogueado = null;

        String sql="select correo,contrasena,rol,id_usuario from usuario where correo=? and contrasena=STANDARD_HASH(?, 'SHA256') ";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)){
                prs.setString(1,correo);
                prs.setString(2,password);
                try (ResultSet rs= prs.executeQuery()){
                    if(rs.next()){
                        usuarioLogueado = new BeanUsuario();

                        usuarioLogueado.setCorreo(rs.getString("correo"));
                        usuarioLogueado.setPassword(rs.getString("contrasena"));
                        usuarioLogueado.setRol(rs.getString("rol"));
                        usuarioLogueado.setId(rs.getInt("id_usuario"));
                    }
                }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return usuarioLogueado;

    }
}
