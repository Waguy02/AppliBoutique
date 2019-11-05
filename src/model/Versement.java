/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.HashCode;
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ESDRAS
 */
@Entity
@Table(name = "versement")
@NamedQuery(name = "Versement.findAll", query = "SELECT r FROM Versement r")
public class Versement implements Serializable, HashCode{

    private VersementId versementId = new VersementId();
    private final ObjectProperty<Date> dateVersement = new SimpleObjectProperty<>();
    private final IntegerProperty montant = new SimpleIntegerProperty();
    
    public Versement() {
    }
    
    @EmbeddedId
    public VersementId getVersementId() {
        return versementId;
    }

    public void setVersementId(VersementId versementId) {
        this.versementId = versementId;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateVersement")
    public Date getDateVersement() {
        return this.dateVersement.get();
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement.set(dateVersement);
    }

    public ObjectProperty<Date> dateVersementProperty() {
        return this.dateVersement;
    }
    
    @Column(name = "montant", nullable = false)
    public int getMontant() {
        return this.montant.get();
    }

    public void setMontant(int montant) {
        this.montant.set(montant);
    }

    public IntegerProperty montantProperty() {
        return this.montant;
    }
    @Override
    public String hash(String prefix, int index) {
        return HashCode.super.hash(prefix, index); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void genererCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
