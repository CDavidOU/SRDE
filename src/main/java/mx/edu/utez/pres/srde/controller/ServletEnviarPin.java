package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.service.ServiceUsuario;

import java.io.IOException;

@WebServlet(name = "servletEnviarPin", value = "/servlet-enviar-pin")
public class ServletEnviarPin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String correo = req.getParameter("correo");

        if (correo == null || correo.trim().isEmpty()) {
            req.setAttribute("mensajeError", "Por favor ingresa tu correo.");
            req.getRequestDispatcher("/olvide-password.jsp").forward(req, res);
            return;
        }

        ServiceUsuario servicioUsuario = new ServiceUsuario();

        // 1. Verificar si el correo existe en la base de datos
        if (!servicioUsuario.existeCorreo(correo.trim())) {
            req.setAttribute("mensajeError", "No existe ninguna cuenta registrada con este correo.");
            req.getRequestDispatcher("/olvide-password.jsp").forward(req, res);
            return;
        }

        // 2. Generar PIN de 4 dígitos y guardarlo en la BD
        String pin = servicioUsuario.generarPinRecuperacion();
        boolean pinGuardado = servicioUsuario.asignarTokenUsuario(correo.trim(), pin);

        if (pinGuardado) {
            // 3. TODO: Aquí llamaremos a la clase JavaMail para enviar el correo (siguiente paso)
            // Por ahora, lo imprimimos en la consola de IntelliJ para que lo puedas ver y probar
            System.out.println("======================================");
            System.out.println("SIMULACIÓN DE CORREO ENVIADO");
            System.out.println("Para: " + correo);
            System.out.println("PIN de recuperación: " + pin);
            System.out.println("======================================");

            // 4. Redirigir al servlet de la pantalla 2, pasando el correo por la URL
            res.sendRedirect(req.getContextPath() + "/servlet-restablecer?correo=" + correo.trim());
        } else {
            req.setAttribute("mensajeError", "Error al generar el PIN de recuperación.");
            req.getRequestDispatcher("/olvide-password.jsp").forward(req, res);
        }
    }
}