package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoGrafica;
import mx.edu.utez.pres.srde.model.BeanEstadistica;

import java.util.List;

public class ServicioGrafica {

    public int contarEstudiantesActivos() {
        DaoGrafica daoGrafica = new DaoGrafica();
        return daoGrafica.contarEstudiantesActivos();
    }

    public int contarDocentes() {
        DaoGrafica daoGrafica = new DaoGrafica();
        return daoGrafica.contarDocentes();
    }

    public List<BeanEstadistica> estudiantesPorCarrera() {
        DaoGrafica daoGrafica = new DaoGrafica();
        return daoGrafica.estudiantesPorCarrera();
    }
}
