package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.service.ServicioListaEstudiante;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "servletEstudiantesAdmin", value = "/servlet-estudiantes-admin")
public class ServletEstudiantesAdmin extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ServicioListaEstudiante servicio = new ServicioListaEstudiante();
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        int idPeriodo = 0;
        String idPeriodoParam = req.getParameter("id_periodo");
        if (idPeriodoParam != null && !idPeriodoParam.isEmpty()) {
            BeanPeriodo periodoSeleccionado = servicioPeriodos.buscarPeriodo(idPeriodoParam);
            if (periodoSeleccionado != null) {
                idPeriodo = periodoSeleccionado.getId_periodo();
            }
        }
        if (idPeriodo == 0) {
            BeanPeriodo periodoAutomatico = servicioPeriodos.automatizacionPeriodos();
            if (periodoAutomatico != null) {
                idPeriodo = periodoAutomatico.getId_periodo();
            }
        }
        List<BeanAsignacionEstadias> listaTodosLosEstudiantes=servicio.listaTodosLosEstudiantes(idPeriodo);
        req.setAttribute("listaTodosLosEstudiantes", listaTodosLosEstudiantes);
        req.getRequestDispatcher("WEB-INF/Admin/lista-estudiantes.jsp").forward(req, res);
    }

}
