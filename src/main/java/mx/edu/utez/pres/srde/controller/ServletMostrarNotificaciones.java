package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanNotificacion;
import mx.edu.utez.pres.srde.service.ServicioNotificacion;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "servletMostrarNotificaciones", value = "/servlet-mostrar-notificaciones")
public class ServletMostrarNotificaciones extends HttpServlet {
    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse res)throws IOException {
        ServicioNotificacion servicioListaNotificaciones = new ServicioNotificacion();
        HttpSession session = req.getSession();
        int idDocente = session.getAttribute("idDocente")==null?0:(Integer)session.getAttribute("idDocente");
        String idPeriodo = (String)session.getAttribute("idPeriodo");
        List<BeanNotificacion> listaNotificaciones= servicioListaNotificaciones.buscarListaNotificaciones(idDocente);
    }
}

