package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioAdminEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioListaEstudiante;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "servletlistaestudiantes", value = "/servlet-lista-estudiantes")
public class ServletListaEstudiantes extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sesion = req.getSession(false);

        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioPeriodos periodoActual = new ServicioPeriodos();
        BeanPeriodo periodoEncontrado = periodoActual.automatizacionPeriodos();
        int id_periodo = (periodoEncontrado != null) ? periodoEncontrado.getId_periodo() : 0;

        // 1. SI ES ADMINISTRADOR
        if (sesion.getAttribute("adminLogueado") != null) {
            ServicioAdminEstudiantes adminServicio = new ServicioAdminEstudiantes();
            List<BeanEstudiante> lista = adminServicio.listaEstudiantes(id_periodo);

            if (lista != null && !lista.isEmpty()) {
                req.setAttribute("listaEstudiantes", lista);
            } else {
                req.setAttribute("mensajeVacio", "No hay ningún estudiante registrado.");
            }
            // Redirige a la vista del Administrador
            req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
            return;
        }

        // 2. SI ES DOCENTE
        if (sesion.getAttribute("docenteLogueado") != null) {
            BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
            int id_docente = docenteLogueado.getId();

            ServicioListaEstudiante listaServicio = new ServicioListaEstudiante();
            List<BeanAsignacionEstadias> listaEstudiantesActivos = listaServicio.listaEstudiantes(id_docente, id_periodo);

            if (listaEstudiantesActivos != null && !listaEstudiantesActivos.isEmpty()) {
                req.setAttribute("listaEstudiantesActivos", listaEstudiantesActivos);
            } else {
                req.setAttribute("mensajeVacio", "No tiene ningún Estudiante Registrado.");
            }
            // Redirige a la vista del Docente
            req.getRequestDispatcher("WEB-INF/Docente/vista-estudiantes.jsp").forward(req, res);
            return;
        }

        res.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession sesion = req.getSession(false);

        if (sesion == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String buscador = req.getParameter("buscador");
        ServicioPeriodos periodoActual = new ServicioPeriodos();
        BeanPeriodo periodoEncontrado = periodoActual.automatizacionPeriodos();
        int id_periodo = (periodoEncontrado != null) ? periodoEncontrado.getId_periodo() : 0;

        // 1. BUSCADOR PARA ADMINISTRADOR
        if (sesion.getAttribute("adminLogueado") != null) {
            ServicioAdminEstudiantes adminServicio = new ServicioAdminEstudiantes();
            List<BeanEstudiante> lista = adminServicio.buscarEstudiantes(id_periodo, buscador);

            if (lista != null && !lista.isEmpty()) {
                req.setAttribute("listaEstudiantes", lista);
            } else {
                req.setAttribute("mensajeVacio", "No hay coincidencias en la búsqueda.");
            }
            req.setAttribute("terminoBuscado", buscador);
            req.getRequestDispatcher("WEB-INF/Admin/vista-estudiantes-admin.jsp").forward(req, res);
            return;
        }

<<<<<<< HEAD
        // LÍNEA AGREGADA: Regresamos la variable a la vista para que el botón "Limpiar" aparezca
        req.setAttribute("terminoBuscado", buscador);

        req.getRequestDispatcher("WEB-INF/Docente/vista-estudiantes.jsp").forward(req, res);
=======
        // 2. BUSCADOR PARA DOCENTE
        if (sesion.getAttribute("docenteLogueado") != null) {
            BeanDocente docenteLogueado = (BeanDocente) sesion.getAttribute("docenteLogueado");
            int id_docente = docenteLogueado.getId();

            ServicioListaEstudiante listaServicio = new ServicioListaEstudiante();
            List<BeanAsignacionEstadias> listaBuscada = listaServicio.listaBuscoEstudiantes(id_docente, id_periodo, buscador);

            if (listaBuscada != null && !listaBuscada.isEmpty()) {
                req.setAttribute("listaEstudiantesActivos", listaBuscada);
            } else {
                req.setAttribute("mensajeVacio", "No hay ninguna coincidencia.");
            }
            req.getRequestDispatcher("WEB-INF/Docente/vista-estudiantes.jsp").forward(req, res);
            return;
        }

        res.sendRedirect(req.getContextPath() + "/index.jsp");
>>>>>>> b978b3a83bc7bea0f2f69f7c745c7f803e0a542f
    }
}