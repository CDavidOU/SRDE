package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.annotation.MultipartConfig;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;

@WebServlet(name = "servletModificarObservacion", value = "/servlet-modificar-observacion")
@MultipartConfig( // ¡ESTO ES NUEVO Y OBLIGATORIO!
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class ServletModificarObservacion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Para que los acentos en las observaciones se guarden bien

        try {
            // 1. Recibir datos de texto del formulario
            int idAsignacion = Integer.parseInt(request.getParameter("idAsignacion"));
            String matricula = request.getParameter("matricula");
            int idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc"));
            String observaciones = request.getParameter("observaciones");

            // 2. Sacar el ID del docente o admin logueado desde el OBJETO de la sesión
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

            String accion = request.getParameter("accion");
            ServicioDocumento servicio = new ServicioDocumento();
            if ("desmarcar".equals(accion)) {
                servicio.procesoDesmarcarRevisado(idAsignacion, idTipoDoc);
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);
                return;
            } else if ("marcar".equals(accion)) {
                servicio.procesoMarcarRevisado(idAsignacion, idTipoDoc);
                response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);
                return;
            }

            // 3. RECIBIR EL POSIBLE ARCHIVO NUEVO
            jakarta.servlet.http.Part nuevoArchivoPart = request.getPart("nuevoArchivoPDF");


            boolean exito = false;

            // 4. LÓGICA DE DECISIÓN (Textos vs. Archivo Nuevo)
            if (nuevoArchivoPart != null && nuevoArchivoPart.getSize() > 0) {

                // CASO A: El usuario seleccionó un archivo nuevo para reemplazar el anterior
                mx.edu.utez.pres.srde.model.BeanArchivo beanArchivo = new mx.edu.utez.pres.srde.model.BeanArchivo();
                beanArchivo.setNombre_archivo(nuevoArchivoPart.getSubmittedFileName());
                beanArchivo.setTamano((int) nuevoArchivoPart.getSize());
                // Usamos el InputStream para optimizar la memoria al subir
                beanArchivo.setContenido_archivo(nuevoArchivoPart.getInputStream());

                // Reutilizamos tu método de subida original.
                // Esto insertará el nuevo archivo en la BD y actualizará las observaciones.
                exito = servicio.procesoSubirDocumento(beanArchivo, idAsignacion, idTipoDoc, "Completado", observaciones, idUsuarioModificador);

            } else {
                // CASO B: El usuario SOLO modificó el texto (La caja del archivo está vacía)
                exito = servicio.procesoModificarObservaciones(idAsignacion, idTipoDoc, observaciones, idUsuarioModificador);
            }

            // 5. Redirigir siempre de vuelta al perfil del estudiante
            response.sendRedirect(request.getContextPath() + "/servlet-datos-estudiante?matricula=" + matricula);

        } catch (Exception e) {
            System.err.println("Error en ServletModificarObservacion: " + e.getMessage());
            // Si algo falla, lo regresamos a la lista general
            response.sendRedirect(request.getContextPath() + "/servlet-admin-estudiantes");
        }
    }
}