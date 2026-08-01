package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletNotificaciones",value = "servlet-crear-notificacion")
public class ServletNotificaciones extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
        ServicioDocente datosDocente = new ServicioDocente();
        List<BeanDocente> listaDocentes = datosDocente.listaDocente();
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        req.setAttribute("docentesDisponibles", listaDocentes);
        req.setAttribute("periodoActivo", periodoActivo);

        ServicioNotificacion servicioNotificacion = new ServicioNotificacion();
        BeanNotificacion creandoNotificacion = new BeanNotificacion();
        BeanNotificacion nuevaNoti=servicioNotificacion.crearNotificacion(creandoNotificacion);


    }
}
