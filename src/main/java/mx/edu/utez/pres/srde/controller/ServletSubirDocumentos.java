package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import mx.edu.utez.pres.srde.model.BeanArchivo;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;

@WebServlet(name = "servletSubirDocumentos", value = "/servlet-subir-documentos")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB máximo por archivo
        maxRequestSize = 1024 * 1024 * 50    // 50MB máximo por toda la petición
)
public class ServletSubirDocumentos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1. Recibir los datos del formulario JSP
        int idAsignacion = Integer.parseInt(request.getParameter("idAsignacion"));
        int idTipoDoc = Integer.parseInt(request.getParameter("idTipoDoc"));

        jakarta.servlet.http.HttpSession sesion = request.getSession();
        int idUsuarioModificador = 0;

        // 2. Verificar quién está en la sesión
        if (sesion.getAttribute("adminLogueado") != null) {
            mx.edu.utez.pres.srde.model.BeanAdmin admin = (mx.edu.utez.pres.srde.model.BeanAdmin) sesion.getAttribute("adminLogueado");
            idUsuarioModificador = admin.getId();

        } else if (sesion.getAttribute("docenteLogueado") != null) {
            mx.edu.utez.pres.srde.model.BeanDocente docente = (mx.edu.utez.pres.srde.model.BeanDocente) sesion.getAttribute("docenteLogueado");
            idUsuarioModificador = docente.getId();

        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String estado = request.getParameter("estado");
        String observaciones = request.getParameter("observaciones");

        // 3. Obtener el archivo y armar el Bean
        Part archivoPart = request.getPart("archivoPDF");

        BeanArchivo beanArchivo = new BeanArchivo();
        beanArchivo.setNombre_archivo(archivoPart.getSubmittedFileName());
        beanArchivo.setTamano((int) archivoPart.getSize());

        // ¡LA ÚNICA CORRECCIÓN ESTÁ AQUÍ! (setContenido_archivo con 'r')
        beanArchivo.setContenido_archivo(archivoPart.getInputStream());

        // 4. Instanciar el SERVICIO
        ServicioDocumento servicio = new ServicioDocumento();

        // 5. Ejecutar la lógica de negocio a través del servicio
        boolean exito = servicio.procesoSubirDocumento(
                beanArchivo, idAsignacion, idTipoDoc, estado, observaciones, idUsuarioModificador
        );

        // 6. Responder al usuario
        String matricula = request.getParameter("matricula");
        response.sendRedirect("servlet-datos-estudiante?matricula=" + matricula);
    }
}