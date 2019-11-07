package model;

import dbManager.Manager;
import interfaces.HashCode;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import outils.Outils;

/**
 * The persistent class for the produit database table.
 *
 */
@Entity
@Table(name = "produit")
@NamedQuery(name = "Produit.findAll", query = "SELECT p FROM Produit p")
public class Produit implements Serializable, HashCode, Cloneable{

    private final ObjectProperty<Date> datePeremption = new SimpleObjectProperty<>();
    
    private final StringProperty description = new SimpleStringProperty();
    
    private final StringProperty id = new SimpleStringProperty();
    
    private final StringProperty nom = new SimpleStringProperty();
    
    private final IntegerProperty quantite = new SimpleIntegerProperty();
    
    private final FloatProperty prixUnitaire = new SimpleFloatProperty();
    
    
    private final FloatProperty prixTotal=new SimpleFloatProperty();
    
   

    public FloatProperty getPrixTotalProperty() {
        return prixTotal;
    }
    
    
    public Float getPrixTotal(){
        
        return quantite.get()*prixUnitaire.get();
    }
    
    
    public void setPrixTotal(float val) {
        
        this.prixTotal.set(val);
    }
    private Categorie categorie;

    public Produit() {

    }
    
    
    
    
    
    
   public Produit clone(){
      Produit clone=new Produit();
      
      clone.setCategorie(categorie);
clone.setDatePeremption(datePeremption.get());
clone.setDescription(description.get());
clone.setId(id.get());
clone.setNom(nom.get());
clone.setPrixUnitaire(this.prixUnitaire.get());
clone.setQuantite(this.quantite.get());
      
      
      
      
      
      
      
      
     return clone;
   }
    
    
    
    
    
    

    @Temporal(TemporalType.DATE)
    @Column(name = "datePeremption")
    public Date getDatePeremption() {
        return this.datePeremption.get();
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption.set(datePeremption);
    }

    public ObjectProperty<Date> datePeremptionProperty() {
        return this.datePeremption;
    }

    @Column(name = "description", length = 100)
    public String getDescription() {
        return this.description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return this.description;
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

    @Column(name = "nom", nullable = false, length = 100)
    public String getNom() {
        return this.nom.get();
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public StringProperty nomProperty() {
        return this.nom;
    }

    @Column(name = "quantite", nullable = false)
    public int getQuantite() {
        return this.quantite.get();
    }

    public void setQuantite(int quantite) {
        this.quantite.set(quantite);
    }

    public IntegerProperty quantiteProperty() {
        return this.quantite;
    }
    @Column(name = "prixUnitaire", nullable = false)
    public float getPrixUnitaire() {
        return this.prixUnitaire.get();
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire.set(prixUnitaire);
    }

    public FloatProperty prixUnitaireProperty() {
        return this.prixUnitaire;
    }
    
    @ManyToOne
    @JoinColumn (name="categorie")
    public Categorie getCategorie() {
        return this.categorie;
    }
    
    public void setCategorie(Categorie categorie){
        this.categorie = categorie;
    }
    
    
    @Override
    public String toString() {
        return id.get()+" "+nom.get();
    }

    @Override
    public void genererCode() {
        if (this.id.get() == null) {
            this.id.set(this.hash(Outils.PREPRODUIT, Outils.INDPRODUIT++));
            Outils.enregistrementPropertiesIndex();
        }
    }

    public ObservableList<String> listeIdFournisseur() {
        ObservableList<String> list = FXCollections.observableArrayList();
    	for (String s : Manager.em.createQuery("SELECT f.produitFournisseurid.fournisseurid FROM ProduitFournisseur f WHERE f.produitFournisseurid.produitid like ?1", String.class).setParameter(1, this.getId()).getResultList()) {
    		list.add((Manager.em.find(Fournisseur.class, s)).toString());
    	}	
        return list;
    }
    
    
    
}
