package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.Rifornimento;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisualizzaRichiesteFornitoriUltimaSettimanaDAO implements GenericProcedureDAO<ArrayList<Rifornimento>> {

    @Override
    public ArrayList<Rifornimento> execute(Object... params) throws DAOException {
        ArrayList<Rifornimento> lista = new ArrayList<>();
        Rifornimento rifornimento;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call mostra_richieste_ai_fornitori_settimana()}");
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    rifornimento = new Rifornimento(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                            rs.getFloat(4), rs.getDate(5), rs.getString(6), rs.getString(7));
                    lista.add(rifornimento);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Errore visualizza richieste fornitore: " + e.getMessage());
        }
        return lista;
    }
}