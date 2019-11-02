package model;

import interfaces.HashCode;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import outils.Outils;

/**
 * The persistent class for the Commande database table.
 *
 */
@Entity
@Table(name = "commande", schema = "supermarche")
@NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")
public class Commande implements Serializable, HashCode {

    private final ObjectProperty<Date> dateEnregistrement = new SimpleObjectProperty<>();
    private final StringProperty id = new SimpleStringProperty();
    private final FloatProperty montant = new SimpleFloatProperty();
    private final StringProperty employeid = new SimpleStringProperty();

    public Commande() {

    }

    @Column(name = "employeid")
    public String getEmployeid() {
        return employeid.get();
    }

    public void setEmployeid(String employeid) {
        this.employeid.set(employeid);
    }

    public StringProperty employeidProperty() {
        return this.employeid;
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

    public FloatProperty montantProperty() {
        return this.montant;
    }

    @Override
    public String toString() {
        return "Commande [dateEnregistrement=" + dateEnregistrement.get() + ", id=" + id.get() + ", montant=" + montant.get()
                + ", employeid=" + employeid.get() + "]";
    }

    public ObservableList<LigneCommande> getligneCommandes(EntityManager em) {
        TypedQuery<LigneCommande> query = em.createQuery("SELECT lc from ligneCommande lc WHERE lc.ligneCommandeId.commandeid = ?1", LigneCommande.class);
        return FXCollections.observableList(query.setParameter(1, this.id.getValue()).getResultList());
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PRECOMMANDE, Outils.INDCOMMANDE++));
            Outils.enregistrementPropertiesIndex();
        }
    }
}
