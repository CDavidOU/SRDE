package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoRegistroEstudiante;
import mx.edu.utez.pres.srde.model.BeanEstudiante;

public class ServicioRegistroEstudiante {

    public BeanEstudiante registrarEstudiante(BeanEstudiante nuevoEstudiante) {
        System.out.println("-> [SERVICIO] ¡El estudiante llegó al servicio! Pasando al DAO...");

        // Quitamos el IF molesto temporalmente para probar
        DaoRegistroEstudiante daoRegistroEst = new DaoRegistroEstudiante();

        return daoRegistroEst.registrarEstudiante(nuevoEstudiante);
    }
}
