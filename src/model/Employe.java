package model;

import dbManager.Manager;
import interfaces.HashCode;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import outils.Outils;

/**
 * The persistent class for the employe database table.
 *
 */
@Entity
@Table(name = "employe")
@NamedQuery(name = "Employe.findAll", query = "SELECT e FROM Employe e")
public class Employe implements Serializable, HashCode {

    private final StringProperty adresse = new SimpleStringProperty();
    private final ObjectProperty<Date> dateEmbauche = new SimpleObjectProperty<>();
    private final StringProperty grade = new SimpleStringProperty();
    private final StringProperty nom = new SimpleStringProperty();
    private final StringProperty numeroCni = new SimpleStringProperty();
    private final StringProperty telephone = new SimpleStringProperty();
    private final StringProperty telephoneProche = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty motDePasse = new SimpleStringProperty();

    public Employe() {

    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty idProperty() {
        return this.id;
    }

    @Column(name = "motDePasse")
    public String getMotDePasse() {
        return motDePasse.get();
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse.set(motDePasse);
    }

    public StringProperty motDePasseProperty() {
        return this.motDePasse;
    }

    @Column(name = "adresse", nullable = false, length = 50)
    public String getAdresse() {
        return this.adresse.get();
    }

    public void setAdresse(String adresse) {
        this.adresse.set(adresse);
    }

    public StringProperty adresseProperty() {
        return this.adresse;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "dateEmbauche")
    public Date getDateEmbauche() {
        return this.dateEmbauche.get();
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche.set(dateEmbauche);
    }

    public ObjectProperty<Date> dateEmbaucheProperty() {
        return this.dateEmbauche;
    }

    @Column(name = "grade", nullable = false, length = 50)
    public String getGrade() {
        return this.grade.get();
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public StringProperty gradeProperty() {
        return this.grade;
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

    @Column(name = "numeroCni", nullable = false, length = 50)
    public String getNumeroCni() {
        return this.numeroCni.get();
    }

    public void setNumeroCni(String numeroCni) {
        this.numeroCni.set(numeroCni);
    }

    public StringProperty numeroCniProperty() {
        return this.numeroCni;
    }

    @Column(name = "telephone", nullable = false, length = 15)
    public String getTelephone() {
        return this.telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public StringProperty telephoneProperty() {
        return this.telephone;
    }

    @Column(name = "telephoneProche", length = 15)
    public String getTelephoneProche() {
        return this.telephoneProche.get();
    }

    public void setTelephoneProche(String telephoneProche) {
        this.telephoneProche.set(telephoneProche);
    }

    public StringProperty telephoneProcheProperty() {
        return this.telephoneProche;
    }

    @Override
    public String toString() {
        return this.getId() + " " + this.getNom() + " " + this.getMotDePasse();
    }

    public List<Facture> getFactures(EntityManager em) {
        TypedQuery<Facture> query = em.createQuery("SELECT f FROM Facture f WHERE f.employeId = ?1", Facture.class);
        return query.setParameter(1, this.getId()).getResultList();
    }

    public List<Commande> getCommandes(EntityManager em) {
        TypedQuery<Commande> query = em.createQuery("SELECT c FROM Commande c WHERE c.employeId = ?1", Commande.class);
        return query.setParameter(1, this.id).getResultList();
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PREEMPLOYE, Outils.INDEMPLOYE++));
            Outils.enregistrementPropertiesIndex();
        }
    }

    public boolean modifierMotDePasse(String motDePasse) {
        try {
            Employe emp = new Employe();
            emp.setAdresse(this.getAdresse());
            emp.setDateEmbauche(this.getDateEmbauche());
            emp.setGrade(this.getGrade());
            emp.setId(this.getId());
            emp.setMotDePasse(this.getMotDePasse());
            emp.setNom(this.getNom());
            emp.setNumeroCni(this.getNumeroCni());
            emp.setTelephone(this.getTelephone());
            emp.setTelephoneProche(this.getTelephoneProche());
            Manager.em.getTransaction().begin();
            emp.setMotDePasse(motDePasse);
            Manager.em.merge(emp);
            Manager.em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean equals(String nom, String mdp) {
        return this.nom.getValue().equals(nom) && this.motDePasse.getValue().equals(mdp);
    }
}
