package mx.edu.utez.pres.srde.service;
import mx.edu.utez.pres.srde.dao.CalendarioDao;
import mx.edu.utez.pres.srde.model.CalendarioBean;

import java.util.ArrayList;
import java.util.List;

public class CalendarioServicio {
    CalendarioDao calendarioDao = new CalendarioDao();
    public boolean registrarCalendario(CalendarioBean calendario) {

        if (calendario == null) {
            return false;
        }

        if (calendario.getTipo_doc() <= 0) {
            System.out.println("Error: Debes seleccionar un tipo de documento válido.");
            return false;
        }
        if (calendario.getId_periodo() <= 0) {
            System.out.println("Error: No se encontró un periodo activo en la base de datos.");
            return false;
        }
        if (calendario.getFechaInicio() == null || calendario.getFechaLimite() == null) {
            return false;
        }

        if (calendario.getFechaLimite().before(calendario.getFechaInicio())) {
            System.out.println("Error: La fecha límite no puede ser anterior a la fecha de inicio.");
            return false;
        }

        boolean yaExiste = calendarioDao.existeCalendarioEnPeriodo(calendario.getId_periodo(), calendario.getTipo_doc());
        if (yaExiste) {
            System.out.println("Error: El tipo de documento ya tiene un calendario asignado en el periodo actual.");
            return false;
        }

        boolean resultado = calendarioDao.registrarCalendario(calendario);

        return resultado;
    }

    public List<CalendarioBean> buscarListaProgramada(int idPeriodo){
        if (idPeriodo > 0) {
            return calendarioDao.obtenerCalendarios(idPeriodo);
        }

        return new ArrayList<>();
    }
    public CalendarioBean buscarCalendario(int idCalendario){
        CalendarioDao calendarioDao = new CalendarioDao();
        return calendarioDao.obtenerCalendarioPorId(idCalendario);
    }

    public boolean actualizarCalendario(CalendarioBean actualizarCalendario){
        if (actualizarCalendario == null) {
            return false;
        }

        if (actualizarCalendario.getFechaLimite().before(actualizarCalendario.getFechaInicio())) {
            System.out.println("Error: La fecha límite no puede ser anterior a la fecha de inicio.");
            return false;
        }
        boolean resultado = calendarioDao.actualizarCalendario(actualizarCalendario);

        return resultado;
    }
}

