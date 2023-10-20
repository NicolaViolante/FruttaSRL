package progetto.model.domain;


import java.sql.Date;

public class Rifornimento {

    private int ID;
    private int prodotto;
    private int fornitore;
    private float quantità;
    private Date data;
    private String nomeProdotto;
    private String nomeFornitore;



    public Rifornimento(int ID, int prodotto, int fornitore, float quantità, Date data, String nomeProdotto, String nomeFornitore) {
        this.ID = ID;
        this.prodotto = prodotto;
        this.fornitore = fornitore;
        this.quantità = quantità;
        this.data = data;
        this.nomeProdotto = nomeProdotto;
        this.nomeFornitore = nomeFornitore;
    }

    public Rifornimento(int prodotto, int fornitore, float quantità) {
        this.prodotto = prodotto;
        this.fornitore = fornitore;
        this.quantità = quantità;
    }

    public int getID() { return ID; }

    public int getCodiceProdotto() {
        return prodotto;
    }

    public int getCodiceFornitore() { return fornitore; }

    public float getQuantità() { return quantità; }

    public Date getData() { return data; }

    public String getNomeProdotto() { return nomeProdotto; }
    public String getNomeFornitore() { return nomeFornitore; }
}