package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoAdminEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

import java.util.List;
public class ServicioAdminEstudiantes {

    private final DaoAdminEstudiantes dao = new DaoAdminEstudiantes();

    public List<BeanEstudiante> listaEstudiantes(int idPeriodo) {
        return dao.listaEstudiantes(idPeriodo);
    }

    public List<BeanEstudiante> buscarEstudiantes(int idPeriodo, String condicion) {
        return dao.buscarEstudiantes(idPeriodo, condicion);
    }
}
