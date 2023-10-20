package progetto.model.domain;

public class IndirizzoSede {

    private String città;
    private String via;
    private int civico;
    private int fornitore;

    public IndirizzoSede(String città, String via, int civico, int fornitore) {
        this.città = città;
        this.via = via;
        this.civico = civico;
        this.fornitore = fornitore;
    }

    public IndirizzoSede(String città, String via, int civico) {
        this.città = città;
        this.via = via;
        this.civico = civico;
    }
    public String getCittà() { return città; }
    public String getVia() { return via; }
    public int getCivico() { return civico; }
    public int getFornitore() {return fornitore; }

}