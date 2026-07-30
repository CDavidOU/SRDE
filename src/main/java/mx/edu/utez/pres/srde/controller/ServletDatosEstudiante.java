package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;

import java.io.IOException;

@WebServlet(name = "servletDatosEstudiante",value = "/servlet-datos-estudiante")
public class ServletDatosEstudiante extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServicioDatosEstudiantes servicioDatosEstudiante = new ServicioDatosEstudiantes();
        String matricula= req.getParameter("matricula");


        if(matricula!=null && !matricula.trim().isEmpty()){
            BeanEstudiante estudiante = servicioDatosEstudiante.datosEstudiante(matricula);
            System.out.println("MATRICULA: "+matricula);
            if(estudiante!=null){
                req.setAttribute("datosEstudiante",estudiante);
                req.getRequestDispatcher("WEB-INF/Docente/vista-datos-estudiante.jsp").forward(req,res);
            }

        }else{
            System.out.println("matricula invalida");
            res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
        }

    }

}
