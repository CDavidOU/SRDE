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
@WebServlet(name = "ServletAdminEstudiantes", value = "/servlet-admin-estudiantes")
public class ServletAdminEstudiantes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodo = servicioPeriodos.automatizacionPeriodos();

        ServicioAdminEstudiantes servicioAdmin = new ServicioAdminEstudiantes();
        List<BeanEstudiante> lista = servicioAdmin.listaEstudiantes(periodo.getId_periodo());

        req.setAttribute("listaEstudiantes", lista);
        req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String buscador = req.getParameter("buscador");
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodo = servicioPeriodos.automatizacionPeriodos();

        ServicioAdminEstudiantes servicioAdmin = new ServicioAdminEstudiantes();
        List<BeanEstudiante> lista;

        if (buscador != null && !buscador.trim().isEmpty()) {
            lista = servicioAdmin.buscarEstudiantes(periodo.getId_periodo(), buscador);
        } else {
<<<<<<< HEAD
            req.setAttribute("mensajeVacio", "No hay ninguna coincidencia.");
        }// Asegúrate de tener esta línea en el doPost de tu ServletAdminEstudiantes
        req.setAttribute("terminoBuscado", buscador);
=======
            lista = servicioAdmin.listaEstudiantes(periodo.getId_periodo());
        }
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f

        req.setAttribute("listaEstudiantes", lista);
        req.setAttribute("terminoBuscado", buscador);
        req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
    }
}