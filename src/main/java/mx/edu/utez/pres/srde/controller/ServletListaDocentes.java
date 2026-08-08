package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioDocente;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletlistadocentes", value = "/servlet-lista-docentes")
public class ServletListaDocentes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        ServicioDocente servicioDocente = new ServicioDocente();
        List<BeanDocente> listaDocentes = servicioDocente.listaDocentes(periodoActual.getId_periodo());

        if (listaDocentes != null && !listaDocentes.isEmpty()) {
            req.setAttribute("listaDocentes", listaDocentes);
        } else {
            req.setAttribute("mensajeVacio", "No hay docentes registrados.");
        }

        req.getRequestDispatcher("WEB-INF/Admin/vista-docentes.jsp").forward(req, res);
    }
}