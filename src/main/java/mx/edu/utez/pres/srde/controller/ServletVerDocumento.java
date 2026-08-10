package mx.edu.utez.pres.srde.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.pres.srde.model.BeanArchivo;
import mx.edu.utez.pres.srde.service.ServicioDocumento;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "servletVerDocumento", value = "/servlet-ver-documento")
public class ServletVerDocumento extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String idParam = req.getParameter("idArchivo");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int idArchivo = Integer.parseInt(idParam);

                ServicioDocumento servicio = new ServicioDocumento();
                BeanArchivo archivo = servicio.obtenerArchivo(idArchivo);

                // Verificamos que el archivo y el arreglo de bytes no estén vacíos
                if (archivo != null && archivo.getArchivoBytes() != null) {

                    res.setContentType("application/pdf");
                    res.setHeader("Content-Disposition", "inline; filename=\"" + archivo.getNombre_archivo() + "\"");

                    // Escribimos los bytes en la respuesta del navegador
                    try (OutputStream out = res.getOutputStream()) {
                        out.write(archivo.getArchivoBytes());
                        out.flush();
                    }
                    return;
                }
            } catch (NumberFormatException e) {
                System.err.println("ID de archivo inválido: " + e.getMessage());
            }
        }

        res.sendError(HttpServletResponse.SC_NOT_FOUND, "El documento no se encuentra disponible.");
    }
}