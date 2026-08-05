package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;

import java.util.List;

public class ServicioDocente {

    // Constante añadida de la rama union
    public static final String PASSWORD_TEMPORAL_DEFAULT = "Utez2026";

    public BeanDocente datosDocente(int id) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.datosDocente(id);
    }

    // ==========================================
    // Métodos de la rama Carlos
    // ==========================================
    public List<BeanDocente> listaDocente() {
        DaoDocente daoListaDocente = new DaoDocente();
        return daoListaDocente.listaDocente();
    }

    public boolean editarDocente(BeanDocente docente) {
        DaoDocente daoEditarDocente = new DaoDocente();
        return daoEditarDocente.editarDocente(docente);
    }

    // ==========================================
    // Métodos de la rama union
    // ==========================================
    public List<BeanDocente> listaDocentes(int idPeriodo) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.listaDocentes(idPeriodo);
    }

    public BeanDocente registrarDocente(BeanDocente nuevoDocente) {
        DaoDocente daoDocente = new DaoDocente();
        return daoDocente.registrarDocente(nuevoDocente, PASSWORD_TEMPORAL_DEFAULT);
    }
}