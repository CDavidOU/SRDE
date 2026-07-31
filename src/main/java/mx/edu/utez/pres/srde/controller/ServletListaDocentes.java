package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.service.ServicioListaDocentes;

import java.io.IOException;
import java.util.List;

@WebServlet("/servlet-lista-docentes")
public class ServletListaDocentes extends HttpServlet {

    private final ServicioListaDocentes servicio = new ServicioListaDocentes();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        // Permite sesión si es Docente O Admin
        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Periodo por defecto (ajustar si lo obtienes de sesión)
        int idPeriodo = 1;

        List<BeanDocente> listaDocentesActivos = servicio.listaDocentes(idPeriodo);

        if (listaDocentesActivos != null && !listaDocentesActivos.isEmpty()) {
            req.setAttribute("listaDocentesActivos", listaDocentesActivos);
        } else {
            req.setAttribute("mensajeVacio", "No hay ningún Docente Registrado.");
        }

        // Ruta corregida a la carpeta WEB-INF/admin/
        req.getRequestDispatcher("/WEB-INF/Admin/vista-docentes.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);

        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String buscador = req.getParameter("txtBuscador");
        int idPeriodo = 1;

        List<BeanDocente> listaBuscada;
        if (buscador != null && !buscador.trim().isEmpty()) {
            listaBuscada = servicio.listaBuscoDocentes(idPeriodo, buscador);
        } else {
            listaBuscada = servicio.listaDocentes(idPeriodo);
        }

        if (listaBuscada != null && !listaBuscada.isEmpty()) {
            req.setAttribute("listaDocentesActivos", listaBuscada);
        } else {
            req.setAttribute("mensajeVacio", "No hay ninguna coincidencia.");
        }

        req.getRequestDispatcher("/WEB-INF/Admin/vista-docentes.jsp").forward(req, res);
    }
}