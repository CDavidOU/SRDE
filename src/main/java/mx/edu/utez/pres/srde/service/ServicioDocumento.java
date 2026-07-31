package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoArchivo;
import mx.edu.utez.pres.srde.model.BeanArchivo;

import java.util.List;

public class ServicioDocumento {

    private DaoArchivo daoArchivo;

    public ServicioDocumento(){
        //Inicializa el dao
        this.daoArchivo = new DaoArchivo();
    }

    public boolean procesoSubirDocumento(BeanArchivo beanArchivo,int idAsignacion, int idTipoDoc, String estado, String observaciones, int idUsuarioModificador) {

        //Registrramos el archivo fisico y obetenemos el id
        int idArchivoGenerado = daoArchivo.registrarDocumento(beanArchivo);

        //Se rebizo si se gguarda en oracle
        if(idArchivoGenerado > 0 ){

            //Registramos el control del documento porque esta vinculado el id generado
            boolean exitoControl = daoArchivo.registrarDetallesDocumento(
                    idAsignacion, idTipoDoc, idArchivoGenerado, estado, observaciones, idUsuarioModificador
            );
            return exitoControl;
        }
        return false;
    }
    // Método para obtener los documentos de un estudiante usando su matrícula
    public List<BeanArchivo> obtenerDocumentosPorMatricula(String matricula) {
        // Delegamos la búsqueda al DAO
        return daoArchivo.consultarDocumentosPorMatricula(matricula);
    }

    public void inicializarDocumento(int idAsignacion, int idDocente){
        DaoArchivo iniciArchivo = new DaoArchivo();
        List<Integer> idsTiposDocumento = iniciArchivo.obtenerTodosIds();

        for (int id_tipo_archivo : idsTiposDocumento){
            iniciArchivo.insertarControlDocPendiente(idAsignacion, id_tipo_archivo, idDocente);
        }
    }

}
