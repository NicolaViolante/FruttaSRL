package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RimuoviProdottoOrdineDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int ID;
        int prodotto;


        ID = (int) params[0];
        prodotto = (int) params[1];


        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call rimuovi_prodotto_a_ordine(?,?)}");
            cs.setInt(1, ID);
            cs.setInt(2, prodotto);

            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore rimuovi prodotto da ordine: "+ e.getMessage());
        }
        return true;
    }
}
