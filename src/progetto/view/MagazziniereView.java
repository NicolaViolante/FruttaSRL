package progetto.view;

import progetto.model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Scanner;
public class MagazziniereView {

    public static int showMenu() {
        System.out.println("*********************************");
        System.out.println("*    FRUTTASRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa vuoi fare? ***\n");
        System.out.println("1) Visualizza ordini da spedire");
        System.out.println("2) Mostra prodotti in un ordine");
        System.out.println("3) Finalizza ordine");
        System.out.println("4) Effettua richiesta ad un fornitore");
        System.out.println("5) Report prodotti in scadenza");
        System.out.println("6) Registra rifornimento");
        System.out.println("7) Visualizza fornitori che hanno un determinato prodotto");
        System.out.println("8) Visualizza richieste ai fornitori dell'ultima settimana");
        System.out.println("9) Esci");

        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Seleziona un operazione: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 9) {
                break;
            }
            System.out.println("Opzione non valida");
        }

        return choice;
    }

    public static void visualizzaOrdiniDaSpedireView(ArrayList<Ordine> lista) {
        for (Ordine ordine : lista) {
            System.out.printf(
                    "Ordini da spedire:%n(%s): Via %s %s, %s. Recapito associato: %s%n",
                    ordine.getID(), ordine.getVia(), ordine.getCivico(), ordine.getCittà(), ordine.getContatto());
        }
    }

    public static Ordine selezionaOrdine() throws IOException {

        int ordine;

        Scanner scan = new Scanner(System.in);
        System.out.printf("Selezionare ordine da visualizzare%n");
        ordine = scan.nextInt();

        return new Ordine(ordine);
    }

    public static void visualizzaProdottiOrdineView(ArrayList<Prodotto> lista) {

        System.out.printf("L'ordine deve contenere:%n");

        for (Prodotto prodotto : lista) {
            System.out.printf(
                    "%s(codice:%s), chilogrammi %s%n",
                    prodotto.getNomeProdotto(), prodotto.getCodiceProdotto(), prodotto.getQuantitàProdotto());
        }
    }

    public static Ordine finalizzaOrdineView() throws IOException{
        int ID;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci ID ordine da finalizzare: ");
        ID = scan.nextInt();

        return new Ordine(ID);
    }

    public static Rifornimento effettuaRichiestaFornitoreView() throws IOException{
        int prodotto;
        int fornitore;
        float quantità;

        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codice prodotto: ");
        prodotto = scan.nextInt();
        System.out.println("Inserisci codice fornitore: ");
        fornitore = scan.nextInt();
        System.out.println("Selezionare la quantità(in chilogrammi): ");
        quantità = scan.nextFloat();

        return new Rifornimento(prodotto, fornitore, quantità);
    }

    public static void reportProdottiInScadenzaView(ArrayList<ProdottoInMagazzino> lista) {
        System.out.printf("Prodotti in scadenza:%n");
        for (ProdottoInMagazzino prodotto : lista) {
            System.out.printf(
                    "Codice Prodotto: %s, Nome: %s, Quantità residua: %s, Scadenza: %s%n",
                    prodotto.getCodiceProdotto(), prodotto.getNomeProdotto(), prodotto.getGiacenza(), prodotto.getScadenza());
        }
    }

    public static ProdottoInMagazzino registraRifornimentoView() throws IOException{
        int prodotto;
        Date scadenza;
        String temp;
        float quantità;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codice prodotto: ");
        prodotto = scan.nextInt();
        System.out.println("Inserisci quantità prodotto(in chilogrammi): ");
        quantità = scan.nextFloat();
        System.out.println("Indicare data di scadenza(YYYY-MM-DD): ");
        temp = reader.readLine();
        scadenza = Date.valueOf(temp);


        return new ProdottoInMagazzino(prodotto, quantità, scadenza);
    }

    public static Prodotto selezionaProdotto() throws IOException{
        int codice;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codice prodotto: ");
        codice = scan.nextInt();

        return new Prodotto(codice);
    }

    public static void visualizzaFornitoriProdottoView(ArrayList<Fornitore> lista) {
        System.out.printf("Prodotto disponibile presso:%n");
        for (Fornitore fornitore : lista) {
            System.out.printf(
                    "%s, codice fornitore: %s%n",
                    fornitore.getNomeFornitore(), fornitore.getCodiceFornitore());
        }
    }

    public static void visualizzaRichiesteFornitoriUltimaSettimanaView(ArrayList<Rifornimento> lista) {
        System.out.printf("Richieste ai fornitori dell'ultima settimana:%n");
        for (Rifornimento rifornimento : lista) {
            System.out.printf(
                    "Data: %s, Prodotto: %s(%s), Quantità: %s chilogrammi, Fornitore: %s(%s)%n",
                    rifornimento.getData(), rifornimento.getNomeProdotto(), rifornimento.getCodiceProdotto(),
                    rifornimento.getQuantità(), rifornimento.getNomeFornitore(), rifornimento.getCodiceFornitore());
        }
    }

    public static void conferma(Boolean conferma){
        if(Boolean.TRUE.equals(conferma)) System.out.println("Azione avvenuta correttamente");
    }
}
