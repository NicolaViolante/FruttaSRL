package progetto.model.domain;

public class Disponibilità {

    private int prodotto;
    private int fornitore;

    public Disponibilità(int fornitore, int prodotto) {

        this.prodotto = prodotto;
        this.fornitore = fornitore;
    }

    public int getCodiceProdotto() { return prodotto; }

    public int getCodiceFornitore() {
        return fornitore;
    }
}
