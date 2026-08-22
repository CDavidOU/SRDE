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
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CalendarioServlet",value = "/servlet-crear-calendario")
public class CalendarioServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        // Carga las listas para los selects
        cargarDatosFormulario(req);
        req.getRequestDispatcher("/WEB-INF/Admin/programar-documentacion.jsp").forward(req, res);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CalendarioBean calendarioIngresado = new CalendarioBean();
        String comentario = req.getParameter("txtComentario");
        Date fechaInicio = Date.valueOf(req.getParameter("fechaInicio"));
        Date fechaFin = Date.valueOf(req.getParameter("fechaLimite"));
        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        String tipoDocParam = req.getParameter("tipoDoc");
        int idTipoDoc = (tipoDocParam != null && !tipoDocParam.isEmpty()) ? Integer.parseInt(tipoDocParam) : 0;

        int idPeriodo = (periodoActivo != null) ? periodoActivo.getId_periodo() : 0;
        calendarioIngresado.setId_periodo(idPeriodo);
        calendarioIngresado.setComentario(comentario);
        calendarioIngresado.setFechaInicio(fechaInicio);
        calendarioIngresado.setFechaLimite(fechaFin);
        calendarioIngresado.setTipo_doc(idTipoDoc);

        String mensaje = "";
        if (idPeriodo == 0 || idTipoDoc == 0) {
            mensaje = "Error: Debe seleccionar un tipo de documento y contar con un periodo activo.";
            req.setAttribute("mensajeError", mensaje);
        } else {
            CalendarioServicio servicioCalendario = new CalendarioServicio();
            boolean calendarioRespuesta = servicioCalendario.registrarCalendario(calendarioIngresado);

            if (calendarioRespuesta) {
                mensaje = "Se ha registrado Correctamente las fechas del documento";
                req.setAttribute("mensajeCorrecto", mensaje);
                req.setAttribute("calendarioRegistrado", calendarioIngresado);
            } else {
                mensaje = "Error al registrar el calendario, calendario ya registrado o coloque correctamente los datos";
                req.setAttribute("mensajeError", mensaje);
            }
        }
        cargarDatosFormulario(req);
        cargarListaProgramada(req);
        req.getRequestDispatcher("/WEB-INF/Admin/calendariosProgramados.jsp").forward(req, resp);

    }
    private void cargarDatosFormulario(HttpServletRequest req) {
        ServicioTiposDocumento servicioTiposDocumento = new ServicioTiposDocumento();
        List<BeanTipoDocumento> listaTiposDocs = servicioTiposDocumento.consultarTiposDocumento();
        req.setAttribute("listaTiposDocs", listaTiposDocs);

        ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();
        req.setAttribute("periodoActivo", periodoActivo);
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
