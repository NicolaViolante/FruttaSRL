package progetto.model.domain;

import java.util.ArrayList;
import java.util.List;


public class Cliente {
    private String partitaIVA;
    private String nome;
    private String viaResidenza;
    private String cittàResidenza;
    private int civicoResidenza;
    private String viaFatturazione;
    private String cittàFatturazione;
    private int civicoFatturazione;
    List<Contatto> contatti = new ArrayList<>();


    public Cliente(String partitaIVA, String nome, String viaResidenza, String cittàResidenza,
                   int civicoResidenza, String viaFatturazione, String cittàFatturazione, int civicoFatturazione) {

        this.partitaIVA = partitaIVA;
        this.nome = nome;
        this.viaResidenza = viaResidenza;
        this.cittàResidenza = cittàResidenza;
        this.civicoResidenza = civicoResidenza;
        this.viaFatturazione = viaFatturazione;
        this.cittàFatturazione = cittàFatturazione;
        this.civicoFatturazione = civicoFatturazione;
    }

    public Cliente(String partitaIVA){
        this.partitaIVA = partitaIVA;
    }



    public String getPartitaIVA() { return partitaIVA; }

    public String getNome() {
        return nome;
    }

    public String getViaResidenza() { return viaResidenza; }

    public String getCittàResidenza() { return cittàResidenza; }

    public int getCivicoResidenza() { return civicoResidenza; }
    public String getViaFatturazione() { return viaFatturazione; }

    public String getCittàFatturazione() { return cittàFatturazione; }
    public List<Contatto> getContatti() { return contatti; }

    public int getCivicoFatturazione() { return civicoFatturazione; }
    public void addContatto(Contatto contatto){
        this.contatti.add(contatto);
    }
}
