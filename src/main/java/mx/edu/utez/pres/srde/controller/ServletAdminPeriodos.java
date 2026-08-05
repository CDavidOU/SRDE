package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletadminperiodos", value = "/servlet-admin-periodos")
public class ServletAdminPeriodos extends HttpServlet {

    private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        List<BeanPeriodo> listaPeriodos = servicioPeriodos.listarTodosPeriodos();

        if (listaPeriodos != null) {
            for (BeanPeriodo periodo : listaPeriodos) {
                periodo.setTotalEstudiantes(servicioPeriodos.contarEstudiantesPorPeriodo(periodo.getId_periodo()));
            }
        }

        if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
            req.setAttribute("listaPeriodos", listaPeriodos);
        } else {
            req.setAttribute("mensajeVacio", "No hay periodos registrados.");
        }

        req.getRequestDispatcher("WEB-INF/Admin/vista-periodos-admin.jsp").forward(req, res);
    }
}
