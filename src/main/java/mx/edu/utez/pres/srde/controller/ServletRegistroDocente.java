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
import java.text.Normalizer;

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

        // Normalizamos para quitar acentos y caracteres especiales (ej. 'María José' ->)
        String textoBase = nombre.trim() + apellido.trim();
        String contrasenaLimpia = Normalizer.normalize(textoBase, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")      // Elimina los acentos/tildes
                .replaceAll("[^a-zA-Z0-9]", "") // Elimina cualquier carácter que no sea letra o número
                .toLowerCase();                // Todo a minúsculas

        usuarioDocente.setPassword(contrasenaLimpia);

        ServicioRegistroDocente servicioRegistroDocente = new ServicioRegistroDocente();
        String resultado = servicioRegistroDocente.registrarTodoElDocente(nuevoDocente, usuarioDocente);

        if ("EXISTE".equals(resultado)) {
            req.setAttribute("mensajeError", "El correo ya se encuentra registrado por otro docente.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        }else if ("EXITO".equals(resultado)) {
            // Guardas el mensaje en la sesión para que sobreviva a la redirección (sendRedirect)
            sesion.setAttribute("mensajeOk", "¡Docente registrado con éxito! Contraseña: " + usuarioDocente.getPassword());

            // Rediriges al SERVLET que consulta y muestra la lista de docentes
            res.sendRedirect(req.getContextPath() + "/servlet-lista-docentes");
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error interno en la base de datos.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        }
    }
}