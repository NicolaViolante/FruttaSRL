package progetto.controller;

import progetto.exception.DAOException;
import progetto.model.dao.*;
import progetto.model.domain.*;
import progetto.view.OperatoreView;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class OperatoreController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.OPERATORE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean exitNow = false;
        while (!exitNow) {
        try {
            int choice;
            choice = OperatoreView.showMenu();
            switch (choice) {
                case 1 -> visualizzaProdottiDisponibili();
                case 2 -> apriOrdine();
                case 3 -> aggiungiProdottoOrdine();
                case 4 -> rimuoviProdottoOrdine();
                case 5 -> inviaOrdine();
                case 6 -> reportOrdine();
                case 7 -> registraCliente();
                case 8 -> aggiungiContatto();
                case 9 -> mostraInfoCliente();
                case 10 -> exitNow = true;
                default -> throw new RuntimeException("Invalid choice");
            }
        }   catch (Exception e) {
            System.out.println(e);
        }
        }
    }

    public void visualizzaProdottiDisponibili(){
        ArrayList<Prodotto> listaProdottiDisponibili;
        try {
            listaProdottiDisponibili = new VisualizzaProdottiDisponibiliDAO().execute();
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        OperatoreView.stampaListaProdottiDisponibili(listaProdottiDisponibili);

    }

    public void apriOrdine() {
        Ordine info;
        Ordine ordine;
        Composto composto;
        try {
            info = OperatoreView.apriOrdineView();
            composto = OperatoreView.aggiungiProdotto();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            ordine = new ApriOrdineDAO().execute(info.getVia(), info.getCittà(),
                    info.getCivico(), info.getContatto(), composto.getProdotto(), composto.getQuantità());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        if(ordine != null) OperatoreView.mostraIDOrdine(ordine);
    }

    public void aggiungiProdottoOrdine(){
        Composto composto;
        Boolean conferma;
        try{
            composto = OperatoreView.aggiungiProdottoOrdineView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new AggiungiProdottoOrdineDAO().execute(composto.getOrdine(), composto.getProdotto(), composto.getQuantità());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);
    }

    public void rimuoviProdottoOrdine(){
        Composto composto;
        Boolean conferma;
        try{
            composto = OperatoreView.rimuoviProdottoOrdineView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new RimuoviProdottoOrdineDAO().execute(composto.getOrdine(), composto.getProdotto());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);
    }

    public void inviaOrdine(){
        Ordine ordine;
        Boolean conferma;
        try{
            ordine = OperatoreView.inviaOrdineView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new InviaOrdineDAO().execute(ordine.getID());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);
    }

    public void reportOrdine(){
        Ordine ordine;
        ReportOrdine report;
        try{
            ordine = OperatoreView.reportOrdineView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            report = new ReportOrdineDAO().execute(ordine.getID());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        OperatoreView.stampaReport(report);
    }
    public void registraCliente(){
        Cliente cliente;
        Contatto contatto;
        Boolean conferma;
        try{
            cliente = OperatoreView.registraCliente();
            contatto = OperatoreView.registraPrimoContatto();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            conferma = new RegistraClienteDAO().execute(cliente.getPartitaIVA(), cliente.getNome(), cliente.getViaResidenza(),
                    cliente.getCittàResidenza(), cliente.getCivicoResidenza(), cliente.getViaFatturazione(),
                    cliente.getCittàFatturazione(), cliente.getCivicoFatturazione(), contatto.getRecapito(), contatto.getTipoContatto());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);
    }

    public void aggiungiContatto(){
        Contatto contatto;
        Boolean conferma;
        try {
            contatto = OperatoreView.registraContatto();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
           conferma =  new AggiungiContattoDAO().execute(contatto.getRecapito(), contatto.getCliente(), contatto.getTipoContatto());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);

    }

    public void mostraInfoCliente() {
        Cliente temp;
        Cliente cliente;
        try {
            temp = OperatoreView.selezionaCliente();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            cliente = new mostraInfoClienteDAO().execute(temp.getPartitaIVA());

        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.mostraInfoClienteView(cliente);

    }
}