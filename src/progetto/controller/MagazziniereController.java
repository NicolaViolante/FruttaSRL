package progetto.controller;

import progetto.exception.DAOException;
import progetto.model.dao.*;
import progetto.model.domain.*;
import progetto.view.MagazziniereView;
import progetto.view.OperatoreView;



import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MagazziniereController implements Controller {

    @Override
    public void start() {
        try {
            ConnectionFactory.changeRole(Role.MAGAZZINIERE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        boolean exitNow = false;

        while (!exitNow) {

            try {
                int choice;
                choice = MagazziniereView.showMenu();

                switch (choice) {
                    case 1 -> visualizzaOrdiniDaSpedire();
                    case 2-> mostraProdottiOrdine();
                    case 3 -> finalizzaOrdine();
                    case 4 -> effettuaRichiestaFornitore();
                    case 5 -> reportProdottiInScadenza();
                    case 6 -> registraRifornimento();
                    case 7 -> visualizzaFornitoriProdotto();
                    case 8 -> visualizzaRichiesteFornitoriUltimaSettimana();
                    case 9 -> exitNow = true;
                    default -> throw new RuntimeException("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public void visualizzaOrdiniDaSpedire(){
        ArrayList<Ordine> listaOrdini;
        try {
            listaOrdini = new VisualizzaOrdiniDaSpedireDAO().execute();
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        MagazziniereView.visualizzaOrdiniDaSpedireView(listaOrdini);

    }

    public void mostraProdottiOrdine(){
        Ordine ordine;
        ArrayList<Prodotto> listaProdotti;
        try {
            ordine = MagazziniereView.selezionaOrdine();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }try {
            listaProdotti = new MostraProdottiOrdineDAO().execute(ordine.getID());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        MagazziniereView.visualizzaProdottiOrdineView(listaProdotti);

    }

    public void finalizzaOrdine(){
        Ordine ordine;
        Boolean conferma;
        try{
            ordine = MagazziniereView.finalizzaOrdineView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new FinalizzaOrdineDAO().execute(ordine.getID());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        OperatoreView.conferma(conferma);
    }

    public void effettuaRichiestaFornitore(){
        Rifornimento rifornimento;
        Boolean conferma;
        try{
            rifornimento = MagazziniereView.effettuaRichiestaFornitoreView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new EffettuaRichiestaFornitoreDAO().execute(rifornimento.getCodiceFornitore(), rifornimento.getCodiceProdotto(), rifornimento.getQuantità());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        MagazziniereView.conferma(conferma);
    }

    public void reportProdottiInScadenza(){
        ArrayList<ProdottoInMagazzino> prodottiInScadenza;
        try {
            prodottiInScadenza = new ReportProdottiInScadenzaDAO().execute();
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        MagazziniereView.reportProdottiInScadenzaView(prodottiInScadenza);
    }

    public void registraRifornimento(){
        ProdottoInMagazzino prodotto;
        Boolean conferma;
        try{
            prodotto = MagazziniereView.registraRifornimentoView();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            conferma = new RegistraRifornimentoDAO().execute(prodotto.getCodiceProdotto(), prodotto.getScadenza(), prodotto.getGiacenza());
        }
        catch(DAOException e) {
            throw new RuntimeException(e);
        }
        MagazziniereView.conferma(conferma);
    }

    public void visualizzaFornitoriProdotto(){
        ArrayList<Fornitore> listaFornitori;
        Prodotto prodotto;
        try{
            prodotto = MagazziniereView.selezionaProdotto();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        try {
            listaFornitori = new VisualizzaFornitoriProdottoDAO().execute(prodotto.getCodiceProdotto());
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        MagazziniereView.visualizzaFornitoriProdottoView(listaFornitori);
    }

    public void visualizzaRichiesteFornitoriUltimaSettimana(){
        ArrayList<Rifornimento> listaRifornimenti;
        try {
            listaRifornimenti = new VisualizzaRichiesteFornitoriUltimaSettimanaDAO().execute();
        }
        catch(DAOException e){
            throw new RuntimeException(e);
        }
        MagazziniereView.visualizzaRichiesteFornitoriUltimaSettimanaView(listaRifornimenti);

    }
}