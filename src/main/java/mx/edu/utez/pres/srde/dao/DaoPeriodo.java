package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPeriodo {
    public BeanPeriodo buscarPeriodo(String periodo){
        String sql ="SELECT id_periodo, nombre_periodo, fecha_inicio, fecha_fin FROM periodo WHERE UPPER(nombre_periodo) = UPPER(?)";;
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

    public List<BeanPeriodo> obtenerPeriodosPorDocente(int idDocente) {
        List<BeanPeriodo> listaPeriodos = new ArrayList<>();
        String sql = "SELECT DISTINCT p.id_periodo, p.nombre_periodo, p.fecha_inicio, p.fecha_fin " +
                "FROM asignacion_estadias aes " +
                "INNER JOIN periodo p ON aes.id_periodo = p.id_periodo " +
                "WHERE aes.id_usuario_docente = ? " +
                "ORDER BY p.fecha_inicio DESC";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idDocente);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanPeriodo periodo = new BeanPeriodo();
                    periodo.setId_periodo(rs.getInt("id_periodo"));
                    periodo.setNombre_periodo(rs.getString("nombre_periodo"));
                    periodo.setFecha_inicio(rs.getDate("fecha_inicio"));
                    periodo.setFecha_fin(rs.getDate("fecha_fin"));
                    listaPeriodos.add(periodo);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar periodos del docente: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPeriodos;
    }

    // Todos los periodos registrados (uso administrativo, no filtra por docente)
    public List<BeanPeriodo> listarTodosPeriodos() {
        List<BeanPeriodo> listaPeriodos = new ArrayList<>();
        String sql = "SELECT id_periodo, nombre_periodo, fecha_inicio, fecha_fin FROM periodo ORDER BY fecha_inicio DESC";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {

            while (rs.next()) {
                BeanPeriodo periodo = new BeanPeriodo();
                periodo.setId_periodo(rs.getInt("id_periodo"));
                periodo.setNombre_periodo(rs.getString("nombre_periodo"));
                periodo.setFecha_inicio(rs.getDate("fecha_inicio"));
                periodo.setFecha_fin(rs.getDate("fecha_fin"));
                listaPeriodos.add(periodo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todos los periodos: " + e.getMessage());
            e.printStackTrace();
        }
        return listaPeriodos;
    }

    // Cuenta cuántos estudiantes (de todos los docentes) están asignados en un periodo
    public int contarEstudiantesPorPeriodo(int idPeriodo) {
        String sql = "SELECT COUNT(*) FROM asignacion_estadias WHERE id_periodo = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idPeriodo);
            try (ResultSet rs = prs.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar estudiantes del periodo: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public List<BeanEstudiante> consultarEstudiantePeriodo (int idDocente, int idPeriodo ){
        List<BeanEstudiante> lista = new ArrayList<>();
        String sql = "SELECT e.matricula, e.nombre, e.apellido " +
                "FROM asignacion_estadias aes " +
                "INNER JOIN estudiante e ON aes.matricula = e.matricula " +
                "WHERE aes.id_usuario_docente = ? AND aes.id_periodo = ?";

        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql)) {

                prs.setInt(1, idDocente);
                prs.setInt(2, idPeriodo);

                try (ResultSet rs = prs.executeQuery()){
                    while (rs.next()){
                        BeanEstudiante e = new BeanEstudiante();
                        e.setMatricula(rs.getString("matricula"));
                        e.setNombre(rs.getString("nombre"));
                        e.setApellido(rs.getString("apellido"));
                        lista.add(e);
                    }
                }
            }catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }
    public List<BeanPeriodo> consultarTodosLosPeriodos() {
        List<BeanPeriodo> lista = new ArrayList<>();
        String sql = "SELECT id_periodo, nombre_periodo, fecha_inicio, fecha_fin FROM periodo ORDER BY fecha_inicio DESC";
        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql);
             ResultSet rs = prs.executeQuery()) {
            while (rs.next()) {
                BeanPeriodo p = new BeanPeriodo();
                p.setId_periodo(rs.getInt("id_periodo"));
                p.setNombre_periodo(rs.getString("nombre_periodo"));
                p.setFecha_inicio(rs.getDate("fecha_inicio"));
                p.setFecha_fin(rs.getDate("fecha_fin"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public List<BeanEstudiante> consultarTodosLosEstudiantesPorPeriodo(int idPeriodo) {
        List<BeanEstudiante> lista = new ArrayList<>();
        String sql = "SELECT e.matricula, e.nombre, e.apellido " +
                "FROM asignacion_estadias aes " +
                "INNER JOIN estudiante e ON aes.matricula = e.matricula " +
                "WHERE aes.id_periodo = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setInt(1, idPeriodo);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanEstudiante e = new BeanEstudiante();
                    e.setMatricula(rs.getString("matricula"));
                    e.setNombre(rs.getString("nombre"));
                    e.setApellido(rs.getString("apellido"));
                    lista.add(e);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public List<BeanPeriodo> obtenerPeriodosPorEstudiante(String matricula) {
        List<BeanPeriodo> lista = new ArrayList<>();
        String sql = "SELECT DISTINCT p.id_periodo, p.nombre_periodo, p.fecha_inicio, p.fecha_fin FROM asignacion_estadias aes INNER JOIN periodo p ON aes.id_periodo = p.id_periodo WHERE aes.matricula = ? ORDER BY p.fecha_inicio DESC";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement prs = conexion.prepareStatement(sql)) {

            prs.setString(1, matricula);

            try (ResultSet rs = prs.executeQuery()) {
                while (rs.next()) {
                    BeanPeriodo p = new BeanPeriodo();
                    p.setId_periodo(rs.getInt("id_periodo"));
                    p.setNombre_periodo(rs.getString("nombre_periodo"));
                    p.setFecha_inicio(rs.getDate("fecha_inicio"));
                    p.setFecha_fin(rs.getDate("fecha_fin"));

                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener periodos del estudiante: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}
