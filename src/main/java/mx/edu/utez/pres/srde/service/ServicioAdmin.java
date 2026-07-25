package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoAdmin;
import mx.edu.utez.pres.srde.model.BeanAdmin;


public class ServicioAdmin {
    public BeanAdmin datosAdmin(int idAdmin) {
        DaoAdmin daoAdmin = new DaoAdmin();

        return daoAdmin.datosAdmin(idAdmin);
    }
}
