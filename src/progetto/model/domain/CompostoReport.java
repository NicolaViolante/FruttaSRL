package progetto.model.domain;

public class CompostoReport {
    private int codice;
    private String nome;
    private float prezzoKg;
    private float quantità;
    private float costo;

    public CompostoReport(int codice, String nome, float prezzoKg, float quantità, float costo) {

        this.codice = codice;
        this.nome = nome;
        this.prezzoKg = prezzoKg;
        this.quantità = quantità;
        this.costo = costo;

    }
    public int getCodice() { return codice; }

    public String getNome() {
        return nome;
    }

    public float getPrezzoKg() { return prezzoKg; }
    public float getQuantità() { return quantità; }
    public float getCosto() {
        return costo;
    }

}