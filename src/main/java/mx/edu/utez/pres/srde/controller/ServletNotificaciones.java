package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;

import java.io.IOException;

@WebServlet(name = "servletNotificaciones",value = "servlet-crear-notificacion")
public class ServletNotificaciones extends HttpServlet {

    @Override
    public void doPost(HttpServletResponse res, HttpServletRequest req) throws IOException {
        BeanDocente docenteDatos= new BeanDocente();
        ServicioDocente datosDocente = new ServicioDocente();

        ServicioNotificacion servicioNotificacion = new ServicioNotificacion();

        BeanNotificacion creandoNotificacion = new BeanNotificacion();

        BeanNotificacion nuevaNoti=servicioNotificacion.crearNotificacion(creandoNotificacion)

    }
}
