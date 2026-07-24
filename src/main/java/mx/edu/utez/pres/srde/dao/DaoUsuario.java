package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUsuario {

    public BeanUsuario verificarUsuario(String correo,String password) {
        BeanUsuario usuarioLogueado = null;

        String sql="select * from usuario where correo=? and contrasena=STANDARD_HASH(?, 'SHA256')";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)){
                prs.setString(1,correo);
                prs.setString(2,password);

                try (ResultSet rs= prs.executeQuery()){
                    if(rs.next()){
                        System.out.println("🔥 ¡LOGRADO! El usuario existe en la BD y entró al IF.");
                        usuarioLogueado = new BeanUsuario();

                        usuarioLogueado.setCorreo(rs.getString("correo"));
                        usuarioLogueado.setPassword(rs.getString("contrasena"));
                    }System.out.println("❌ La BD regresó vacío. Correo o clave con STANDARD_HASH no coincidieron.");
                }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return usuarioLogueado;

    }
}
