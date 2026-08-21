package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;
public class ServicioDatosEstudiantes {

    public BeanEstudiante datosEstudiante(String matricula) {
        DaoDatosEstudiantes daoDatosEstudiantes = new DaoDatosEstudiantes();
        return daoDatosEstudiantes.datosEstudiante(matricula);
    }

    // Envía la matrícula original (para el WHERE) y los nuevos datos
    public boolean actualizarEstudiante(String matriculaOriginal, BeanEstudiante estudiante, int idDocente) {
        DaoDatosEstudiantes daoDatosEstudiantes = new DaoDatosEstudiantes();
        return daoDatosEstudiantes.actualizarEstudiante(matriculaOriginal, estudiante, idDocente);
    }
}
