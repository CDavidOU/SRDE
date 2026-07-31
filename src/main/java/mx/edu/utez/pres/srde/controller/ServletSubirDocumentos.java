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
// ... tus demás importaciones ...

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
        int idUsuarioModificador = Integer.parseInt(request.getParameter("idUsuarioModificador"));
        String estado = request.getParameter("estado"); // 'Pendiente', 'Completado', o 'Sin entregar'
        String observaciones = request.getParameter("observaciones");

        // 2. Obtener el archivo y armar el Bean
        Part archivoPart = request.getPart("archivoPdf");

        BeanArchivo beanArchivo = new BeanArchivo();
        beanArchivo.setNombre_archivo(archivoPart.getSubmittedFileName());
        beanArchivo.setTamano((int) archivoPart.getSize());
        beanArchivo.setContenido_achivo(archivoPart.getInputStream());

        // 3. Instanciar el SERVICIO (Ya no el DAO directamente)
        ServicioDocumento servicio = new ServicioDocumento();

        // 4. Ejecutar la lógica de negocio a través del servicio
        boolean exito = servicio.procesoSubirDocumento(
                beanArchivo, idAsignacion, idTipoDoc, estado, observaciones, idUsuarioModificador
        );

        // 5. Responder al usuario
        if (exito) {
            response.sendRedirect("ruta_de_exito.jsp?mensaje=Documento procesado correctamente");
        } else {
            response.sendRedirect("ruta_de_error.jsp?mensaje=Error al procesar el documento");
        }
    }

}