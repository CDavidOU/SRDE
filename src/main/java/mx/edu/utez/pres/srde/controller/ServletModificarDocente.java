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

@WebServlet(name = "servletModificarDocente",value = "/servlet-modificar-docente")
public class ServletModificarDocente extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idParam = req.getParameter("idDocente");

        if (idParam != null && !idParam.isEmpty()) {
            int idDocente = Integer.parseInt(idParam);
            ServicioDocente servicio = new ServicioDocente();
            BeanDocente docenteAEditar = servicio.datosDocente(idDocente);
            req.setAttribute("datoDocente", docenteAEditar);
        }
        req.getRequestDispatcher("WEB-INF/Admin/editar-docente.jsp").forward(req, res);
    }
    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        ServicioDocente servicioEditandoDocente = new ServicioDocente();
        BeanDocente docenteEditado = new BeanDocente();
        docenteEditado.setId(Integer.parseInt(req.getParameter("idDocente")));
        docenteEditado.setNombre(req.getParameter("nombre"));
        docenteEditado.setApellido(req.getParameter("apellidoPaterno"));
        docenteEditado.setCorreo(req.getParameter("correo"));
        docenteEditado.setTelefono(req.getParameter("telefono"));
        docenteEditado.setAcademia(req.getParameter("area"));
        docenteEditado.setCarrera(req.getParameter("carrera"));
        docenteEditado.setEstado(req.getParameter("estado"));

        boolean exito = servicioEditandoDocente.editarDocente(docenteEditado);
        if (exito) {
            List<BeanDocente> listaActualizada = servicioEditandoDocente.listaDocente();
            req.getSession().setAttribute("mensajeExito", "¡Docente actualizado correctamente!");
            req.setAttribute("listaDocentes", listaActualizada);
            req.getRequestDispatcher("WEB-INF/Admin/lista-docentes.jsp").forward(req, res);
        } else {
            req.setAttribute("mensajeError", "No se pudieron guardar los cambios en la base de datos.");
            req.setAttribute("datoDocente", docenteEditado);
            req.getRequestDispatcher("WEB-INF/Admin/datos-docente.jsp").forward(req, res);
        }
}
}
