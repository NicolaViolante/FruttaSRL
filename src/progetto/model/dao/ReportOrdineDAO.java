package progetto.model.dao;

import progetto.exception.DAOException;
import progetto.model.domain.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ReportOrdineDAO implements GenericProcedureDAO<ReportOrdine> {
@Override
public ReportOrdine execute(Object...params)throws DAOException{
        ReportOrdine report=new ReportOrdine();
        int codiceOrdine=(int)params[0];

        try{
        Connection conn=ConnectionFactory.getConnection();
        CallableStatement cs=conn.prepareCall("{call report_ordine(?)}");
        cs.setInt(1,codiceOrdine);
        boolean status=cs.execute();

        if(status){
        ResultSet rs=cs.getResultSet();
        if(status){
        while(rs.next()){
        CompostoReport composto=new CompostoReport(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getFloat(4),rs.getFloat(5));
        report.addSpecieToOrderReport(composto);
        }
        }
        status=cs.getMoreResults();
        if(status){
        rs=cs.getResultSet();
        while(rs.next()){
        report.addQuantitaPrezzoTotali(rs.getFloat(1));
        }
        }
        }
        }catch(SQLException e){
        throw new DAOException("Errore report ordine: "+e.getMessage());
        }
        return report;
        }
}