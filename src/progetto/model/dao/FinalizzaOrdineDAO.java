package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class FinalizzaOrdineDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int ID;

        ID = (int) params[0];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call finalizza_ordine(?)}");
            cs.setInt(1, ID);

            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore finalizzazione ordine: "+ e.getMessage());
        }
        return true;
    }
}

