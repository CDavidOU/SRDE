package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.service.ServicioDocente;

import java.io.IOException;

@WebServlet(name = "servletregistrodocente", value = "/servlet-registro-docente")
public class ServletRegistroDocente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String nombre = req.getParameter("nombre");
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correo");
        String academia = req.getParameter("area");
        String carrera = req.getParameter("carrera");

        if (nombre == null || nombre.trim().isEmpty()
                || apellidoPaterno == null || apellidoPaterno.trim().isEmpty()
                || telefono == null || telefono.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || academia == null || academia.trim().isEmpty()
                || carrera == null || carrera.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
            return;
        }

        String apellido = apellidoPaterno.trim();
        if (apellidoMaterno != null && !apellidoMaterno.trim().isEmpty()) {
            apellido += " " + apellidoMaterno.trim();
        }

        BeanDocente nuevoDocente = new BeanDocente();
        nuevoDocente.setNombre(nombre.trim());
        nuevoDocente.setApellido(apellido);
        nuevoDocente.setTelefono(telefono.trim());
        nuevoDocente.setCorreo(correo.trim());
        nuevoDocente.setAcademia(academia.trim());
        nuevoDocente.setCarrera(carrera.trim());

        ServicioDocente servicioDocente = new ServicioDocente();
        BeanDocente docenteRegistrado = servicioDocente.registrarDocente(nuevoDocente);

        if (docenteRegistrado != null) {
            sesion.setAttribute("mensajeOk", "Docente registrado correctamente. Contraseña temporal: "
                    + mx.edu.utez.pres.srde.service.ServicioDocente.PASSWORD_TEMPORAL_DEFAULT);
            res.sendRedirect(req.getContextPath() + "/servlet-lista-docentes");
        } else {
            req.setAttribute("mensajeError", "Error: El correo ya existe o faltan campos obligatorios.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        }
    }
}
