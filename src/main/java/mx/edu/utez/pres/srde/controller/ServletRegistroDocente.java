package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServicioRegistroDocente;

import java.io.IOException;

@WebServlet(name = "servletRegistroDocente",value = "/servlet-registro-docente")
public class ServletRegistroDocente extends HttpServlet {
    public void  doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServicioRegistroDocente servicioRegistroDocente = new ServicioRegistroDocente();
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String correo = req.getParameter("correo");
        String telefono = req.getParameter("telefono");
        String academia = req.getParameter("academia");
        String carrera = req.getParameter("carrera");
        BeanUsuario usuarioDocente = new BeanUsuario();
        BeanDocente nuevoDocente = new BeanDocente();
        nuevoDocente.setNombre(nombre);
        nuevoDocente.setApellido(apellido);
        nuevoDocente.setTelefono(telefono);
        nuevoDocente.setAcademia(academia);
        nuevoDocente.setCarrera(carrera);
        nuevoDocente.setCorreo(correo);
        String contrasenaAutomatica = (nombre + apellido).toLowerCase().replace(" ", "");
        usuarioDocente.setPassword(contrasenaAutomatica);

        String resultado = servicioRegistroDocente.registrarTodoElDocente(nuevoDocente, usuarioDocente);
        if ("EXISTE".equals(resultado)) {
            req.setAttribute("mensajeError", "El correo ya se encuentra registrado por otro docente.");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req,res);
        } else if ("EXITO".equals(resultado)) {
            req.setAttribute("mensajeExito", "¡Docente registrado con éxito!");
            req.getRequestDispatcher("WEB-INF/Admin/registro-docente.jsp").forward(req, res);
        } else {
            req.setAttribute("mensajeError", "Ocurrió un error interno en la base de datos.");
            req.getRequestDispatcher("WEB-INF/Admin/perfil.jsp").forward(req, res);
        }
    }
}
