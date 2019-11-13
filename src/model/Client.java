package model;

import interfaces.HashCode;
import java.io.Serializable;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import outils.Outils;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

/**
 * The persistent class for the client database table.
 *
 */
@Entity
@Table(name = "client")
@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
public class Client implements Serializable, HashCode {

    private final StringProperty adresse = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty telephone = new SimpleStringProperty();

    public Client() {

    }

    @Column(name = "adresse", length = 50)
    public String getAdresse() {
        return this.adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty adressProperty() {
        return this.adresse;
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

    @Column(name = "nom", nullable = false, length = 75)
    public String getNom() {
        return this.nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return this.nom;
    }

    @Column(name = "telephone", length = 15)
    public String getTelephone() {
        return this.telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public StringProperty telephoneProperty() {
        return this.telephone;
    }

    @Override
    public String toString() {
        return this.id.get()+"  "+this.nom.get();
    }

    public ObservableList<Facture> getFactures(EntityManager em) {
        TypedQuery<Facture> query = em.createQuery("SELECT f from Facture f WHERE f.clientId = ?1", Facture.class);
        return FXCollections.observableList(query.setParameter(1, this.getId()).getResultList());
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PRECLIENT, Outils.INDCLIENT++));
            Outils.enregistrementPropertiesIndex();
        }
    }
}
