package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoDatosEstudiantes;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

public class ServicioDatosEstudiantes {
    public BeanEstudiante datosEstudiante(String matricula){
        DaoDatosEstudiantes daoDatosEstudiantes = new DaoDatosEstudiantes();
         return daoDatosEstudiantes.datosEstudiante(matricula);
    }

}
