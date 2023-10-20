package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class EffettuaRichiestaFornitoreDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object... params) throws DAOException {
        int fornitore;
        int prodotto;
        float quantità;


        fornitore = (int) params[0];
        prodotto = (int) params[1];
        quantità = (float) params[2];


        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call effettua_richiesta_fornitore(?,?,?)}");
            cs.setInt(1, fornitore);
            cs.setInt(2, prodotto);
            cs.setFloat(3, quantità);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Errore richiesta al fornitore: " + e.getMessage());
        }
        return true;
    }
}