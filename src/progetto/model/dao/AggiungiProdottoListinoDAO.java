package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AggiungiProdottoListinoDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {

        String nome;
        String tipo;
        float prezzo;

        nome = (String) params[0];
        tipo = (String) params[1];
        prezzo = (float) params[2];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call aggiungi_prodotto(?,?,?)}");
            cs.setString(1, nome);
            cs.setString(2, tipo);
            cs.setFloat(3, prezzo);
            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore creazione nuovo prodotto: "+ e.getMessage());
        }
        return true;
    }
}
