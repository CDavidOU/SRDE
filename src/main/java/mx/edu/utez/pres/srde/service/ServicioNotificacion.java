package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoNotificaciones;
import mx.edu.utez.pres.srde.model.BeanNotificacion;

import java.util.List;

public class ServicioNotificacion {

    public BeanNotificacion crearNotificacion(BeanNotificacion beanNotificacion) {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();

        return  daoNotificaciones.notificacionCreada(beanNotificacion);
    }
    public List<BeanNotificacion> buscarListaNotificaciones(int idDocente) {
        DaoNotificaciones daoNotificacionesBuscar = new DaoNotificaciones();
        return  daoNotificacionesBuscar.mostrarNotificaciones(idDocente);
    }

    public boolean ocultarNotificacion(int idNotificacion) {
        DaoNotificaciones daoOcultarNotificaciones = new DaoNotificaciones();
        return daoOcultarNotificaciones.ocultarNotificacion(idNotificacion);
    }
}
