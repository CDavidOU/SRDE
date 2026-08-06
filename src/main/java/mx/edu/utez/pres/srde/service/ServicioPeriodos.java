package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoPeriodo;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class ServicioPeriodos {
        //Servicios Admin
    private DaoPeriodo nuevoPeriodo = new DaoPeriodo();
    public List<BeanPeriodo> obtenerTodosPeriodos() {
        return nuevoPeriodo.consultarTodosLosPeriodos();
    }

    public List<BeanEstudiante> consultarTodosLosEstudiantesPorPeriodo(int idPeriodo) {
        return nuevoPeriodo.consultarTodosLosEstudiantesPorPeriodo(idPeriodo);
    }

    // Método para obtener la lista de periodos asociados al docente (para el acordeón)
    public List<BeanPeriodo> obtenerPeriodosPorDocente(int idDocente) {
        return nuevoPeriodo.obtenerPeriodosPorDocente(idDocente);
    }

    // Nuevo método delegado para obtener los estudiantes de un periodo
    public List<BeanEstudiante> consultarEstudiantePeriodo(int idDocente, int idPeriodo) {
        return nuevoPeriodo.consultarEstudiantePeriodo(idDocente, idPeriodo);
    }

    // Métodos para la vista administrativa de periodos
    public List<BeanPeriodo> listarTodosPeriodos() {
        return nuevoPeriodo.listarTodosPeriodos();
    }

    public int contarEstudiantesPorPeriodo(int idPeriodo) {
        return nuevoPeriodo.contarEstudiantesPorPeriodo(idPeriodo);
    }

    // Métodos delegados del DAO
    public BeanPeriodo buscarPeriodo(String periodo) {
        return nuevoPeriodo.buscarPeriodo(periodo);
    }

    public BeanPeriodo registrarNuevoPeriodo(BeanPeriodo periodo) {
        return nuevoPeriodo.registrarNuevoPeriodo(periodo);
    }

    public BeanPeriodo automatizacionPeriodos() {
        LocalDate fechaHoy = LocalDate.now();
        int mes = fechaHoy.getMonthValue();
        int anio = fechaHoy.getYear();
        String nombreCalculado = "";
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        switch(mes){
            case 1: case 2: case 3: case 4:
                nombreCalculado = "Enero - Abril "+anio;
                fechaInicio = LocalDate.of(anio, 1, 1);   // 1 de Enero
                fechaFin = LocalDate.of(anio, 4, 30);
                break;
            case 5: case 6: case 7: case 8:
                nombreCalculado = "Mayo - Agosto "+anio;
                fechaInicio = LocalDate.of(anio, 5, 1);
                fechaFin = LocalDate.of(anio, 8, 31);
                break;
            default: nombreCalculado = "Septiembre - Diciembre "+anio;
                fechaInicio = LocalDate.of(anio,9,1);
                fechaFin = LocalDate.of(anio,12,31);
                break;
        }
        BeanPeriodo periodoExistente = nuevoPeriodo.buscarPeriodo(nombreCalculado);
        if(periodoExistente!=null){
            return periodoExistente;
        }
        BeanPeriodo periodoNuevo = new BeanPeriodo();
        periodoNuevo.setNombre_periodo(nombreCalculado);
        periodoNuevo.setFecha_fin(Date.valueOf(fechaFin));
        periodoNuevo.setFecha_inicio(Date.valueOf(fechaInicio));

        BeanPeriodo periodoRegistrado = nuevoPeriodo.registrarNuevoPeriodo(periodoNuevo);
        if (periodoRegistrado != null) {
            return periodoRegistrado;
        }

        // Si el insert falló (p.ej. dos peticiones concurrentes insertando el mismo periodo nuevo),
        // reintentamos la búsqueda: es probable que la otra petición ya lo haya creado.
        return nuevoPeriodo.buscarPeriodo(nombreCalculado);
    }
}