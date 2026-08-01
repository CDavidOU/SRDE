package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoNotificaciones;
import mx.edu.utez.pres.srde.model.BeanNotificacion;

public class ServicioNotificacion {

    public BeanNotificacion crearNotificacion(BeanNotificacion beanNotificacion) {
        DaoNotificaciones daoNotificaciones = new DaoNotificaciones();

        return  daoNotificaciones.notificacionCreada(beanNotificacion);
    }
}
