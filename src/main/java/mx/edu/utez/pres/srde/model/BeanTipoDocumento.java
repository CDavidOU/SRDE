package mx.edu.utez.pres.srde.model;

public class BeanTipoDocumento {
        private int id_tipo;
        private String nombreDoc;

        public int getId_tipo() {
            return id_tipo;
        }

        public void setId_tipo(int id_tipo) {
            this.id_tipo = id_tipo;
        }

        // Getter limpio
        public String getNombreDoc() {
            return this.nombreDoc;
        }

        public void setNombreDoc(String nombreDoc) {
            this.nombreDoc = nombreDoc;
        }
}