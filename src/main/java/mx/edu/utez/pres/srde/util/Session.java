package mx.edu.utez.pres.srde.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mx.edu.utez.pres.srde.model.BeanAdmin;
import mx.edu.utez.pres.srde.model.BeanDocente;

public class Session {

    public static BeanDocente getDocenteLogueado(HttpServletRequest req) {
        HttpSession sesion = req.getSession(false);
        if (sesion != null) {
            return (BeanDocente) sesion.getAttribute("docenteLogueado");
        }
        return null;
    }

    public static BeanAdmin getAdminLogueado(HttpServletRequest req) {
        HttpSession sesion = req.getSession(false);
        if (sesion != null) {
            return (BeanAdmin) sesion.getAttribute("adminLogueado");
        }
        return null;
    }
}
