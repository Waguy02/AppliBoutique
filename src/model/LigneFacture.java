package model;

import java.io.Serializable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.*;

/**
 * The persistent class for the ligneFacture database table.
 *
 */
@Entity
@Table(name = "ligneFacture")
@NamedQuery(name = "LigneFacture.findAll", query = "SELECT l FROM LigneFacture l")
public class LigneFacture implements Serializable {

    private final FloatProperty prixUnitaire = new SimpleFloatProperty();
    private final IntegerProperty quantite = new SimpleIntegerProperty();
    private LigneFactureId ligneFactureId = new LigneFactureId();

    public LigneFacture() {
    }

    @EmbeddedId
    public LigneFactureId getLigneFactureId() {
        return ligneFactureId;
    }

    public void setLigneFactureId(LigneFactureId ligneFactureId) {
        this.ligneFactureId = ligneFactureId;
    }

    @Column(name = "prixUnitaire", nullable = false)
    public float getPrixUnitaire() {
        return this.prixUnitaire.get();
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire.set(prixUnitaire);
    }

    public FloatProperty prixUnitaireProperty() {
        return this.prixUnitaire;
    }
    @Column(name = "quantite", nullable = false)
    public int getQuantite() {
        return this.quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public IntegerProperty quantiteProperty() {
        return this.quantite;
    }
    
    @Override
    public String toString() {
        return "LigneFacture [prixUnitaire=" + prixUnitaire + ", quantite=" + quantite + ", ligneFactureId="
                + ligneFactureId.toString() + "]";
    }

}
