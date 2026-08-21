package mx.edu.utez.pres.srde.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import mx.edu.utez.pres.srde.util.Validador;

public class XssRequestWrapper extends HttpServletRequestWrapper {

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String valor = super.getParameter(name);
        if (valor == null) {
            return null;
        }
        // Limpia scripts y caracteres peligrosos automáticamente
        return Validador.sanitizarTextoLibre(valor);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] valores = super.getParameterValues(name);
        if (valores == null) {
            return null;
        }
        int conteo = valores.length;
        String[] valoresCodificados = new String[conteo];
        for (int i = 0; i < conteo; i++) {
            valoresCodificados[i] = Validador.sanitizarTextoLibre(valores[i]);
        }
        return valoresCodificados;
    }
}