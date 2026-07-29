package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoListaEstudiantes;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;

import java.util.List;

public class ServicioListaEstudiantes {
    public List<BeanAsignacionEstadias> listaEstudiantes(int id_docente,int id_periodo){
        DaoListaEstudiantes daoListaEstudiantes = new DaoListaEstudiantes();
        List<BeanAsignacionEstadias> listaEstudiantesActivos=daoListaEstudiantes.listaEstudiantes(id_docente,id_periodo);
        return listaEstudiantesActivos;
    }
}
