package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanArchivo;
import mx.edu.utez.pres.srde.model.BeanDocente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;
@WebServlet(name = "servletModificarObservacion", value = "/servlet-modificar-observacion")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class ServletModificarObservacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Acentos y caracteres especiales

        try {
            // 1. Recibir datos del formulario
            int idAsignacion = Integer.parseInt(request.getParameter("idAsignacion"));
            String matricula = request.getParameter("matricula");
            int idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc"));
            String observaciones = request.getParameter("observaciones");

            // 2. Identificar el usuario logueado en la sesión
            HttpSession session = request.getSession();
            int idUsuarioModificador = 0;

            BeanDocente docenteLogueado = (BeanDocente) session.getAttribute("docenteLogueado");
            BeanAdmin adminLogueado = (BeanAdmin) session.getAttribute("adminLogueado");

            if (docenteLogueado != null) {
                idUsuarioModificador = docenteLogueado.getId();
            } else if (adminLogueado != null) {
                idUsuarioModificador = adminLogueado.getId();
            } else {
                System.err.println("Error: No se encontró sesión activa de Admin ni Docente.");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }

            // 3. Evaluar acciones de "marcar" o "desmarcar" como revisado
            String accion = request.getParameter("accion");
            ServicioDocumento servicio = new ServicioDocumento();

            if ("desmarcar".equals(accion)) {
                boolean exitoDesmarcar = servicio.procesoDesmarcarRevisado(idAsignacion, idTipoDoc);
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);
                return;
            } else if ("marcar".equals(accion)) {
                boolean exitoMarcar = servicio.procesoMarcarRevisado(idAsignacion, idTipoDoc);
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);
                return;
            }

            // 4. Evaluar si subió un nuevo PDF para reemplazar o solo texto
            Part nuevoArchivoPart = request.getPart("nuevoArchivoPDF");
            boolean exito = false;

            if (nuevoArchivoPart != null && nuevoArchivoPart.getSize() > 0) {
                // CASO A: Viene un archivo nuevo -> Armamos el Bean
                BeanArchivo beanArchivo = new BeanArchivo();
                beanArchivo.setNombre_archivo(nuevoArchivoPart.getSubmittedFileName());
                beanArchivo.setTamano(nuevoArchivoPart.getSize());
                beanArchivo.setContenido_archivo(nuevoArchivoPart.getInputStream());

                // ¡AQUÍ ESTÁ EL CAMBIO! Se manda llamar al método que guarda el nuevo PDF
                exito = servicio.procesoModificarObservaciones(idAsignacion, idTipoDoc, observaciones, idUsuarioModificador);

            } else {
                // CASO B: Solo se actualizaron las observaciones en texto
                exito = servicio.procesoModificarObservaciones(idAsignacion, idTipoDoc, observaciones, idUsuarioModificador);
            }

            // 5. Redireccionar devolviendo el control al perfil del alumno
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);

        } catch (Exception e) {
            e.printStackTrace(); // Ver el error detallado en la consola del servidor
            response.sendRedirect(request.getContextPath() + "/servlet-admin-estudiantes");
        }
    }
}