package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioListaEstudiantes;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "servletlistaestudiantes", value = "/servlet-lista-estudiantes")
public class ServletListaEstudiantes extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServicioListaEstudiantes listaServicio=new ServicioListaEstudiantes();
        HttpSession sesion=req.getSession();
        BeanDocente docenteLogueado = (BeanDocente)sesion.getAttribute("docenteLogueado");
        int id_docente=docenteLogueado.getId();
        ServicioPeriodos periodoActual=new ServicioPeriodos();
        BeanPeriodo periodoEncontrado= periodoActual.automatizacionPeriodos();
        int id_periodo=periodoEncontrado.getId_periodo();
        List<BeanAsignacionEstadias> listaEstudiantesActivos=listaServicio.listaEstudiantes(id_docente,id_periodo);


        if(listaEstudiantesActivos !=null && !listaEstudiantesActivos.isEmpty()){
            req.setAttribute("listaEstudiantesActivos",listaEstudiantesActivos);
        } else{
            req.setAttribute("mensajeVacio","No tiene ningun Estudiante Registrado");
        }
        req.getRequestDispatcher("WEB-INF/Docente/vista-estudiantes.jsp").forward(req,res);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}
