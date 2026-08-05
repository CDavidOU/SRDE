package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoNotificaciones;
import mx.edu.utez.pres.srde.model.BeanNotificacion;

import java.util.List;

public class ServicioNotificaciones {

    public int contarDocumentosPendientesDocente(int idDocente) {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();
        return daoNotificaciones.contarDocumentosPendientesDocente(idDocente);
    }

    public int contarDocumentosPendientesGlobal() {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();
        return daoNotificaciones.contarDocumentosPendientesGlobal();
    }

    public List<BeanNotificacion> listarPendientesDocente(int idDocente) {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();
        return daoNotificaciones.listarPendientesDocente(idDocente);
    }

    public List<BeanNotificacion> listarPendientesGlobal() {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();
        return daoNotificaciones.listarPendientesGlobal();
    }
}
