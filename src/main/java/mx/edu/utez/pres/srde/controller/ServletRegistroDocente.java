package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServicioRegistroDocente;

import java.io.IOException;

@WebServlet(name = "servletRegistroDocente", value = "/servlet-registro-docente")
public class ServletRegistroDocente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Validación de sesión de la rama union (Seguridad)
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        
        req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Validación de sesión de la rama union (Seguridad)
        HttpSession sesion = req.getSession(false);
        if (sesion == null || sesion.getAttribute("adminLogueado") == null) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        req.setCharacterEncoding("UTF-8");

        String nombre = req.getParameter("nombre");
        
        // Compatibilidad de parámetros entre la rama Carlos y union
        String apellido = req.getParameter("apellido");
        if (apellido == null) {
            String pat = req.getParameter("apellidoPaterno");
            String mat = req.getParameter("apellidoMaterno");
            apellido = (pat != null ? pat.trim() : "") + (mat != null && !mat.trim().isEmpty() ? " " + mat.trim() : "");
        }

        String correo = req.getParameter("correo");
        String telefono = req.getParameter("telefono");
        
        String academia = req.getParameter("academia");
        if (academia == null) {
            academia = req.getParameter("area");
        }
        
        String carrera = req.getParameter("carrera");

        // Validación de campos vacíos de la rama union
        if (nombre == null || nombre.trim().isEmpty()
                || apellido == null || apellido.trim().isEmpty()
                || telefono == null || telefono.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || academia == null || academia.trim().isEmpty()
                || carrera == null || carrera.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
            return;
        }

        // Lógica de registro de la rama Carlos
        BeanDocente nuevoDocente = new BeanDocente();
        nuevoDocente.setNombre(nombre.trim());
        nuevoDocente.setApellido(apellido.trim());
        nuevoDocente.setTelefono(telefono.trim());
        nuevoDocente.setAcademia(academia.trim());
        nuevoDocente.setCarrera(carrera.trim());
        nuevoDocente.setCorreo(correo.trim());

        BeanUsuario usuarioDocente = new BeanUsuario();
        usuarioDocente.setRol("Docente");
        usuarioDocente.setDatosPersona(nuevoDocente);
        
        // Generación de contraseña de la rama Carlos
        String contrasenaAutomatica = (nombre.trim() + apellido.trim()).toLowerCase().replace(" ", "");
        usuarioDocente.setPassword(contrasenaAutomatica);

        ServicioRegistroDocente servicioRegistroDocente = new ServicioRegistroDocente();
        String resultado = servicioRegistroDocente.registrarTodoElDocente(nuevoDocente, usuarioDocente);

        if ("EXISTE".equals(resultado)) {
            req.setAttribute("mensajeError", "El correo ya se encuentra registrado por otro docente.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        } else if ("EXITO".equals(resultado)) {
            req.setAttribute("mensajeExito", "¡Docente registrado con éxito! Contraseña: " + contrasenaAutomatica);
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error interno en la base de datos.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        }
    }
}