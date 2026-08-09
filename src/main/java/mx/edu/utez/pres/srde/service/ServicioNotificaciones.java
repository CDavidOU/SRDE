package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoNotificaciones;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.model.NotificacionBean;

import java.util.ArrayList;
import java.util.List;

public class ServicioNotificaciones {
        private final DaoNotificaciones daoNotificaciones = new DaoNotificaciones();

        public boolean desocultarNotificacion(NotificacionBean notificacion) {
            DaoNotificaciones dao = new DaoNotificaciones();
            return dao.desocultarNotificacion(notificacion);
        }

        public List<CalendarioBean> buscarListaNotificaciones(int idDocente, int idPeriodo) {
            if (idDocente <= 0 || idPeriodo <= 0) {
                return new ArrayList<>();
            }
            return daoNotificaciones.mostrarCalendariosDocente(idDocente, idPeriodo);
        }

        public boolean ocultarNotificacion(NotificacionBean notif) {
            if (notif == null || notif.getIdCalendario() <= 0 || notif.getId_docente() <= 0) {
                return false;
            }
            return daoNotificaciones.ocultarNotificacion(notif);
        }

        // ==========================================
        // 2. DOCUMENTOS PENDIENTES (RAMA UNIÓN)
        // ==========================================

        public int contarDocumentosPendientesDocente(int idDocente) {
            if (idDocente <= 0) return 0;
            return daoNotificaciones.contarDocumentosPendientesDocente(idDocente);
        }

        public int contarDocumentosPendientesGlobal() {
            return daoNotificaciones.contarDocumentosPendientesGlobal();
        }

        public List<BeanNotificacion> listarPendientesDocente(int idDocente) {
            if (idDocente <= 0) return new ArrayList<>();
            return daoNotificaciones.listarPendientesDocente(idDocente);
        }

        public List<BeanNotificacion> listarPendientesGlobal() {
            return daoNotificaciones.listarPendientesGlobal();
        }
    }