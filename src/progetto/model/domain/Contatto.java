package progetto.model.domain;

public class Contatto {
    private String recapito;
    private String cliente;
    private String tipoContatto;

    public Contatto(String recapito, String tipoContatto) {
        this.recapito = recapito;
        this.tipoContatto = tipoContatto;
    }

    public Contatto(String recapito, String cliente, String tipoContatto) {
        this.recapito = recapito;
        this.cliente = cliente;
        this.tipoContatto = tipoContatto;
    }

    public String getRecapito(){
        return recapito;
    }

    public String getTipoContatto() {
        return tipoContatto;
    }

    public String getCliente() { return cliente; }


}