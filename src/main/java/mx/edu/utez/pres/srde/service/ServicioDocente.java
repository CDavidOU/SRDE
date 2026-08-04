package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;

import java.util.List;

public class ServicioDocente {

    public static final String PASSWORD_TEMPORAL_DEFAULT = "Utez2026";

    public BeanDocente datosDocente(int id){
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }

    public List<BeanDocente> listaDocentes(int idPeriodo) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.listaDocentes(idPeriodo);
    }

    public BeanDocente registrarDocente(BeanDocente nuevoDocente) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.registrarDocente(nuevoDocente, PASSWORD_TEMPORAL_DEFAULT);
    }
}
