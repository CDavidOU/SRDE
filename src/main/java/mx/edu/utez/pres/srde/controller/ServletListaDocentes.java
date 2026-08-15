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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        // 1. Validar que sea el Administrador
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // 2. Obtener el periodo actual
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();

        // 3. Obtener el texto que el usuario escribió en el buscador
        String buscador = req.getParameter("buscador");

        // 4. Instanciar el servicio y buscar
        ServicioDocente servicioDocente = new ServicioDocente();

        // ¡OJO AQUÍ! Debes tener creado este método "buscarDocentes" en tu ServicioDocente
        List<BeanDocente> listaBuscada = servicioDocente.buscarDocentes(periodoActual.getId_periodo(), buscador);

        // 5. Enviar los resultados o el mensaje de vacío
        if (listaBuscada != null && !listaBuscada.isEmpty()) {
            req.setAttribute("listaDocentes", listaBuscada);
        } else {
            req.setAttribute("mensajeVacio", "No se encontró ningún docente con ese criterio.");
        }

        // 6. Enviar el término buscado de vuelta para que no se borre del input en el JSP
        req.setAttribute("terminoBuscado", buscador);

        // 7. Redirigir a la vista
        req.getRequestDispatcher("WEB-INF/Admin/vista-docentes.jsp").forward(req, res);
    }
}