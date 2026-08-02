package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;

import java.util.List;

public class ServicioDocente {

    public BeanDocente datosDocente(int id){
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }

    public List<BeanDocente> listaDocente(){
        DaoDocente daoListaDocente = new DaoDocente();
        return daoListaDocente.listaDocente();
    }
}
