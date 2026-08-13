package mx.edu.utez.pres.srde.service;

import mx.edu.utez.pres.srde.dao.DaoArchivo;
import mx.edu.utez.pres.srde.model.BeanArchivo;

import java.util.List;
public class ServicioDocumento {

    private DaoArchivo daoArchivo;

    public ServicioDocumento(){
        // Inicializa el dao
        this.daoArchivo = new DaoArchivo();
    }

    public boolean procesoSubirDocumento(BeanArchivo beanArchivo, int idAsignacion, int idTipoDoc, String estado, String observaciones, int idUsuarioModificador) {

        // Registramos el archivo físico y obtenemos el id
        int idArchivoGenerado = daoArchivo.registrarDocumento(beanArchivo);

        // Se revisa si se guardó en oracle
        if(idArchivoGenerado > 0 ){
            // Registramos el control del documento porque está vinculado al id generado
            boolean exitoControl = daoArchivo.registrarDetallesDocumento(
                    idAsignacion, idTipoDoc, idArchivoGenerado, estado, observaciones, idUsuarioModificador
            );
            return exitoControl;
        }
        return false;
    }

    // Método para cambiar la columna REVISADO
    public boolean procesoMarcarRevisado(int idAsignacion, int idTipoDoc) {
        return daoArchivo.marcarComoRevisado(idAsignacion, idTipoDoc);
    }

    // Método para obtener los documentos de un estudiante usando su matrícula
    public List<BeanArchivo> obtenerDocumentosPorMatricula(String matricula) {
        return daoArchivo.consultarDocumentosPorMatricula(matricula);
    }

    public void inicializarDocumento(int idAsignacion, int idDocente){
        DaoArchivo iniciArchivo = new DaoArchivo();
        List<Integer> idsTiposDocumento = iniciArchivo.obtenerTodosIds();

        for (int id_tipo_archivo : idsTiposDocumento){
            iniciArchivo.insertarControlDocPendiente(idAsignacion, id_tipo_archivo, idDocente);
        }
    }

    // Método puente para "Eliminar" (resetear a Pendiente)
    public boolean procesoEliminarDocumento(int idAsignacion, int idTipoDoc, int idUsuarioModificador) {
        return daoArchivo.eliminarDocumento(idAsignacion, idTipoDoc, idUsuarioModificador);
    }

    // Método puente para Modificar Observaciones
    public boolean procesoModificarObservaciones(int idAsignacion, int idTipoDoc, String observaciones, int idUsuarioModificador) {
        if (observaciones == null) {
            observaciones = "";
        }
        return daoArchivo.modificarObservaciones(idAsignacion, idTipoDoc, observaciones, idUsuarioModificador);
    }

    public boolean procesoDesmarcarRevisado(int idAsignacion, int idTipoDoc) {
        return daoArchivo.desmarcarRevisado(idAsignacion, idTipoDoc);
    }

    public BeanArchivo obtenerArchivo(int idArchivo) {
        return daoArchivo.obtenerArchivoPorId(idArchivo);
    }
}
