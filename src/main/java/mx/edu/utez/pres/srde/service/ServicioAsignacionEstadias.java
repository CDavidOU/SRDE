package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoAsignacionEstadias;
import mx.edu.utez.pres.srde.model.BeanAsignacionEstadias;

public class ServicioAsignacionEstadias {

    public BeanAsignacionEstadias registroAsignacionEstadias(int docente, int periodo,String matricula) {
        DaoAsignacionEstadias daoAsignacion = new DaoAsignacionEstadias();

        int totalEstudiantesAsignados= daoAsignacion.contarEstudiantesAsignados(docente,periodo);
        if(totalEstudiantesAsignados>=10){
            return null;
        }
        BeanAsignacionEstadias asignacionNueva=new BeanAsignacionEstadias();
        asignacionNueva.setId_docente(docente);
        asignacionNueva.setId_periodo(periodo);
        asignacionNueva.setMatricula(matricula);

        return daoAsignacion.registroAsignacionEstadias(asignacionNueva);
    }
}
