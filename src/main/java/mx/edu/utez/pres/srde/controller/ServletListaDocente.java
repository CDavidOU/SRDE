package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.service.ServicioDocente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "servletListaDocente",value = "/servlet-lista-docente")
public class ServletListaDocente extends HttpServlet {
    private ServicioDocente servicioDocente = new ServicioDocente();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        List<BeanDocente> listaDocentes = servicioDocente.listaDocente();
        req.setAttribute("listaDocentes",listaDocentes);
        String idParam = req.getParameter("idDocente");

        if (idParam != null && !idParam.isEmpty()) {
            int idDocente = Integer.parseInt(idParam);
            BeanDocente docenteDato = servicioDocente.datosDocente(idDocente);
            req.setAttribute("datoDocente", docenteDato);
            req.getRequestDispatcher("WEB-INF/Admin/datos-docente.jsp").forward(req, res);
            return;
        }
        req.getRequestDispatcher("WEB-INF/Admin/lista-docentes.jsp").forward(req,res);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int idDocente = Integer.parseInt(req.getParameter("idDocente"));
        BeanDocente datoDocente = servicioDocente.datosDocente(idDocente);
        req.setAttribute("datoDocente",datoDocente);
        System.out.println("datoDocente: "+datoDocente.getId());
        System.out.println("datoDocente: "+datoDocente.getNombre());
        System.out.println("datoDocente: "+datoDocente.getApellido());
        if(idDocente>0){
            req.getRequestDispatcher("WEB-INF/Admin/datos-docente.jsp").forward(req,res);
        }else{
            System.out.println("id docente no encontrada");
            req.getRequestDispatcher("WEB-INF/Admin/lista-docente.jsp").forward(req,res);
        }
    }
}
