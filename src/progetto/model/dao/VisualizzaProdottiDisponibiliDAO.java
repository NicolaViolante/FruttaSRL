package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.Prodotto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisualizzaProdottiDisponibiliDAO implements GenericProcedureDAO<ArrayList<Prodotto>>{

    @Override
    public ArrayList<Prodotto> execute(Object... params) throws DAOException {
        ArrayList<Prodotto> lista = new ArrayList<>();
        Prodotto prodotto;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_prodotti_disponibili()}");
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    prodotto = new Prodotto(rs.getInt(1), rs.getString(2), rs.getFloat(3),
                            rs.getString(4), rs.getFloat(5));
                    lista.add(prodotto);
                }
            }
        }catch(SQLException e){
            throw new DAOException("Errore visualizza prodotti disponibili: " + e.getMessage());
        }
        return lista;
    }
}