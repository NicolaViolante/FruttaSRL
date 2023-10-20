package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.ProdottoInMagazzino;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReportProdottiInScadenzaDAO implements GenericProcedureDAO<ArrayList<ProdottoInMagazzino>>{

    @Override
    public ArrayList<ProdottoInMagazzino> execute(Object... params) throws DAOException {
        ArrayList<ProdottoInMagazzino> lista = new ArrayList<>();
        ProdottoInMagazzino prodotto;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call report_prodotti_in_scadenza()}");
            boolean status = cs.execute();
            if(status){
                ResultSet rs = cs.getResultSet();
                while (rs.next()){
                    prodotto = new ProdottoInMagazzino(rs.getInt(1), rs.getFloat(2), rs.getDate(3),
                            rs.getString(4));
                    lista.add(prodotto);
                }
            }
        }catch(SQLException e){
            throw new DAOException("Errore report prodotti in scadenza: " + e.getMessage());
        }
        return lista;
    }
}