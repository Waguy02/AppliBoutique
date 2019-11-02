package model;

import interfaces.HashCode;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import outils.Outils;

/**
 * The persistent class for the facture database table.
 */
@Entity
@Table(name = "facture")
@NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f")
public class Facture implements Serializable, HashCode {

    private final ObjectProperty<Date> dateEnregistrement = new SimpleObjectProperty<>();
    private final StringProperty id = new SimpleStringProperty();
    private final FloatProperty montant = new SimpleFloatProperty();
    private final BooleanProperty paye = new SimpleBooleanProperty();
    private final StringProperty clientId = new SimpleStringProperty();
    private final StringProperty employeId = new SimpleStringProperty();

    public Facture() {
    }

    @Column(name = "clientid")
    public String getClientId() {
        return clientId.get();
    }

    public void setClientId(String clientId) {
        this.clientId.set(clientId);
    }

    public StringProperty clientIdProperty() {
        return this.clientId;
    }

    @Column(name = "employeid")
    public String getEmployeId() {
        return employeId.get();
    }

    public void setEmployeId(String employeId) {
        this.employeId.set(employeId);
    }

    public StringProperty employeIdProperty() {
        return this.employeId;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dateEnregistrement", nullable = false)
    public Date getDateEnregistrement() {
        return this.dateEnregistrement.get();
    }

    public void setDateEnregistrement(Date dateEnregistrement) {
        this.dateEnregistrement.set(dateEnregistrement);
    }

    public ObjectProperty<Date> dateEnregistrementProperty() {
        return this.dateEnregistrement;
    }

    @Id
    @Column(name = "id", nullable = false, length = 15)
    public String getId() {
        return this.id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return this.id;
    }

    @Column(name = "montant", nullable = false)
    public float getMontant() {
        return this.montant.get();
    }

    public void setMontant(float montant) {
        this.montant.set(montant);
    }

    @Column(name = "paye", nullable = false)
    public boolean isPaye() {
        return this.paye.get();
    }

    public void setPaye(boolean paye) {
        this.paye.set(paye);
    }

    public BooleanProperty payeProperty() {
        return this.paye;
    }

    @Override
    public String toString() {
        return "Facture [dateEnregistrement=" + dateEnregistrement.get() + ", id=" + id.get() + ", montant=" + montant.get() + ", paye="
                + paye.get() + ", clientId=" + clientId.get() + ", employeId=" + employeId.get() + "]";
    }

    public ObservableList<LigneFacture> ligneFactures(EntityManager em) {
        TypedQuery<LigneFacture> query = em.createQuery("SELECT lf FROM LigneFacture lf WHERE lf.ligneFactureId.factureId = ?1", LigneFacture.class);
        return FXCollections.observableList(query.setParameter(1, this.id.getValue()).getResultList());
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PREFACTURE, Outils.INDFACTURE++));
            Outils.enregistrementPropertiesIndex();
        }
    }
}
