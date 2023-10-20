package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AggiungiIndirizzoFornitoreDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        String città = (String) params[0];
        String via= (String) params[1];
        int civico = (int) params[2];
        int codice = (int) params[3];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_indirizzo_fornitore(?,?,?,?)}");
            cs.setString(1, città);
            cs.setString(2, via);
            cs.setInt(3, civico);
            cs.setInt(4, codice);
            cs.executeQuery();

        } catch(SQLException e) {
            throw new DAOException("Errore aggiunta indirizzo sede: " + e.getMessage());
        }
        return true;
    }
}
