package progetto.view;

import progetto.model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class OperatoreView {

    public static int showMenu() {
        System.out.println("*********************************");
        System.out.println("*    FRUTTASRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa vuoi fare? ***\n");
        System.out.println("1) Visualizza i prodotti disponibili");
        System.out.println("2) Apri ordine");
        System.out.println("3) Aggiungi prodotto ad un ordine");
        System.out.println("4) Rimuovi prodotto da un ordine");
        System.out.println("5) Invia ordine");
        System.out.println("6) Report ordine");
        System.out.println("7) Registra cliente");
        System.out.println("8) Aggiungi contatto");
        System.out.println("9) Visualizza info cliente");
        System.out.println("10) Esci");

        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Seleziona un operazione: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 10) {
                break;
            }
            System.out.println("Opzione non valida");
        }

        return choice;
    }


    public static void stampaListaProdottiDisponibili(ArrayList<Prodotto> lista) {
        System.out.printf("Prodotti disponibili:%n");
        for (Prodotto prodotto : lista) {
            System.out.printf(
                    "Codice Prodotto: %s, Nome: %s, Quantità disponibile: %s chilogrammi, Tipologia: %s, Prezzo/kg: %s €/kg%n",
                    prodotto.getCodiceProdotto(), prodotto.getNomeProdotto(), prodotto.getQuantitàProdotto(), prodotto.getTipoProdotto(),
                    prodotto.getPrezzoProdotto());
        }
    }

    public static Ordine apriOrdineView() throws IOException{
        String via;
        String città;
        int civico;
        String contatto;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Via consegna: ");
        via = reader.readLine();
        System.out.println("Città consegna: ");
        città = reader.readLine();
        System.out.println("Civico consegna: ");
        civico = scan.nextInt();
        System.out.println("Contatto consegna: ");
        contatto = reader.readLine();

        return new Ordine(via, città, civico, contatto);

    }

    public static Composto aggiungiProdotto() throws IOException{
        int prodotto;
        float quantità;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci codice prodotto: ");
        prodotto = scan.nextInt();
        System.out.println("Inserisci quantità desiderata(in chilogrammi): ");
        quantità = scan.nextFloat();

        return new Composto(prodotto, quantità);
    }

    public static void mostraIDOrdine(Ordine ordine) {
        System.out.printf("Aperto ordine %d%n", ordine.getID());
    }

    public static Composto aggiungiProdottoOrdineView() throws IOException{
        int ID;
        int prodotto;
        float quantità;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci ID ordine: ");
        ID = scan.nextInt();
        System.out.println("Inserisci codice prodotto: ");
        prodotto = scan.nextInt();
        System.out.println("Inserisci quantità desiderata(in chilogrammi): ");
        quantità = scan.nextFloat();

        return new Composto(ID, prodotto, quantità);
    }

    public static Composto rimuoviProdottoOrdineView() throws IOException{
        int ordine;
        int prodotto;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci ID ordine da modificare: ");
        ordine = scan.nextInt();
        System.out.println("Indicare il codice del prodotto da rimuovere: ");
        prodotto = scan.nextInt();

        return new Composto(ordine, prodotto);
    }

    public static Ordine inviaOrdineView() throws IOException{
        int ID;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci ID ordine da inviare: ");
        ID = scan.nextInt();

        return new Ordine(ID);
    }

    public static Ordine reportOrdineView() throws IOException{
        int ID;
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserisci ID ordine: ");
        ID = scan.nextInt();

        return new Ordine(ID);
    }
    public static void stampaReport(ReportOrdine report) {
        List<CompostoReport> prodotti = report.getLista();
        for (CompostoReport composto : prodotti) {
            System.out.printf(
                    "Codice Prodotto: %s, Nome: %s, Prezzo/Kg: %s, Quantità: %s chilogrammi, Costo: %s €%n",
                    composto.getCodice(), composto.getNome(), composto.getPrezzoKg(), composto.getQuantità(), composto.getCosto());
        }
        System.out.printf("Costo totale: %s €%n", report.getCostoTotale());
    }

    public static Cliente registraCliente() throws IOException{
        String partitaIVA;
        String nome;
        String viaResidenza;
        String cittàResidenza;
        int civicoResidenza;
        String viaFatturazione;
        String cittàFatturazione;
        int civicoFatturazione;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Partita IVA: ");
        partitaIVA = reader.readLine();
        System.out.println("Nome: ");
        nome = reader.readLine();
        System.out.println("Via residenza: ");
        viaResidenza = reader.readLine();
        System.out.println("Città residenza:  ");
        cittàResidenza = reader.readLine();
        System.out.println("Civico residenza: ");
        civicoResidenza = scan.nextInt();
        System.out.println("Via fatturazione: ");
        viaFatturazione = reader.readLine();
        System.out.println("Città fatturazione:  ");
        cittàFatturazione = reader.readLine();
        System.out.println("Civico fatturazione: ");
        civicoFatturazione = scan.nextInt();

        return new Cliente(partitaIVA, nome, viaResidenza, cittàResidenza,
         civicoResidenza, viaFatturazione, cittàFatturazione, civicoFatturazione);
    }

    public static Contatto registraContatto() throws IOException {
        String recapito;
        String cliente;
        String tipoContatto;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserire recapito: ");
        recapito = reader.readLine();
        System.out.print("Indicare la tipologia del recapito (Mail/Telefono/Cellulare): ");
        tipoContatto = reader.readLine();
        System.out.print("Cliente associato: ");
        cliente = reader.readLine();

        return new Contatto(recapito, cliente, tipoContatto);
    }
    public static Contatto registraPrimoContatto() throws IOException {
        String recapito;
        String tipoContatto;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserire recapito: ");
        recapito = reader.readLine();
        System.out.print("Indicare la tipologia del recapito (Mail/Telefono/Cellulare): ");
        tipoContatto = reader.readLine();

        return new Contatto(recapito, tipoContatto);
    }

    public static Cliente selezionaCliente() throws IOException {
        String cliente;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Inserire partita IVA: ");
        cliente = reader.readLine();

        return new Cliente(cliente);
    }

    public static void mostraInfoClienteView(Cliente cliente) {
        List<Contatto> contatti = cliente.getContatti();
        System.out.printf("Partita Iva: %s; Indirizzo residenza: Via %s %s, %s; Indirizzo fatturazione: Via %s %s, %s%n",
                        cliente.getPartitaIVA(), cliente.getViaResidenza(), cliente.getCivicoResidenza(), cliente.getCittàResidenza(),
                        cliente.getViaFatturazione(), cliente.getCivicoFatturazione(), cliente.getCittàFatturazione());
        for (Contatto contatto : contatti) {
            System.out.printf(
                    "%s: %s%n",
                    contatto.getTipoContatto(), contatto.getRecapito());
        }
    }

    public static void conferma(Boolean conferma){
        if(Boolean.TRUE.equals(conferma)) System.out.println("Azione avvenuta correttamente");
    }
}

