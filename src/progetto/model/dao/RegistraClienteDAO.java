package progetto.model.dao;

import progetto.exception.DAOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistraClienteDAO implements GenericProcedureDAO<Boolean>{
    @Override
    public Boolean execute(Object ... params) throws DAOException {
        String partitaIVA;
        String nome;
        String viaResidenza;
        String cittàResidenza;
        int civicoResidenza;
        String viaFatturazione;
        String cittàFatturazione;
        int civicoFatturazione;
        String recapito;
        String tipoContatto;

        partitaIVA = (String) params[0];
        nome = (String) params[1];
        viaResidenza = (String) params[2];
        cittàResidenza = (String) params[3];
        civicoResidenza = (int) params[4];
        viaFatturazione = (String) params[5];
        cittàFatturazione = (String) params[6];
        civicoFatturazione = (int) params[7];
        recapito = (String) params[8];
        tipoContatto = (String) params[9];

        try{
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call registra_cliente(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, partitaIVA);
            cs.setString(2, nome);
            cs.setString(3, viaResidenza);
            cs.setString(4, cittàResidenza);
            cs.setInt(5, civicoResidenza);
            cs.setString(6, viaFatturazione);
            cs.setString(7, cittàFatturazione);
            cs.setInt(8, civicoFatturazione);
            cs.setString(9, recapito);
            cs.setString(10, tipoContatto);
            cs.executeQuery();

        }catch(SQLException e){
            throw new DAOException("Errore registra cliente "+ e.getMessage());
        }
        return true;
    }
}


