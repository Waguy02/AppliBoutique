/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.HashCode;
import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
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
@Table(name = "remise")
@NamedQuery(name = "Remise.findAll", query = "SELECT r FROM Remise r")
public class Remise implements Serializable, HashCode{

    private RemiseId remiseId = new RemiseId();
    private final ObjectProperty<Date> dateDebut = new SimpleObjectProperty<>();
    private final ObjectProperty<Date> dateFin = new SimpleObjectProperty<>();
    private final FloatProperty remise = new SimpleFloatProperty();
    
    public Remise() {
    }
    
    @EmbeddedId
    public RemiseId getRemiseId() {
        return remiseId;
    }

    public void setRemiseId(RemiseId remiseId) {
        this.remiseId = remiseId;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateDebut")
    public Date getDateDebut() {
        return this.dateDebut.get();
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut.set(dateDebut);
    }

    public ObjectProperty<Date> dateDebutProperty() {
        return this.dateDebut;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dateFin")
    public Date getDateFin() {
        return this.dateFin.get();
    }

    public void setDateFin(Date dateFin) {
        this.dateFin.set(dateFin);
    }

    public ObjectProperty<Date> dateFinProperty() {
        return this.dateFin;
    }
    
    @Column(name = "remise", nullable = false)
    public float getRemise() {
        return this.remise.get();
    }

    public void setRemise(float remise) {
        this.remise.set(remise);
    }

    public FloatProperty remiseProperty() {
        return this.remise;
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
