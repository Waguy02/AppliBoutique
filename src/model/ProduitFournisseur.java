package model;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.*;

/**
 * The persistent class for the produitFournisseur database table.
 *
 */
@Entity
@Table(name = "produitFournisseur")
@NamedQuery(name = "ProduitFournisseur.findAll", query = "SELECT p FROM ProduitFournisseur p")
public class ProduitFournisseur implements Serializable {

    private ProduitFournisseurId produitFournisseurid;
    private final IntegerProperty prixAchat = new SimpleIntegerProperty();
    private final IntegerProperty quantite = new SimpleIntegerProperty();
    
    public ProduitFournisseur() {
    }

    @EmbeddedId
    public ProduitFournisseurId getProduitFournisseurid() {
        return produitFournisseurid;
    }

    public void setProduitFournisseurid(ProduitFournisseurId produitFournisseurid) {
        this.produitFournisseurid = produitFournisseurid;
    }
    
    @Column(name = "quantite", nullable = false)
    public int getQuantite() {
        return this.quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }
    
    @Column(name = "prixAchat", nullable = false)
    public int getPrixAchat() {
        return this.quantite.get();
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat.set(prixAchat);
    }
}
