package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioAdminEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletadminestudiantes", value = "/servlet-admin-estudiantes")
public class ServletAdminEstudiantes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        ServicioAdminEstudiantes servicioAdminEstudiantes = new ServicioAdminEstudiantes();
        List<BeanEstudiante> listaEstudiantes = servicioAdminEstudiantes.listaEstudiantes(periodoActual.getId_periodo());

        if (listaEstudiantes != null && !listaEstudiantes.isEmpty()) {
            req.setAttribute("listaEstudiantes", listaEstudiantes);
        } else {
            req.setAttribute("mensajeVacio", "No hay estudiantes registrados.");
        }

        req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        String buscador = req.getParameter("buscador");

        ServicioAdminEstudiantes servicioAdminEstudiantes = new ServicioAdminEstudiantes();
        List<BeanEstudiante> listaBuscada = servicioAdminEstudiantes.buscarEstudiantes(periodoActual.getId_periodo(), buscador);

        if (listaBuscada != null && !listaBuscada.isEmpty()) {
            req.setAttribute("listaEstudiantes", listaBuscada);
        } else {
            req.setAttribute("mensajeVacio", "No hay ninguna coincidencia.");
        }// Asegúrate de tener esta línea en el doPost de tu ServletAdminEstudiantes
        req.setAttribute("terminoBuscado", buscador);

        req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
    }
}
