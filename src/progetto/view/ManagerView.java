package progetto.view;

import progetto.model.domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Scanner;
public class ManagerView {

    public static int showMenu() {
        System.out.println("*********************************");
        System.out.println("*    FRUTTASRL DASHBOARD    *");
        System.out.println("*********************************\n");
        System.out.println("*** Cosa vuoi fare? ***\n");
        System.out.println("1) Modifica prezzo/kg di un prodotto");
        System.out.println("2) Aggiungi prodotto a listino");
        System.out.println("3) Registra nuovo fornitore");
        System.out.println("4) Aggiungi indirizzo fornitore");
        System.out.println("5) Aggiungi prodotto del fornitore");
        System.out.println("6) Esci");


        Scanner input = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.print("Seleziona un operazione: ");
            choice = input.nextInt();
            if (choice >= 1 && choice <= 6) {
                break;
            }
            System.out.println("Opzione non valida");
        }

        return choice;
    }

    public static Prodotto modificaPrezzoKgView() throws IOException{

        int prodotto;
        float prezzo;
        Scanner scan = new Scanner(System.in);
        System.out.println("Seleziona il prodotto da modificare: ");
        prodotto = scan.nextInt();
        System.out.println("Inserisci il nuovo prezzo al chilogrammo: ");
        prezzo = scan.nextFloat();

        return new Prodotto(prodotto, prezzo);

    }

    public static Prodotto aggiungiProdottoListinoView() throws IOException{

        String prodotto;
        String tipo;
        float prezzo;
        Scanner scan = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Nome nuovo prodotto: ");
        prodotto = reader.readLine();
        System.out.println("Tipologia prodotto(Frutta/Verdura): ");
        tipo = reader.readLine();
        System.out.println("Inserisci prezzo al chilogrammo: ");
        prezzo = scan.nextFloat();

        return new Prodotto(prodotto, tipo, prezzo);

    }

    public static Fornitore registraFornitoreView() throws IOException {

        String nome;
        String codiceFiscale;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserire nome fornitore: ");
        nome = reader.readLine();
        System.out.println("Inserire codice fiscale fornitore: ");
        codiceFiscale = reader.readLine();

        return new Fornitore(nome, codiceFiscale);
    }

    public static IndirizzoSede registraIndirizzoSedeView() throws IOException {

        String città;
        String via;
        int civico;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire città sede: ");
        città = reader.readLine();
        System.out.println("Inserire via sede: ");
        via = reader.readLine();
        System.out.println("Inserire civico sede: ");
        civico = scan.nextInt();

        return new IndirizzoSede(città, via, civico);
    }

    public static Fornitore selezionaFornitoreView() throws IOException {

        int codice;

        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire codice fornitore: ");
        codice = scan.nextInt();

        return new Fornitore(codice);
    }

    public static void comunicaCodice(Fornitore fornitore) {
        System.out.printf("Fornitore registrato con codice %s%n", fornitore.getCodiceFornitore());
    }

    public static Disponibilità aggiungiProdottoFornitoreView() throws IOException{

        int fornitore;
        int prodotto;

        Scanner scan = new Scanner(System.in);
        System.out.println("Inserire codice fornitore: ");
        fornitore = scan.nextInt();
        System.out.println("Inserire codice prodotto: ");
        prodotto = scan.nextInt();

        return new Disponibilità(fornitore, prodotto);
    }

    public static void conferma(Boolean conferma){
        if(Boolean.TRUE.equals(conferma)) System.out.println("Azione avvenuta correttamente");
    }
}

