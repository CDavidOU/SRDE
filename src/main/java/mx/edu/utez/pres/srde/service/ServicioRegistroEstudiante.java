package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoRegistroEstudiante;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

public class ServicioRegistroEstudiante {

    public BeanEstudiante registrarEstudiante(BeanEstudiante nuevoEstudiante) {
        DaoRegistroEstudiante daoRegistroEst = new DaoRegistroEstudiante();

        return daoRegistroEst.registrarEstudiante(nuevoEstudiante);
    }
}
