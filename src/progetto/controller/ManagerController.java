package progetto.controller;

import progetto.exception.DAOException;
import progetto.model.dao.*;
import progetto.model.domain.*;
import progetto.view.ManagerView;



import java.io.IOException;
import java.sql.SQLException;



public class ManagerController implements Controller {
    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.MANAGER);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean exitNow = false;
        while (!exitNow) {
            try {
                int choice;
                choice = ManagerView.showMenu();
                switch (choice) {
                    case 1 -> modificaPrezzoKg();
                    case 2 -> aggiungiProdottoListino();
                    case 3 -> registraFornitore();
                    case 4 -> aggiungiIndirizzoFornitore();
                    case 5 -> aggiungiProdottoFornitore();
                    case 6 -> exitNow = true;
                    default -> throw new RuntimeException("Invalid choice");
                }
        }       catch (Exception e) {
                System.out.println(e);
        }
        }
    }

    public void modificaPrezzoKg() {
        Prodotto prodotto;
        Boolean conferma;
        try {
            prodotto = ManagerView.modificaPrezzoKgView();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new ModificaPrezzoKgDAO().execute(prodotto.getCodiceProdotto(), prodotto.getPrezzoProdotto());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        ManagerView.conferma(conferma);
    }

    public void aggiungiProdottoListino() {
        Prodotto prodotto;
        Boolean conferma;
        try {
            prodotto = ManagerView.aggiungiProdottoListinoView();
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new AggiungiProdottoListinoDAO().execute(prodotto.getNomeProdotto(), prodotto.getTipoProdotto(),
                    prodotto.getPrezzoProdotto());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        ManagerView.conferma(conferma);
    }

    public void registraFornitore(){
            Fornitore fornitore;
            IndirizzoSede indirizzoSede;
            Fornitore codice;
            try{
                fornitore = ManagerView.registraFornitoreView();
                indirizzoSede = ManagerView.registraIndirizzoSedeView();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
            try{
                codice = new RegistraFornitoreDAO().execute(fornitore.getNomeFornitore(), fornitore.getCodiceFiscale(),
                        indirizzoSede.getCittà(), indirizzoSede.getVia(), indirizzoSede.getCivico());
            }catch(DAOException e){
                throw new RuntimeException(e);
            }
            ManagerView.comunicaCodice(codice);
        }

    public void aggiungiIndirizzoFornitore(){
        Fornitore fornitore;
        IndirizzoSede indirizzoSede;
        boolean conferma;
        try{
            fornitore = ManagerView.selezionaFornitoreView();
            indirizzoSede = ManagerView.registraIndirizzoSedeView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            conferma = new AggiungiIndirizzoFornitoreDAO().execute(indirizzoSede.getCittà(), indirizzoSede.getVia(),
                    indirizzoSede.getCivico(), fornitore.getCodiceFornitore());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        ManagerView.conferma(conferma);
    }

    public void aggiungiProdottoFornitore(){
        Disponibilità disponibilità;
        Boolean conferma;
        try{
            disponibilità = ManagerView.aggiungiProdottoFornitoreView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try{
            conferma = new AggiungiProdottoFornitoreDAO().execute(disponibilità.getCodiceFornitore(), disponibilità.getCodiceProdotto());
        }catch(DAOException e){
            throw new RuntimeException(e);
        }
        ManagerView.conferma(conferma);
    }
}