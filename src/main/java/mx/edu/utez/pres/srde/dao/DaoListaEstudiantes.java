package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoListaEstudiantes {

    public List<BeanAsignacionEstadias> listaTodosLosEstudiantes(int idPeriodoActual){
        List<BeanAsignacionEstadias> listaCompleta = new ArrayList<>();
        String sql = "SELECT e.matricula, e.nombre, e.apellido, e.estado, e.carrera, e.correo, e.cuatrimestre, e.grupo, a.id_usuario_docente, a.id_periodo FROM estudiante e LEFT JOIN asignacion_estadias a ON e.matricula = a.matricula AND a.id_periodo = ? ORDER BY e.apellido ASC";
        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)){
            prs.setInt(1, idPeriodoActual);
            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanEstudiante estudianteEncontrado = new BeanEstudiante();
                    estudianteEncontrado.setMatricula(rs.getString("matricula"));
                    estudianteEncontrado.setNombre(rs.getString("nombre"));
                    estudianteEncontrado.setApellido(rs.getString("apellido"));
                    estudianteEncontrado.setEstado(rs.getString("estado"));
                    estudianteEncontrado.setCarrera(rs.getString("carrera"));
                    estudianteEncontrado.setCorreo(rs.getString("correo"));
                    estudianteEncontrado.setCuatrimestre(rs.getInt("cuatrimestre"));
                    estudianteEncontrado.setGrupo(rs.getString("grupo"));

                    BeanAsignacionEstadias asignacion = new BeanAsignacionEstadias();
                    asignacion.setId_docente(rs.getInt("id_usuario_docente"));
                    asignacion.setId_periodo(rs.getInt("id_periodo"));
                    asignacion.setMatricula(rs.getString("matricula"));
                    asignacion.setEstudiante(estudianteEncontrado);

                    listaCompleta.add(asignacion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al generar lista global del Admin: " + e.getMessage());
            e.printStackTrace();
        }
        return listaCompleta;
    }

    public List<BeanAsignacionEstadias> listaEstudiantes(int id_docente, int id_periodo) {
        List<BeanAsignacionEstadias> listaEstudiantes = new ArrayList<>();
        String sql = "SELECT aes.id_usuario_docente, aes.id_periodo, es.nombre, es.matricula, es.apellido " +
                "FROM asignacion_estadias aes " +
                "INNER JOIN estudiante es ON aes.matricula = es.matricula " +
                "WHERE (aes.id_usuario_docente = ? AND aes.id_periodo = ? AND es.estado = 'Activo')";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, id_docente);
            prs.setInt(2, id_periodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanAsignacionEstadias asignacion = new BeanAsignacionEstadias();
                    asignacion.setId_docente(rs.getInt("id_usuario_docente"));
                    asignacion.setId_periodo(rs.getInt("id_periodo"));

                    BeanEstudiante estudiante = new BeanEstudiante();
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setMatricula(rs.getString("matricula"));
                    estudiante.setApellido(rs.getString("apellido"));

                    asignacion.setMatricula(estudiante.getMatricula());
                    asignacion.setEstudiante(estudiante);

                    listaEstudiantes.add(asignacion);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar estudiantes: " + e.getMessage());
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    public List<BeanAsignacionEstadias> buscarEstudiantes (int id_docente, int id_periodo, String condicion){
        List<BeanAsignacionEstadias> listaEstudiantes = new ArrayList<>();

        //Estructura de la sentencia donde pedimos  el id usuairo, pedido, nombre y matricula
        String sql = "SELECT aes.id_usuario_docente, aes.id_periodo, es.nombre, es.apellido, es.matricula " +
                "FROM asignacion_estadias aes " +
                "INNER JOIN estudiante es ON aes.matricula = es.matricula " +
                "WHERE aes.id_usuario_docente = ? " +
                "AND aes.id_periodo = ? " +
                "AND es.estado = 'Activo' " +
                "AND (LOWER(es.nombre) LIKE LOWER(?) OR LOWER(es.apellido) LIKE LOWER(?) OR LOWER(es.matricula) LIKE LOWER(?))";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)) {
            String textoBuscar = "%" + condicion + "%";

                prs.setInt(1, id_docente);
                prs.setInt(2, id_periodo);
                prs.setString(3,textoBuscar);
                prs.setString(4,textoBuscar);
                prs.setString(5,textoBuscar);

                try (ResultSet rs = prs.executeQuery()){
                    while (rs.next()) {
                        BeanAsignacionEstadias asignacion = new BeanAsignacionEstadias();
                        asignacion.setId_docente(rs.getInt("id_usuario_docente"));
                        asignacion.setId_periodo(rs.getInt("id_periodo"));

                        // Mapeo usando BeanEstudiante igual que en listaEstudiantes
                        BeanEstudiante estudiante = new BeanEstudiante();
                        estudiante.setNombre(rs.getString("nombre"));
                        estudiante.setMatricula(rs.getString("matricula"));
                        estudiante.setApellido(rs.getString("apellido"));

                        asignacion.setMatricula(estudiante.getMatricula());
                        asignacion.setEstudiante(estudiante);

                        listaEstudiantes.add(asignacion);
                    }
                }

        }catch (SQLException e){
            System.out.println("Error al buscar al estudiante");
            e.printStackTrace();
        }
        return listaEstudiantes;
    }
    public List<BeanAsignacionEstadias> buscarTodosLosEstudiantesAdmin(int id_periodo, String condicion) {
        List<BeanAsignacionEstadias> listaEstudiantes = new ArrayList<>();

        String sql = "SELECT aes.id_usuario_docente, aes.id_periodo, es.nombre, es.apellido, es.matricula, es.carrera, es.cuatrimestre, es.grupo, es.correo, es.estado FROM asignacion_estadias aes INNER JOIN estudiante es ON aes.matricula = es.matricula WHERE aes.id_periodo = ? AND es.estado = 'Activo' AND (LOWER(es.nombre) LIKE LOWER(?) OR LOWER(es.apellido) LIKE LOWER(?) OR LOWER(es.matricula) LIKE LOWER(?))";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            String textoBuscar = "%" + condicion + "%";
            prs.setInt(1, id_periodo);
            prs.setString(2, textoBuscar);
            prs.setString(3, textoBuscar);
            prs.setString(4, textoBuscar);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanAsignacionEstadias asignacion = new BeanAsignacionEstadias();
                    asignacion.setId_docente(rs.getInt("id_usuario_docente"));
                    asignacion.setId_periodo(rs.getInt("id_periodo"));

                    BeanEstudiante estudiante = new BeanEstudiante();
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setApellido(rs.getString("apellido"));
                    estudiante.setMatricula(rs.getString("matricula"));
                    estudiante.setCarrera(rs.getString("carrera"));
                    estudiante.setCuatrimestre(rs.getInt("cuatrimestre"));
                    estudiante.setGrupo(rs.getString("grupo"));
                    estudiante.setCorreo(rs.getString("correo"));
                    estudiante.setEstado(rs.getString("estado"));

                    asignacion.setMatricula(estudiante.getMatricula());
                    asignacion.setEstudiante(estudiante);

                    listaEstudiantes.add(asignacion);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar estudiantes de forma global para el Admin");
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    //Aqui se borran los estudiantes
    public boolean eliminarEstudianteCompleto(String matricula) {
        String sqlEliminarAsignacion = "DELETE FROM ASIGNACION_ESTADIAS WHERE MATRICULA = ?";
        String sqlEliminarEstudiante = "DELETE FROM ESTUDIANTE WHERE MATRICULA = ?";

        Connection con = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {

            con = Conexion.getConexion();
            con.setAutoCommit(false); // Iniciamos transacción para asegurar que ambos se borren o ninguno

            // 1. Borrar registros de la tabla hija (ASIGNACION_ESTADIAS)
            ps1 = con.prepareStatement(sqlEliminarAsignacion);
            ps1.setString(1, matricula);
            ps1.executeUpdate();

            // 2. Borrar el registro de la tabla padre (ESTUDIANTE)
            ps2 = con.prepareStatement(sqlEliminarEstudiante);
            ps2.setString(1, matricula);
            int filasAfectadas = ps2.executeUpdate();

            con.commit(); // Confirmamos los cambios si ambos SQL salieron bien
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar estudiante completo: " + e.getMessage());
            if (con != null) {
                try {
                    con.rollback(); // Si algo falla, revertimos para no dejar datos corruptos
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            // Cerrar recursos
            try {
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}