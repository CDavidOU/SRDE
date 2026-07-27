package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanPersona;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServiceUsuario;
import mx.edu.utez.pres.srde.service.ServicioAdmin;
import mx.edu.utez.pres.srde.service.ServicioDocente;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "servletinicio", value = "/servlet-inicio")
public class ServletInicio extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        BeanUsuario usuario = new BeanUsuario();
        BeanPersona persona = new BeanPersona();

        persona.setCorreo(req.getParameter("correoUsuario"));
        usuario.setDatosPersona(persona);
        usuario.setPassword(req.getParameter("password"));

        ServiceUsuario servicioUsuario = new ServiceUsuario();
        BeanUsuario usuarioLogueado = servicioUsuario.verificarUsuario(usuario);

        if (usuarioLogueado != null) {
            HttpSession sesion = req.getSession();
            if ("Administrador".equals(usuarioLogueado.getRol())) {
                ServicioAdmin servicioAdmin = new ServicioAdmin();
                BeanAdmin admin = servicioAdmin.datosAdmin(usuarioLogueado.getId());
                sesion.setAttribute("adminLogueado", admin);
                int idAdmin = admin.getId();
                req.getRequestDispatcher("WEB-INF/Admin/perfil.jsp").forward(req, res);
            } else {
                ServicioDocente serviceDocente = new ServicioDocente();
                BeanDocente datosDocente = serviceDocente.datosDocente(usuarioLogueado.getId());
                sesion.setAttribute("docenteLogueado", datosDocente);
                int idDocente = datosDocente.getId();
                req.getRequestDispatcher("WEB-INF/Docente/perfilDocente.jsp").forward(req, res);
            }
        } else {
            req.setAttribute("mensajeError", "Credenciales incorrectas");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}