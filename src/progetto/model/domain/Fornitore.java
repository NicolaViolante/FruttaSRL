package progetto.model.domain;

public class Fornitore {

    private int codice;
    private String nome;
    private String codiceFiscale;


    public Fornitore(int codice, String nome, String codiceFiscale) {
        this.codice = codice;
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
    }

    public Fornitore(int codice, String nome) {
        this.codice = codice;
        this.nome = nome;

    }

    public Fornitore( String nome, String codiceFiscale) {
        this.nome = nome;
        this.codiceFiscale = codiceFiscale;
    }

    public Fornitore(int codice) {
        this.codice = codice;
    }
    public int getCodiceFornitore() { return codice; }

    public String getNomeFornitore() {
        return nome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

}
