package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ModificaPrezzoKgDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {

        int prodotto;
        float prezzo;

        prodotto = (int) params[0];
        prezzo = (float) params[1];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call modifica_prezzoKg(?,?)}");
            cs.setInt(1, prodotto);
            cs.setFloat(2, prezzo);
            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore modifica prezzo/Kg: "+ e.getMessage());
        }
        return true;
    }
}
