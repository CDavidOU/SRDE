package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoRegistroEstudiante {
    public BeanEstudiante registrarEstudiante(BeanEstudiante nuevoEstudiante){
        BeanEstudiante estudianteRegistrado=null;

        String sql="insert into estudiante (matricula,nombre,apellido,carrera,cuatrimestre,estado,correo) values(?,?,?,?,?,'Activo',?)";
        try(Connection conexion= Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)) {
            prs.setString(1, nuevoEstudiante.getMatricula());
            prs.setString(2, nuevoEstudiante.getNombre());
            prs.setString(3, nuevoEstudiante.getApellido());
            prs.setString(4, nuevoEstudiante.getCarrera());
            prs.setInt(5, nuevoEstudiante.getCuatrimestre());
            prs.setString(6, nuevoEstudiante.getCorreo());
            int filasAfectadas = prs.executeUpdate();

            if (filasAfectadas > 0) {
                estudianteRegistrado = nuevoEstudiante;
            }
        } catch (SQLException e) {
            System.out.println("⚠️ ERROR EN EL DAO REGISTRO: " + e.getMessage());
            e.printStackTrace();
        }
        return estudianteRegistrado;
    }
}