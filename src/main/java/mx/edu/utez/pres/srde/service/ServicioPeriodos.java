package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoPeriodo;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import java.time.LocalDate;
import java.sql.Date;

public class ServicioPeriodos {
    DaoPeriodo nuevoPeriodo = new DaoPeriodo();
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
                     fechaInicio = LocalDate.of(anio,9,30);
                     fechaFin = LocalDate.of(anio,10,31);
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

        return nuevoPeriodo.registrarNuevoPeriodo(periodoNuevo);
    }
}
