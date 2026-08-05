package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.service.ServicioDocente;

import java.io.IOException;

@WebServlet(name = "servletdatosdocente", value = "/servlet-datos-docente")
public class ServletDatosDocente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String strId = req.getParameter("id");
        int idDocente;
        try {
            idDocente = Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/servlet-lista-docentes");
            return;
        }

        ServicioDocente servicioDocente = new ServicioDocente();
        BeanDocente docente = servicioDocente.datosDocente(idDocente);

        if (docente == null) {
            res.sendRedirect(req.getContextPath() + "/servlet-lista-docentes");
            return;
        }

        req.setAttribute("docente", docente);
        req.getRequestDispatcher("WEB-INF/Admin/datos-docente.jsp").forward(req, res);
    }
}
