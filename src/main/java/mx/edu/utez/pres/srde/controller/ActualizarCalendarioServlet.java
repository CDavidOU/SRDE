package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanPeriodo;
import mx.edu.utez.pres.srde.model.CalendarioBean;
import mx.edu.utez.pres.srde.service.CalendarioServicio;
import mx.edu.utez.pres.srde.service.ServicioPeriodos;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ActualizarCalendariosServlet",value = "/actualizarCalendarioServlet")
public class ActualizarCalendarioServlet extends HttpServlet {

        // las estaremos reutilizando por ello las creamos de esta manera
        private final ServicioPeriodos servicioPeriodos = new ServicioPeriodos();
        private final CalendarioServicio servicioCalendario = new CalendarioServicio();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            cargarDatosParaVista(req);
            req.getRequestDispatcher("/WEB-INF/Admin/calendariosProgramados.jsp").forward(req, res);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
            String idCalParam = req.getParameter("idCalendario");
            int idCalendario = (idCalParam != null && !idCalParam.isEmpty()) ? Integer.parseInt(idCalParam) : 0;

            String comentario = req.getParameter("txtComentario");

            String fInicio = req.getParameter("fechaInicio");
            String fFin = req.getParameter("fechaLimite");
            Date fechaInicio = (fInicio != null && !fInicio.isEmpty()) ? Date.valueOf(fInicio) : null;
            Date fechaFin = (fFin != null && !fFin.isEmpty()) ? Date.valueOf(fFin) : null;

            String tipoDocParam = req.getParameter("idTipoDoc");
            int idTipoDoc = (tipoDocParam != null && !tipoDocParam.isEmpty()) ? Integer.parseInt(tipoDocParam) : 0;
            CalendarioBean calendarioIngresado = new CalendarioBean();
            calendarioIngresado.setIdCalendario(idCalendario);
            calendarioIngresado.setComentario(comentario);
            calendarioIngresado.setFechaInicio(fechaInicio);
            calendarioIngresado.setFechaLimite(fechaFin);
            calendarioIngresado.setTipo_doc(idTipoDoc);

            if (idCalendario == 0) {
                req.setAttribute("mensajeError", "Error: Identificador de calendario inválido.");
            } else {
                boolean respuesta = servicioCalendario.actualizarCalendario(calendarioIngresado);

                if (respuesta) {
                    req.setAttribute("mensajeCorrecto", "Se han actualizado correctamente las fechas del documento.");
                } else {
                    req.setAttribute("mensajeError", "Error al actualizar el calendario. Verifique las fechas.");
                }
            }
            //carga las listas y datos que creamos en el metodo de abajo
            cargarDatosParaVista(req);

            req.getRequestDispatcher("/WEB-INF/Admin/calendariosProgramados.jsp").forward(req, res);
        }
        //evitamos usar mas lineas duplicando todo de manera manual, mejor usamos este metodo
        private void cargarDatosParaVista(HttpServletRequest req) {
            BeanPeriodo periodoActivo = servicioPeriodos.automatizacionPeriodos();

            if (periodoActivo != null) {
                int idPeriodo = periodoActivo.getId_periodo();
                List<CalendarioBean> listaProgramada = servicioCalendario.buscarListaProgramada(idPeriodo);

                req.setAttribute("listaProgramada", listaProgramada);
                req.setAttribute("periodoActivo", periodoActivo);
            }
        }
}
