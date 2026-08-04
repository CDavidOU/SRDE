package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoAdminEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

import java.util.List;

public class ServicioAdminEstudiantes {

    public List<BeanEstudiante> listaEstudiantes(int idPeriodo) {
        DaoAdminEstudiantes daoAdminEstudiantes = new DaoAdminEstudiantes();
        return daoAdminEstudiantes.listaEstudiantes(idPeriodo);
    }

    public List<BeanEstudiante> buscarEstudiantes(int idPeriodo, String condicion) {
        DaoAdminEstudiantes daoAdminEstudiantes = new DaoAdminEstudiantes();
        return daoAdminEstudiantes.buscarEstudiantes(idPeriodo, condicion);
    }
}
