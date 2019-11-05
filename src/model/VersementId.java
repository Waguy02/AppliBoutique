/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ESDRAS
 */
@Embeddable
public class VersementId implements Serializable {

    private final StringProperty idFacture = new SimpleStringProperty();
    private final StringProperty idProduit = new SimpleStringProperty();
    private final StringProperty idVersement = new SimpleStringProperty();
    
    public VersementId() {

    }

    @Column(name = "idVersement")
    public String getIdVersement() {
        return idVersement.get();
    }

    public void setIdVersement(String idVersement) {
        this.idVersement.set(idVersement);
    }
    
    @Column(name = "idFacture")
    public String getIdFacture() {
        return idFacture.get();
    }

    public void setIdFacture(String idFacture) {
        this.idFacture.set(idFacture);
    }
    
    public StringProperty idFactureProperty() {
        return this.idFacture;
    }

    @Column(name = "idProduit")
    public String getProduitid() {
        return idProduit.get();
    }

    public void setProduitid(String idProduit) {
        this.idProduit.set(idProduit);
    }
    
    public StringProperty idProduitProperty() {
        return this.idProduit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idFacture);
        hash = 53 * hash + Objects.hashCode(this.idProduit);
        hash = 53 * hash + Objects.hashCode(this.idVersement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VersementId other = (VersementId) obj;
        if (!Objects.equals(this.idFacture, other.idFacture)) {
            return false;
        }
        if (!Objects.equals(this.idProduit, other.idProduit)) {
            return false;
        }
        if (!Objects.equals(this.idVersement, other.idVersement)) {
            return false;
        }
        return true;
    }

}
