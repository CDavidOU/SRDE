package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoAdmin {

    public BeanAdmin datosAdmin(int idAdmin) {

        BeanAdmin datosAdmin = null;

        String sql = "SELECT u.id_usuario, a.nombre, a.apellido, u.correo, a.telefono FROM admin_datos a INNER JOIN usuario u ON a.id_usuario = u.id_usuario WHERE u.id_usuario = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {
            prs.setInt(1,idAdmin);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    datosAdmin = new BeanAdmin();
                    datosAdmin.setId(rs.getInt("id_usuario"));
                    datosAdmin.setNombre(rs.getString("nombre"));
                    datosAdmin.setApellido(rs.getString("apellido"));
                    datosAdmin.setCorreo(rs.getString("correo"));
                    datosAdmin.setTelefono(rs.getString("telefono"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datosAdmin;}
}
