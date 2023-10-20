package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AggiungiProdottoFornitoreDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        int fornitore = (int) params[0];
        int prodotto = (int) params[1];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_prodotto_fornitore(?,?)}");
            cs.setInt(1, fornitore);
            cs.setInt(2, prodotto);
            cs.executeQuery();

        } catch(SQLException e) {
            throw new DAOException("Errore aggiunta prodotto a fornitore: " + e.getMessage());
        }
        return true;
    }
}