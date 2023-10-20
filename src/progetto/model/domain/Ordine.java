package progetto.model.domain;



public class Ordine {
    private int ID;
    private String stato;
    private String via;
    private String città;
    private int civico;
    private String contatto;

    public Ordine(int ID, String stato, String via, String città, int civico, String contatto) {
        this.ID = ID;
        this.stato = stato;
        this.via = via;
        this.città = città;
        this.civico = civico;
        this.contatto = contatto;
    }

    public Ordine(String via, String città, int civico, String contatto) {
        this.via = via;
        this.città = città;
        this.civico = civico;
        this.contatto = contatto;
    }

    public Ordine(int ID) {
        this.ID = ID;
    }

    public int getID() { return ID; }

    public String getStato() { return stato; }

    public String getVia() { return via; }

    public String getCittà() { return città; }

    public int getCivico() { return civico; }
    public String getContatto() { return contatto; }


}