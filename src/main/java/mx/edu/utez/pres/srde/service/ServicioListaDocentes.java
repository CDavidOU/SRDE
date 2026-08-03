package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoListaDocentes;
import mx.edu.utez.pres.srde.model.BeanDocente;

import java.util.List;

public class ServicioListaDocentes {

    public List<BeanDocente> listaDocentes(int id_periodo) {
        DaoListaDocentes dao = new DaoListaDocentes();
        return dao.listaDocentes(id_periodo);
    }

    public List<BeanDocente> listaBuscoDocentes(int id_periodo, String buscador) {
        DaoListaDocentes dao = new DaoListaDocentes();
        // Orden corregido: (buscador, id_periodo)
        return dao.buscarDocentes(buscador, id_periodo);
    }
}