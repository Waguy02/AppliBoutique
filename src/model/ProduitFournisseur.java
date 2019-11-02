package model;

import java.io.Serializable;
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

    public ProduitFournisseur() {
    }

    @EmbeddedId
    public ProduitFournisseurId getProduitFournisseurid() {
        return produitFournisseurid;
    }

    public void setProduitFournisseurid(ProduitFournisseurId produitFournisseurid) {
        this.produitFournisseurid = produitFournisseurid;
    }
}
