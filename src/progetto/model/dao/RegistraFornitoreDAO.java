package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.Fornitore;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistraFornitoreDAO implements GenericProcedureDAO<Fornitore>{
    @Override
    public Fornitore execute(Object ... params) throws DAOException {
        String nome = (String) params[0];
        String codiceFiscale= (String) params[1];
        String città = (String) params[2];
        String via = (String) params[3];
        int civico = (int) params[4];
        int codice;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_fornitore(?,?,?,?,?,?)}");
            cs.setString(1, nome);
            cs.setString(2, codiceFiscale);
            cs.setString(3, città);
            cs.setString(4, via);
            cs.setInt(5, civico);
            cs.executeQuery();
            codice = cs.getInt(6);

        } catch(SQLException e) {
            throw new DAOException("Errore aggiunta fornitore: " + e.getMessage());
        }
        return new Fornitore(codice);
    }
}
