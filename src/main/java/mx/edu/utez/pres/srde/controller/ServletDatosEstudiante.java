package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.*;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletDatosEstudiante",value = "/servlet-datos-estudiante")
public class ServletDatosEstudiante extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession sesion = req.getSession(false);
        // 2. Declarar la variable intentando obtenerla de la sesión
        Object adminLogueado = null;
        if (sesion != null) {
            adminLogueado = sesion.getAttribute("adminLogueado");
        }

        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioDatosEstudiantes servicioDatosEstudiante = new ServicioDatosEstudiantes();
        String matricula = req.getParameter("matricula");
        if (matricula != null && !matricula.trim().isEmpty()) {
            BeanEstudiante estudiante = servicioDatosEstudiante.datosEstudiante(matricula);

            if (estudiante != null) {
                req.setAttribute("datosEstudiante", estudiante);

                ServicioDocumento servicioDoc = new ServicioDocumento();
                List<BeanArchivo> listaDocumentos = servicioDoc.obtenerDocumentosPorMatricula(matricula);
                req.setAttribute("listaDocumentos", listaDocumentos);

                if (adminLogueado != null) {
                    req.getRequestDispatcher("WEB-INF/Admin/vista-datos-estudiante-admin.jsp").forward(req, res);
                } else {
                    req.getRequestDispatcher("WEB-INF/Docente/vista-datos-estudiante.jsp").forward(req, res);
                }
                return;
            }
        }
        System.out.println("Matrícula inválida o no encontrada");
        if (adminLogueado != null) {
           req.getRequestDispatcher("WEB-INF/Admin/lista-estudiante.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("WEB-INF/Admin/lista-estudiante.jsp").forward(req, res);
        }
    }

    //Aqui estara el doPost

}
