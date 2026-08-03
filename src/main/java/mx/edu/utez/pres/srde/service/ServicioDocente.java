package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;

import java.util.List;

public class ServicioDocente {

    public BeanDocente datosDocente(int id) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }

    public List<BeanDocente> listaDocente() {
        DaoDocente daoListaDocente = new DaoDocente();
        return daoListaDocente.listaDocente();
    }

    public boolean editarDocente(BeanDocente docente) {
        DaoDocente daoEditarDocente = new DaoDocente();
        return daoEditarDocente.editarDocente(docente);
    }
}