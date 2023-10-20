package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class RegistraRifornimentoDAO implements GenericProcedureDAO<Boolean> {
    @Override
    public Boolean execute(Object... params) throws DAOException {
        int prodotto;
        Date scadenza;
        float quantità;


        prodotto = (int) params[0];
        scadenza = (Date) params[1];
        quantità = (float) params[2];


        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_rifornimento(?,?,?)}");
            cs.setInt(1, prodotto);
            cs.setDate(2, scadenza);
            cs.setFloat(3, quantità);

            cs.executeQuery();

        } catch (SQLException e) {
            throw new DAOException("Errore registrazione rifornimento: " + e.getMessage());
        }
        return true;
    }
}