package progetto.model.domain;

import java.sql.Date;

public class ProdottoInMagazzino {

    private int prodotto;
    private Date scadenza;
    private float giacenza;
    String nome;



    public ProdottoInMagazzino(int prodotto, float giacenza, Date scadenza) {
        this.prodotto = prodotto;
        this.scadenza = scadenza;
        this.giacenza = giacenza;
    }

    public ProdottoInMagazzino(int prodotto, float giacenza, Date scadenza, String nome) {
        this.prodotto = prodotto;
        this.scadenza = scadenza;
        this.giacenza = giacenza;
        this.nome = nome;
    }

    public int getCodiceProdotto() { return prodotto; }

    public Date getScadenza() {
        return scadenza;
    }

    public float getGiacenza() {
        return giacenza;
    }
    public String getNomeProdotto() {
        return nome;
    }


}
