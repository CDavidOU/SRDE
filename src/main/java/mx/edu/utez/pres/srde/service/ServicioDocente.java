package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;

public class ServicioDocente {

    public BeanDocente datosDocente(int id){
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }
}
