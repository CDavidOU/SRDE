package mx.edu.utez.pres.srde.dao;

import mx.edu.utez.pres.srde.model.BeanTipoDocumento;
import mx.edu.utez.pres.srde.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTiposDocumentos {
    public List<BeanTipoDocumento> listaTiposDocumentos()
    {
        List<BeanTipoDocumento> listaTiposDocumentos=new ArrayList<>();
        String sql="select * from tipo_doc";
        try(Connection conexion = Conexion.getConexion();
            PreparedStatement prs = conexion.prepareStatement(sql);
            ResultSet rs=prs.executeQuery();){
            while (rs.next()) {
                BeanTipoDocumento documentos=new BeanTipoDocumento();
                documentos.setId_tipo(rs.getInt("id_tipo_doc"));
                documentos.setNombreDoc(rs.getString("nombre_doc"));
                listaTiposDocumentos.add(documentos);

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTiposDocumentos;
    }
}

