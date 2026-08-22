package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;

import java.util.ArrayList;
import java.util.List;
public class ServicioDocente {


    public BeanDocente datosDocente(int id) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }

    // Método existente en tu rama
    public List<BeanDocente> listaDocente() {
        DaoDocente daoListaDocente = new DaoDocente();
        return daoListaDocente.listaDocente();
    }

    // NUEVO MÉTODO: Reutiliza tu listaDocente() y filtra solo los activos para la vista
    public List<BeanDocente> obtenerDocentesActivos() {
        List<BeanDocente> todos = listaDocente();
        List<BeanDocente> activos = new ArrayList<>();

        for (BeanDocente doc : todos) {
            if (doc.getEstado() != null && doc.getEstado().equalsIgnoreCase("activo")) {
                activos.add(doc);
            }
        }
        return activos;
    }

    public boolean editarDocente(BeanDocente docente) {
        DaoDocente daoEditarDocente = new DaoDocente();
        return daoEditarDocente.editarDocente(docente);
    }

    public List<BeanDocente> listaDocentes(int idPeriodo) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.listaDocentes(idPeriodo);
    }

    public List<BeanDocente> buscarDocentes(int idPeriodo, String buscador) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.buscarDocentes(idPeriodo, buscador);
    }

    public List<BeanDocente> buscarDocentes(int idPeriodo, String buscador) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.buscarDocentes(idPeriodo, buscador);
    }
}