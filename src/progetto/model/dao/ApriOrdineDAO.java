package progetto.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import progetto.exception.DAOException;
import progetto.model.domain.Ordine;
public class ApriOrdineDAO implements GenericProcedureDAO<Ordine>{
    @Override
    public Ordine execute(Object ... params) throws DAOException {
        String via = (String) params[0];
        String città= (String) params[1];
        int civico = (int) params[2];
        String contatto= (String) params[3];
        int prodotto = (int) params[4];
        float quantità = (float) params[5];
        int ID;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call apri_ordine(?,?,?,?,?,?,?)}");
            cs.setString(1, via);
            cs.setString(2, città);
            cs.setInt(3, civico);
            cs.setString(4, contatto);
            cs.setInt(5, prodotto);
            cs.setFloat(6, quantità);
            cs.executeQuery();
            ID = cs.getInt(7);

        } catch(SQLException e) {
            throw new DAOException("Errore apertura ordine: " + e.getMessage());
        }
        return new Ordine(ID, "Aperto", via, città, civico, contatto);
    }
}
