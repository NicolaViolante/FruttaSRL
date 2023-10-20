package progetto.model.domain;

import java.util.ArrayList;
import java.util.List;

public class ReportOrdine {

    List<CompostoReport> prodotto = new ArrayList<>();
    float costoTotale;


    public void addSpecieToOrderReport(CompostoReport composto) {
        this.prodotto.add(composto);
    }

    public void addQuantitaPrezzoTotali(float costoTotale) {
        this.costoTotale = costoTotale;
    }

    public List<CompostoReport> getLista() { return prodotto; }
    public float getCostoTotale() { return costoTotale; }
}