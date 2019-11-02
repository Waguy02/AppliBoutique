package model;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LigneCommandeId implements Serializable {

    private final StringProperty produitid = new SimpleStringProperty();
    private final StringProperty commandeid = new SimpleStringProperty();

    public LigneCommandeId() {

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

    @Column(name = "Commandeid")
    public String getCommandeid() {
        return commandeid.get();
    }

    public void setCommandeid(String commandeid) {
        this.commandeid.set(commandeid);
    }
    
    public StringProperty commandeidProperty() {
        return this.commandeid;
    }

    @Override
    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        if (this == arg0) {
            return true;
        }
        if (!(arg0 instanceof LigneCommandeId)) {
            return false;
        }
        LigneCommandeId that = (LigneCommandeId) arg0;
        return that.produitid.equals(this.produitid) && that.commandeid.equals(this.commandeid);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return Objects.hash(this.commandeid, this.produitid);
    }

}
