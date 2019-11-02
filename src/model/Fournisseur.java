package model;

import interfaces.HashCode;
import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.*;
import outils.Outils;

/**
 * The persistent class for the fournisseur database table.
 *
 */
@Entity
@Table(name = "fournisseur")
@NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f")
public class Fournisseur implements Serializable, HashCode {

    private final StringProperty adresse = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty telephone = new SimpleStringProperty();

    public Fournisseur() {
    }

    @Column(name = "adresse", length = 50)
    public String getAdresse() {
        return this.adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty adresseProperty() {
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
        return id.get()+" "+nom.get();
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PREFOURNISSEUR, Outils.INDFOURNISSEUR++));
            Outils.enregistrementPropertiesIndex();
        }
    }
}
