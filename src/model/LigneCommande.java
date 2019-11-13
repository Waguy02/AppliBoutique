package model;

import java.io.Serializable;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the ligneCommande database table.
 *
 */
@Entity(name = "ligneCommande")
@Table(name = "ligneCommande")
@NamedQuery(name = "LigneCommande.findAll", query = "SELECT l FROM ligneCommande l")
@Getter @Setter
public class LigneCommande implements Serializable {

    private LigneCommandeId ligneCommandeId;
    private final StringProperty fournisseurid = new SimpleStringProperty();
    private final FloatProperty prixUnitaire = new SimpleFloatProperty();
    private final IntegerProperty quantite = new SimpleIntegerProperty();
    private String produitId;
    
    
    public LigneCommande() {
    }

    @EmbeddedId
    public LigneCommandeId getLigneCommandeId() {
        return ligneCommandeId;
    }

    public void setLigneCommandeId(LigneCommandeId ligneCommandeId) {
        this.ligneCommandeId = ligneCommandeId;
    }

    @Column(nullable = false, length = 15)
    public String getFournisseurid() {
        return this.fournisseurid.get();
    }

    public void setFournisseurid(String fournisseurid) {
        this.fournisseurid.set(fournisseurid);
    }
    
    public StringProperty fournisseuridProperty() {
        return this.fournisseurid;
    }
    @Column(nullable = false)
    public float getPrixUnitaire() {
        return this.prixUnitaire.get();
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire.set(prixUnitaire);
    }
    
    public FloatProperty prixUnitaireProperty() {
        return this.prixUnitaire;
    }
    @Column(nullable = false)
    public int getQuantite() {
        return this.quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }
    
    public IntegerProperty quantiteProperty() {
        return this.quantite;
    }
}
