package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AggiungiProdottoOrdineDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int ID;
        int prodotto;
        float quantità;


        ID = (int) params[0];
        prodotto = (int) params[1];
        quantità = (float) params[2];


        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_prodotto_a_ordine(?,?,?)}");
            cs.setInt(1, ID);
            cs.setInt(2, prodotto);
            cs.setFloat(3, quantità);

            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore aggiungi prodotto a ordine: "+ e.getMessage());
        }
        return true;
    }
}

