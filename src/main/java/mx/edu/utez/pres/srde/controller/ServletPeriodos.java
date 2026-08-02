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

@WebServlet(name = "servletperiodos", value = "/servlet-periodos")
public class ServletPeriodos extends HttpServlet {

    private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        BeanDocente docente = (BeanDocente) session.getAttribute("docenteLogueado");

        if (docente == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        int idDocente = docente.getId();

        // 1. Obtenemos TODOS los periodos
        List<BeanPeriodo> listaPeriodos = servicioPeriodos.obtenerPeriodosPorDocente(idDocente);

        // 2. Le guardamos sus estudiantes a cada periodo (para que sirvan las flechas)
        if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
            for (BeanPeriodo p : listaPeriodos) {
                List<BeanEstudiante> alumnos = servicioPeriodos.consultarEstudiantePeriodo(idDocente, p.getId_periodo());
                p.setListaEstudiantes(alumnos);
            }
        }

        // 3. Calculamos cuál abrir por defecto al entrar
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();
        int idPeriodoActivo = (periodoActual != null) ? periodoActual.getId_periodo() : 0;

        request.setAttribute("listaPeriodos", listaPeriodos);
        request.setAttribute("idPeriodoActivo", idPeriodoActivo);

        request.getRequestDispatcher("/WEB-INF/Plantillas/periodo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        BeanDocente docente = (BeanDocente) session.getAttribute("docenteLogueado");

        if (docente == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        int idDocente = docente.getId();
        String filtroPeriodo = request.getParameter("filtroPeriodo");

        // 1. Volvemos a obtener TODOS los periodos
        List<BeanPeriodo> listaPeriodos = servicioPeriodos.obtenerPeriodosPorDocente(idDocente);

        // 2. Volvemos a guardarles sus estudiantes (¡Esto era lo que faltaba en tu botón Buscar!)
        if (listaPeriodos != null && !listaPeriodos.isEmpty()) {
            for (BeanPeriodo p : listaPeriodos) {
                List<BeanEstudiante> alumnos = servicioPeriodos.consultarEstudiantePeriodo(idDocente, p.getId_periodo());
                p.setListaEstudiantes(alumnos);
            }
        }

        // 3. Como le dimos a "Buscar", el activo ahora es el que seleccionaste en el menú
        int idPeriodoActivo = 0;
        if (filtroPeriodo != null && !filtroPeriodo.isEmpty()) {
            idPeriodoActivo = Integer.parseInt(filtroPeriodo);
        }

        request.setAttribute("listaPeriodos", listaPeriodos);
        request.setAttribute("idPeriodoActivo", idPeriodoActivo);

        request.getRequestDispatcher("/WEB-INF/Plantillas/periodo.jsp").forward(request, response);
    }
}