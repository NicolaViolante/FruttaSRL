package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.Fornitore;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisualizzaFornitoriProdottoDAO implements GenericProcedureDAO<ArrayList<Fornitore>>{

    @Override
    public ArrayList<Fornitore> execute(Object... params) throws DAOException {
        ArrayList<Fornitore> lista = new ArrayList<>();
        Fornitore fornitore;
        int ID = (int) params[0];
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_fornitori_che_hanno_prodotto(?)}");
            cs.setInt(1, ID);
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    fornitore = new Fornitore(rs.getInt(1), rs.getString(2));
                    lista.add(fornitore);
                }
            }
        }catch(SQLException e){
            throw new DAOException("Errore visualizza dettagli ordine: " + e.getMessage());
        }
        return lista;
    }
}