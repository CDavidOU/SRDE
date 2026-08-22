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
<<<<<<< HEAD
=======
        req.setCharacterEncoding("UTF-8");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
        HttpSession sesion = req.getSession(false);

        // 1. Validar que sea el Administrador
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

<<<<<<< HEAD
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
=======
        // 2. Obtener el periodo actual con validación de nulos
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActual = servicioPeriodos.automatizacionPeriodos();
        int idPeriodo = (periodoActual != null) ? periodoActual.getId_periodo() : 0;

        // 3. Obtener y limpiar el texto del buscador
        String buscador = req.getParameter("buscador");
        ServicioDocente servicioDocente = new ServicioDocente();
        List<BeanDocente> listaBuscada;

        if (buscador != null && !buscador.trim().isEmpty()) {
            // Limpiar espacios extras en los extremos
            buscador = buscador.trim();

            // 4. Buscar con el término limpio
            listaBuscada = servicioDocente.buscarDocentes(idPeriodo, buscador);
        } else {
            // Si el usuario envió el buscador vacío, se recarga la lista completa por defecto
            listaBuscada = servicioDocente.listaDocentes(idPeriodo);
            buscador = "";
        }

        // 5. Enviar resultados o mensaje
        if (listaBuscada != null && !listaBuscada.isEmpty()) {
            req.setAttribute("listaDocentes", listaBuscada);
        } else {
            req.setAttribute("mensajeVacio", "No se encontró ningún docente con el criterio: '" + buscador + "'");
        }

        // 6. Retornar el término buscado para mantenerlo en el campo de texto del JSP
        req.setAttribute("terminoBuscado", buscador);

        // 7. Redirigir a la vista
        req.getRequestDispatcher("/WEB-INF/Admin/vista-docentes.jsp").forward(req, res);
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    }
}