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
public class RemiseId implements Serializable {

    private final StringProperty idClient = new SimpleStringProperty();
    private final StringProperty idProduit = new SimpleStringProperty();

    public RemiseId() {

    }

    @Column(name = "idClient")
    public String getIdClient() {
        return idClient.get();
    }

    public void setIdClient(String idClient) {
        this.idClient.set(idClient);
    }
    
    public StringProperty idClientProperty() {
        return this.idClient;
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
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RemiseId)) {
            return false;
        }
        RemiseId that = (RemiseId) obj;
        return this.idProduit.equals(that.idProduit) && this.idClient.equals(that.idClient);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(this.idProduit, this.idClient);
    }
}
