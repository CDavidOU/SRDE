package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoRegistroDocente;
import mx.edu.utez.pres.srde.model.BeanDocente;
import mx.edu.utez.pres.srde.model.BeanUsuario;

public class ServicioRegistroDocente {
    private DaoRegistroDocente dao = new DaoRegistroDocente();
    public String registrarTodoElDocente(BeanDocente nuevoDocente, BeanUsuario docenteUsuario) {
        if (dao.existeCorreo(nuevoDocente.getCorreo())) {
            return "EXISTE";
        }
        int idUsuarioGenerado = dao.registrarUsuario(docenteUsuario, nuevoDocente);
        if (idUsuarioGenerado > 0) {
            boolean docenteRegistrado = dao.registrarDocente(nuevoDocente, idUsuarioGenerado);
            if (docenteRegistrado) {
                return "EXITO";
            }
        }

        return "ERROR";
    }
}
