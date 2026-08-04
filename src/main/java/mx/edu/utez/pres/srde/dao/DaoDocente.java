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

    // Lista de docentes con el conteo de alumnos asignados en el periodo actual
    public List<BeanDocente> listaDocentes(int idPeriodo) {
        List<BeanDocente> listaDocentes = new ArrayList<>();

        String sql = "SELECT u.id_usuario, d.nombre, d.apellido, d.carrera, d.telefono, d.academia, u.correo, " +
                "(SELECT COUNT(*) FROM asignacion_estadias ae WHERE ae.id_usuario_docente = u.id_usuario AND ae.id_periodo = ?) AS num_alumnos " +
                "FROM docente d INNER JOIN usuario u ON d.id_usuario = u.id_usuario " +
                "ORDER BY d.nombre, d.apellido";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idPeriodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanDocente docente = new BeanDocente();
                    docente.setId(rs.getInt("id_usuario"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setApellido(rs.getString("apellido"));
                    docente.setCorreo(rs.getString("correo"));
                    docente.setCarrera(rs.getString("carrera"));
                    docente.setTelefono(rs.getString("telefono"));
                    docente.setAcademia(rs.getString("academia"));
                    docente.setNumAlumnos(rs.getInt("num_alumnos"));
                    listaDocentes.add(docente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar docentes: " + e.getMessage());
            e.printStackTrace();
        }
        return listaDocentes;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM USUARIO WHERE CORREO = ?";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, correo);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Registra el usuario (login) y los datos del docente en una sola transacción
    public BeanDocente registrarDocente(BeanDocente nuevoDocente, String passwordTemporal) {
        if (existeCorreo(nuevoDocente.getCorreo())) {
            System.out.println("El correo " + nuevoDocente.getCorreo() + " ya está registrado.");
            return null;
        }

        String sqlUsuario = "INSERT INTO USUARIO (CORREO, CONTRASENA, ROL) VALUES (?, STANDARD_HASH(?, 'SHA256'), 'Docente')";
        String sqlDocente = "INSERT INTO DOCENTE (ID_USUARIO, NOMBRE, APELLIDO, CARRERA, TELEFONO, ACADEMIA, ESTADO) VALUES (?, ?, ?, ?, ?, ?, 'activo')";

        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            int idUsuarioGenerado = 0;
            try (PreparedStatement psUsuario = con.prepareStatement(sqlUsuario, new String[]{"ID_USUARIO"})) {
                psUsuario.setString(1, nuevoDocente.getCorreo());
                psUsuario.setString(2, passwordTemporal);
                psUsuario.executeUpdate();

                try (ResultSet rs = psUsuario.getGeneratedKeys()) {
                    if (rs.next()) {
                        idUsuarioGenerado = rs.getInt(1);
                    }
                }
            }

            if (idUsuarioGenerado == 0) {
                con.rollback();
                return null;
            }

            try (PreparedStatement psDocente = con.prepareStatement(sqlDocente)) {
                psDocente.setInt(1, idUsuarioGenerado);
                psDocente.setString(2, nuevoDocente.getNombre());
                psDocente.setString(3, nuevoDocente.getApellido());
                psDocente.setString(4, nuevoDocente.getCarrera());
                psDocente.setString(5, nuevoDocente.getTelefono());
                psDocente.setString(6, nuevoDocente.getAcademia());
                psDocente.executeUpdate();
            }

            con.commit();
            nuevoDocente.setId(idUsuarioGenerado);
            return nuevoDocente;

        } catch (SQLException e) {
            System.err.println("Error al registrar docente: " + e.getMessage());
            e.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return null;
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
