/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.InterfaceAdministrateur;
import javafx.collections.ObservableList;
import dbManager.Manager;
import javafx.collections.FXCollections;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class Administrateur extends Employe implements InterfaceAdministrateur {
    
    ObservableList<Produit> listeProduits;
    ObservableList<Employe> listeEmployes;
    
    ObservableList<Facture> listeFactures;
    ObservableList<Client> listeClients;
    ObservableList<Fournisseur> listeFournisseurs;
    
    @Override
    public void creerEmploye(Employe employe) {
        if (employe == null) {
            throw new NullPointerException();
        }
        Manager.em.getTransaction().begin();
        Manager.em.persist(employe);
        Manager.em.getTransaction().commit();
    }
    
    
    public void supprimerEmploye(Employe employe)
    {
        if (employe == null) {
            throw new NullPointerException();
        }
        
        Manager.em.getTransaction().begin();
        Manager.em.remove(employe);
        Manager.em.getTransaction().commit();
        
        
    }
    @Override
    public void modifierProduit(Produit produit) {

    }
    
    
    
    
   
    
    
    
    
    
    @Override
    public ObservableList<Employe> listeEmployes() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Employe.findAll", Employe.class).getResultList());
    }

       public ObservableList<Employe> listeEmployes(boolean refresh){
        
        if(refresh||this.listeEmployes==null)this.listeEmployes=listeEmployes();
        return this.listeEmployes;
       }
    
    
    
    
    
    @Override
    public ObservableList<Fournisseur> listeFournisseur() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Fournisseur.findAll", Fournisseur.class).getResultList());
    }
    
    
    
    public ObservableList<Fournisseur> listeFournisseur(boolean refresh){
        
             if(refresh||this.listeFournisseurs==null){
             this.listeFournisseurs=listeFournisseur();
                 }
       
            return this.listeFournisseurs;
            
        
        
    }
    @Override
    public void ajouterFournisseur(Fournisseur fournisseur) {
        if (fournisseur == null) {
            throw new NullPointerException();
        }
        Manager.em.getTransaction().begin();
        Manager.em.persist(fournisseur);
        Manager.em.getTransaction().commit();
    }

    @Override
    public void ajouterProduitFournisseur(String idFournisseur, String idProduit) {
        if (idFournisseur == null || idProduit == null) {
            throw new NullPointerException();
        }
        ProduitFournisseurId pfi = new ProduitFournisseurId();
        pfi.setFournisseurid(idFournisseur);
        pfi.setProduitid(idProduit);
        ProduitFournisseur pf = new ProduitFournisseur();
        pf.setProduitFournisseurid(pfi);
        Manager.em.getTransaction().begin();
        Manager.em.persist(pf);
        Manager.em.getTransaction().commit();
    }
}
