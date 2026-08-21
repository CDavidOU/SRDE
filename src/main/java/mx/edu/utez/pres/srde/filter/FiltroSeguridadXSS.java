package mx.edu.utez.pres.srde.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FiltroSeguridadXSS", urlPatterns = {"/*"})
public class FiltroSeguridadXSS implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest req) {
            // Envuelve la petición para sanitizar cualquier entrada
            XssRequestWrapper reqSegura = new XssRequestWrapper(req);
            chain.doFilter(reqSegura, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}