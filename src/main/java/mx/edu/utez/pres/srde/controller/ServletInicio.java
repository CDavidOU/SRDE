package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanUsuario;
import mx.edu.utez.pres.srde.service.ServiceUsuario;

import java.io.IOException;
import java.util.List;

@WebServlet (name = "servletinicio", value = "/servlet-inicio")
public class ServletInicio extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        req.getParameter("correoUsuario");
        req.getParameter("password");
        BeanUsuario usuario = new BeanUsuario();
        usuario.setCorreo(req.getParameter("correoUsuario"));
        usuario.setPassword(req.getParameter("password"));
        ServiceUsuario servicioUsuario = new ServiceUsuario();
        BeanUsuario usuarioLogueado = servicioUsuario.verificarUsuario(usuario);

        if (usuarioLogueado != null) {
            req.setAttribute("usuario", usuarioLogueado);
            req.getRequestDispatcher("WEB-INF/perfil.jsp").forward(req, res);
        }else {
            req.setAttribute("mensajeError", "Credenciales incorrectas");
            req.getRequestDispatcher("index.jsp").forward(req, res);
        }
    }
}