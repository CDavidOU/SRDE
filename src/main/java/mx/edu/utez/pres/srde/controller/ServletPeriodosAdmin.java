package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "servletPeriodoAdmin", value = "/servlet-periodo-admin")
public class ServletPeriodosAdmin extends HttpServlet {
    private ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        List<BeanPeriodo> listaPeriodos = servicioPeriodos.obtenerTodosPeriodos();

        if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
            for (BeanPeriodo p : listaPeriodos) {
                List<BeanEstudiante> todosLosAlumnos = servicioPeriodos.consultarTodosLosEstudiantesPorPeriodo(p.getId_periodo());
                p.setListaEstudiantes(todosLosAlumnos);
            }
        }

        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();
        int idPeriodoActivo = (periodoActual != null) ? periodoActual.getId_periodo() : 0;

        request.setAttribute("listaPeriodos", listaPeriodos);
        request.setAttribute("idPeriodoActivo", idPeriodoActivo);

        request.getRequestDispatcher("/WEB-INF/Admin/periodoAdmin.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String filtroPeriodo = request.getParameter("filtroPeriodo");
        List<BeanPeriodo> listaPeriodos = servicioPeriodos.obtenerTodosPeriodos();

        if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
            for (BeanPeriodo p : listaPeriodos) {
                List<BeanEstudiante> todosLosAlumnos = servicioPeriodos.consultarTodosLosEstudiantesPorPeriodo(p.getId_periodo());
                p.setListaEstudiantes(todosLosAlumnos);
            }
        }

        int idPeriodoActivo = 0;
        if (filtroPeriodo != null && !filtroPeriodo.isEmpty()) {
            idPeriodoActivo = Integer.parseInt(filtroPeriodo);
        }

        request.setAttribute("listaPeriodos", listaPeriodos);
        request.setAttribute("idPeriodoActivo", idPeriodoActivo);

        request.getRequestDispatcher("/WEB-INF/Admin/periodoAdmin.jsp").forward(request, response);
    }
}