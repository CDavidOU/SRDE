package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanArchivo;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.service.ServicioDatosEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletDatosEstudiante",value = "/servlet-datos-estudiante")
public class ServletDatosEstudiante extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession sesion = req.getSession(false);
        if (sesion == null || (sesion.getAttribute("docenteLogueado") == null && sesion.getAttribute("adminLogueado") == null)) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServicioDatosEstudiantes servicioDatosEstudiante = new ServicioDatosEstudiantes();
        String matricula= req.getParameter("matricula");


        if(matricula!=null && !matricula.trim().isEmpty()){
            BeanEstudiante estudiante = servicioDatosEstudiante.datosEstudiante(matricula);
            System.out.println("MATRICULA: "+matricula);
            if(estudiante != null){
                // 1. Mandas los datos personales
                req.setAttribute("datosEstudiante", estudiante);

                // --- 2. AGREGAS LA BÚSQUEDA DE DOCUMENTOS ---
                ServicioDocumento servicioDoc = new ServicioDocumento();
                // Nota: Adapta el nombre del método a como lo tengas en tu ServicioDocumento
                List<BeanArchivo> listaDocumentos = servicioDoc.obtenerDocumentosPorMatricula(matricula);
                req.setAttribute("listaDocumentos", listaDocumentos);
                // --------------------------------------------

                // 3. Envías todo a la vista
                req.getRequestDispatcher("WEB-INF/Docente/vista-datos-estudiante.jsp").forward(req,res);
            }

        }else{
            System.out.println("matricula invalida");
            res.sendRedirect(req.getContextPath() + "/servlet-lista-estudiantes");
        }

    }

    //Aqui estara el doPost

}
