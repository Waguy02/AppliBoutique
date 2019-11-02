package model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LigneFactureId implements Serializable {

    private final StringProperty produitId = new SimpleStringProperty();
    private final StringProperty factureId = new SimpleStringProperty();

    @Column(name = "produitid")
    public String getProduitId() {
        return produitId.get();
    }

    public void setProduitId(String produitId) {
        this.produitId.set(produitId);
    }
    
    public StringProperty produitIdProperty() {
        return this.produitId;
    }

    @Column(name = "factureid")
    public String getFactureId() {
        return factureId.get();
    }

    public void setFactureId(String factureId) {
        this.factureId.set(factureId);
    }
    
    public StringProperty factureidProperty() {
        return this.factureId;
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        if (this == arg0) {
            return true;
        }
        if (!(arg0 instanceof LigneFactureId)) {
            return false;
        }
        LigneFactureId that = (LigneFactureId) arg0;
        return this.produitId.equals(that.produitId) && this.factureId.equals(that.factureId);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(this.produitId, this.factureId);
    }

    @Override
    public String toString() {
        return "LigneFactureId [produitId=" + produitId + ", factureId=" + factureId + "]";
    }

}
