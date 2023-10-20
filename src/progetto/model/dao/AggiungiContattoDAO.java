package progetto.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import progetto.exception.DAOException;


public class AggiungiContattoDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        String recapito;
        String cliente;
        String tipoContatto;

        recapito = (String) params[0];
        cliente= (String) params[1];
        tipoContatto = (String) params[2];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_contatto(?,?,?)}");
            cs.setString(1, recapito);
            cs.setString(2, cliente);
            cs.setString(3, tipoContatto);
            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore aggiunta nuovo contatto: "+ e.getMessage());
        }
        return true;
    }
}
