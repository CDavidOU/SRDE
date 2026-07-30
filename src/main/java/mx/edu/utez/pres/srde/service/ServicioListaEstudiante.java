package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoListaEstudiantes;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;

import java.util.List;

public class ServicioListaEstudiante {

    // Método con 3 parámetros para soportar la búsqueda
    public List<BeanAsignacionEstadias> listaBuscoEstudiantes(int id_docente, int id_periodo, String buscador) {
        DaoListaEstudiantes dao = new DaoListaEstudiantes();
        return dao.buscarEstudiantes(id_docente, id_periodo, buscador);
    }

    // Método sobrecargado original (por si lo usas en otro Servlet sin búsqueda)
    public List<BeanAsignacionEstadias> listaEstudiantes(int id_docente, int id_periodo) {
        DaoListaEstudiantes lista = new DaoListaEstudiantes();
        return lista.listaEstudiantes(id_docente, id_periodo);
    }
}