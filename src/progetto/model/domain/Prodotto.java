package progetto.model.domain;

public class Prodotto {

    private int codice;
    private String nome;
    private float quantità;
    private String tipo;
    private float prezzo;


    public Prodotto(int codice, String nome, float quantità, String tipo, float prezzo) {
        this.codice = codice;
        this.nome = nome;
        this.quantità = quantità;
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    public Prodotto(int codice, float prezzo) {
        this.codice = codice;
        this.prezzo = prezzo;
    }

    public Prodotto(String nome, String tipo, float prezzo) {
        this.nome = nome;
        this.tipo = tipo;
        this.prezzo = prezzo;
    }

    public Prodotto(int codice, String nome, float quantità) {
        this.nome = nome;
        this.codice = codice;
        this.quantità = quantità;
    }

    public Prodotto(int codice) {
        this.codice = codice;
    }

    public int getCodiceProdotto() { return codice; }

    public String getNomeProdotto() {
        return nome;
    }

    public float getQuantitàProdotto() {
        return quantità;
    }

    public String getTipoProdotto() {
        return tipo;
    }

    public float getPrezzoProdotto() {return prezzo;}

}
