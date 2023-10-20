package progetto.model.domain;

public class Composto {
    private int ordine;
    private int prodotto;
    private float quantità;

    public Composto(int ordine, int prodotto, float quantità) {

        this.ordine = ordine;
        this.prodotto = prodotto;
        this.quantità = quantità;

    }
    public Composto(int prodotto, float quantità) {

        this.prodotto = prodotto;
        this.quantità = quantità;

    }

    public Composto(int ordine, int prodotto) {

        this.ordine = ordine;
        this.prodotto = prodotto;
    }
    public int getOrdine() { return ordine; }

    public int getProdotto() {
        return prodotto;
    }

    public float getQuantità() { return quantità; }
}
