package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.Cliente;
import progetto.model.domain.Contatto;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class mostraInfoClienteDAO implements GenericProcedureDAO<Cliente>{
    @Override
    public Cliente execute(Object ... params) throws DAOException {
        String partitaIva = (String) params[0];
        Cliente cliente = null;
        try {
            Connection conn = ConnectionFactory.getConnection();
            CallableStatement cs = conn.prepareCall("{call visualizza_dettagli_cliente(?)}");
            cs.setString(1, partitaIva);
            boolean status = cs.execute();
            if (status) {
                ResultSet rs = cs.getResultSet();
                while (rs.next()) {
                    cliente =  new Cliente(rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7),
                            rs.getInt(8)) ;
                }
            }
            status = cs.getMoreResults();
                if (status) {
                    ResultSet rs = cs.getResultSet();
                    while (rs.next()) {
                        Contatto contatto = new Contatto(rs.getString(1), rs.getString(2));
                        cliente.addContatto(contatto);
                    }
                }

        } catch (SQLException e) {
            throw new DAOException("Errore visualizzazione info cliente: " + e.getMessage());
        }
        return cliente;
    }
}

