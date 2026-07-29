package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoListaEstudiantes {
    public List<BeanAsignacionEstadias> listaEstudiantes(int id_docente, int id_periodo){
        List<BeanAsignacionEstadias> listaEstudiantes=new ArrayList<>();
        String sql="select aes.id_usuario_docente,aes.id_periodo,es.nombre,es.matricula from asignacion_estadias aes inner join estudiante es on aes.matricula= es.matricula where aes.id_usuario_docente=? and aes.id_periodo=? and es.estado='Activo'";
        try (Connection conexion= Conexion.getConexion();
             PreparedStatement prs=conexion.prepareStatement(sql)){
            prs.setInt(1,id_docente);
            prs.setInt(2,id_periodo);
            try (ResultSet rs=prs.executeQuery()){
                while (rs.next()){
                    BeanAsignacionEstadias asignacion=new BeanAsignacionEstadias();
                    asignacion.setId_docente(rs.getInt("id_usuario_docente"));
                    asignacion.setId_periodo(rs.getInt("id_periodo"));

                    BeanEstudiante estudiante=new BeanEstudiante();
                    estudiante.setNombre(rs.getString("nombre"));
                    estudiante.setMatricula(rs.getString("matricula"));

                    asignacion.setMatricula(estudiante.getMatricula());
                    asignacion.setEstudiante(estudiante);

                    listaEstudiantes.add(asignacion);
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listaEstudiantes;
    }
}
