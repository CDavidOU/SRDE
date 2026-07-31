package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;

public class ServicioAsignacionEstadias {

    public int registroAsignacionEstadias(int docente, int periodo, String matricula) {
        DaoAsignacionEstadias daoAsignacion = new DaoAsignacionEstadias();

        int totalEstudiantesAsignados = daoAsignacion.contarEstudiantesAsignados(docente, periodo);
        if (totalEstudiantesAsignados >= 10) {
            return 0; // Retorna 0 si ya alcanzó el límite
        }

        BeanAsignacionEstadias asignacionNueva = new BeanAsignacionEstadias();
        asignacionNueva.setId_docente(docente);
        asignacionNueva.setId_periodo(periodo);
        asignacionNueva.setMatricula(matricula);

        // Esto ahora devolverá el entero del ID generado directamente
        return daoAsignacion.registroAsignacionEstadias(asignacionNueva);
    }
}
