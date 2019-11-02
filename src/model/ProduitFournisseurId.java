package model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProduitFournisseurId implements Serializable {

    private final StringProperty fournisseurid = new SimpleStringProperty();
    private final StringProperty produitid = new SimpleStringProperty();

    public ProduitFournisseurId() {

    }

    @Column(name = "fournisseurid")
    public String getFournisseurid() {
        return fournisseurid.get();
    }

    public void setFournisseurid(String fournisseurid) {
        this.fournisseurid.set(fournisseurid);
    }
    
    public StringProperty fournisseuridProperty() {
        return this.fournisseurid;
    }

    @Column(name = "produitid")
    public String getProduitid() {
        return produitid.get();
    }

    public void setProduitid(String produitid) {
        this.produitid.set(produitid);
    }
    
    public StringProperty produitidProperty() {
        return this.produitid;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProduitFournisseurId)) {
            return false;
        }
        ProduitFournisseurId that = (ProduitFournisseurId) obj;
        return this.produitid.equals(that.produitid) && this.fournisseurid.equals(that.fournisseurid);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(this.produitid, this.fournisseurid);
    }
}
