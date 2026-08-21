package mx.edu.utez.pres.srde.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
public class AuthFilter extends HttpFilter {

    // 1. Rutas Públicas (Login y Recuperación)
    private static final Set<String> RUTAS_PUBLICAS = Set.of(
            "/",
            "/index.jsp",
            "/servlet-inicio",
            "/olvide-password.jsp",
            "/servlet-enviar-pin",
            "/servlet-restablecer"
    );

    // 2. Rutas Exclusivas de Administrador
    private static final Set<String> RUTAS_ADMIN = Set.of(
            "/actualizar-calendario-servlet",
            "/calendario-servlet",
            "/lista-calendarios-servlet",
            "/servlet-admin-estudiantes",
            "/servlet-admin-grafica",
            "/servlet-admin-periodos",
            "/servlet-datos-docente",
            "/servlet-estudiantes-admin",
            "/servlet-lista-docentes",
            "/servlet-modificar-docente",
            "/servlet-periodos-admin",
            "/servlet-registro-docente"
    );

    // 3. Rutas Exclusivas de Docente (removidos /servlet-subir-documentos y /servlet-modificar-observacion)
    private static final Set<String> RUTAS_DOCENTE = Set.of(
            "/servlet-lista-estudiantes",
            "/servlet-periodos",
            "/servlet-revisar-documento"
    );

    // 4. Rutas Compartidas (Admin y Docente)
    private static final Set<String> RUTAS_COMPARTIDAS = Set.of(
            "/servlet-cambiar-contra",
            "/servlet-datos-estudiante",
            "/servlet-eliminar-documento",
            "/servlet-logout",
            "/servlet-modificar-estudiante",
            "/servlet-modificar-observacion",
            "/servlet-mostrar-notificaciones",
            "/servlet-notificaciones",
            "/servlet-registro-estudiante",
            "/servlet-subir-documentos",
            "/servlet-ver-documento"
    );

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String path = req.getServletPath();
        HttpSession sesion = req.getSession(false);

        boolean esDocente = (sesion != null && sesion.getAttribute("docenteLogueado") != null);
        boolean esAdmin = (sesion != null && sesion.getAttribute("adminLogueado") != null);
        boolean estaAutenticado = esAdmin || esDocente;

        // 1. Archivos estáticos o URLs públicas
        if (esEstatico(path) || RUTAS_PUBLICAS.contains(path)) {
            chain.doFilter(req, res);
            return;
        }

        // 2. Comprobar sesión activa
        if (!estaAutenticado) {
            res.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // Desactivar almacenamiento en caché
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);

        // 3. Validación de permisos
        if (RUTAS_COMPARTIDAS.contains(path)) {
            chain.doFilter(req, res);
            return;
        }

        if (RUTAS_ADMIN.contains(path)) {
            if (esAdmin) {
                chain.doFilter(req, res);
            } else {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: Se requiere rol de Administrador.");
            }
            return;
        }

        if (RUTAS_DOCENTE.contains(path)) {
            if (esDocente) {
                chain.doFilter(req, res);
            } else {
                res.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: Se requiere rol de Docente.");
            }
            return;
        }

        chain.doFilter(req, res);
    }

    private boolean esEstatico(String path) {
        return path.startsWith("/css/")
                || path.startsWith("/js/")
                || path.startsWith("/imagenes/")
                || path.startsWith("/img/")
                || path.startsWith("/assets/")
                || path.endsWith(".css")
                || path.endsWith(".js")
                || path.endsWith(".png")
                || path.endsWith(".jpg")
                || path.endsWith(".svg");
    }
}