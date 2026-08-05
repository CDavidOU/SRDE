package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoTiposDocumentos;
import mx.edu.utez.pres.srde.model.BeanTipoDocumento;

import java.util.List;

public class ServicioTiposDocumento {

    public List<BeanTipoDocumento> consultarTiposDocumento(){
        DaoTiposDocumentos daoConsulta=new DaoTiposDocumentos();
        return daoConsulta.listaTiposDocumentos();
    }
}
