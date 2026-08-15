package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.model.BeanTipoDocumento;
import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.service.CalendarioServicio;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;
import mx.edu.utez.pres.srde.service.ServicioTiposDocumento;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListaCalendariosServlet",value = "/listaCalendariosServlet")
public class ListaCalendariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cargarListaProgramada(req);
        req.getRequestDispatcher("/WEB-INF/Admin/calendariosProgramados.jsp").forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idParam = req.getParameter("idCalendario");

        if (idParam != null && !idParam.isEmpty()) {
            int idCalendario = Integer.parseInt(idParam);

            CalendarioServicio servicioCalendario = new CalendarioServicio();
            CalendarioBean calendarioAEditar = servicioCalendario.buscarCalendario(idCalendario);

            req.setAttribute("calendario", calendarioAEditar);
        }
        req.getRequestDispatcher("/WEB-INF/Admin/editarCalendario.jsp").forward(req, res);
    }

    private void cargarListaProgramada(HttpServletRequest req) {
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();

        if (periodoActivo != null) {
            int idPeriodo = periodoActivo.getId_periodo();

            CalendarioServicio servicioCalendario = new CalendarioServicio();
            List<CalendarioBean> listaProgramada = servicioCalendario.buscarListaProgramada(idPeriodo);

            req.setAttribute("listaProgramada", listaProgramada);
            req.setAttribute("periodoActivo", periodoActivo);
        }
    }
}
