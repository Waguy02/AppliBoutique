/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbManager.Manager;

import interfaces.InterfaceGestionnaireStock;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javax.persistence.TypedQuery;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class GestionnaireStock extends Employe implements InterfaceGestionnaireStock {

    public GestionnaireStock() {
        this.setMapProduits(this.listeProduit());
    }

    private final MapProperty<String, Produit> mapProduits = new SimpleMapProperty<>(FXCollections.observableHashMap());

    public ObservableMap getMapProduits() {
        return mapProduits.get();
    }

    public void setMapProduits(ObservableMap value) {
        mapProduits.set(value);
    }

    public MapProperty mapProduitProperty() {
        return mapProduits;
    }

    /**
     * Hypotheses: dans cette méthode nous suppons les faits suivants: - les
     * données ont été bien validées dans les vues. - la fonction *generercode*
     * a été appelé dans la commande passé en paramètre
     *
     * @param commande
     * @param ligneCommande
     * @param ligneCommandes
     * @throws Throwable
     */
    @Override
    public void EffectuerAchat(Commande commande, LigneCommande ligneCommande, LigneCommande... ligneCommandes) throws Throwable {
        if (commande == null || ligneCommande == null || ligneCommandes == null) {
            throw new NullPointerException();
        }
        float somme = 0;
        Produit produit = Manager.em.find(Produit.class, ligneCommande.getLigneCommandeId().getProduitid());
        /**
         * Ouverture de la transaction les modifications
         */
        Manager.em.getTransaction().begin();
        {
            Manager.em.persist(commande);
            this.listeProduit().get(produit.getId()).setQuantite(produit.getQuantite() + ligneCommande.getQuantite());
            Manager.em.persist(ligneCommande);
            somme = ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire();
            if (ligneCommandes != null) {
                for (LigneCommande lc : ligneCommandes) {
                    produit = Manager.em.find(Produit.class, lc.getLigneCommandeId().getProduitid());
                    this.listeProduit().get(produit.getId()).setQuantite(produit.getQuantite() + lc.getQuantite());
                    somme += lc.getPrixUnitaire() * lc.getQuantite();
                    Manager.em.persist(lc);
                }
            }
            commande.setMontant(somme);
        }
        Manager.em.getTransaction().commit();
        System.out.println("achat effectué avec succès");
    }

    /**
     * Hypothèse: dans cette fonction nous supposons les faits suivants; - la
     * commande a déjà été effectué et enregistrer dans la base de données - les
     * données ont été correctement validés dans les controlleurs
     *
     * @param commande
     * @param ligneCommande
     * @throws Throwable
     */
    @Override
    public void ajouterLigneCommande(Commande commande, LigneCommande ligneCommande) throws Throwable {
        if (commande == null || ligneCommande == null) {
            throw new NullPointerException();
        }
        Produit produit = Manager.em.find(Produit.class, ligneCommande.getLigneCommandeId().getProduitid());
        /**
         * Ouverture de la transaction pour les modifications
         */
        Manager.em.getTransaction().begin();
        {
            this.listeProduit().get(produit.getId()).setQuantite(produit.getQuantite() + ligneCommande.getQuantite());
            commande.setMontant(commande.getMontant() + ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire());
            Manager.em.persist(ligneCommande);
        }
        Manager.em.getTransaction().commit();
            System.out.println("ajout de la ligneCommande avec succès");
    }

    @Override
    public void retirerLigneCommande(Commande commande, LigneCommande ligneCommande) throws Throwable {
        if (commande == null || ligneCommande == null) {
            throw new NullPointerException();
        }
        Produit produit = Manager.em.find(Produit.class, ligneCommande.getLigneCommandeId().getProduitid());
        /**
         * Ouverture de la transaction pour les modifications;
         */
        Manager.em.getTransaction().begin();
        {
            this.listeProduit().get(produit.getId()).setQuantite(produit.getQuantite() + ligneCommande.getQuantite());
            commande.setMontant(commande.getMontant() - ligneCommande.getQuantite() * ligneCommande.getPrixUnitaire());
            Manager.em.remove(ligneCommande);
        }
        Manager.em.getTransaction().commit();
        System.out.println("retrait effectué avec succès");
    }

    /**
     * fonction pour retourner la liste des produits;
     *
     * @return
     */
    private ObservableMap<String, Produit> listeProduit() {
        ObservableMap<String, Produit> map = FXCollections.observableHashMap();
        for (Produit p : Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList()) {
            map.put(p.getId(), p);
        }
        return map;
    }

    /**
     * Hypothèse : dans cette fonction nous supposerons de la produit n'existe
     * pas et nous voulons enregistrer son identité dans la base de donnée
     *
     * @param produit
     */
    @Override
    public void ajouterIdentiteProduit(Produit produit) {
        if (produit == null) {
            throw new NullPointerException();
        }
        produit.genererCode();
        produit.setQuantite(0);
        this.mapProduits.put(produit.getId(), produit);
        Manager.em.getTransaction().begin();
        Manager.em.persist(produit);
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
    
    @Override
    public void ajouterCategorie(String categorie) throws Exception{
        Categorie c = new Categorie();
        c.setId(categorie);
        Manager.em.getTransaction().begin();
        Manager.em.persist(c);
        Manager.em.getTransaction().commit();
    }
    
    @Override
    public Categorie selectCategorie(String id){
        TypedQuery<Categorie> query = Manager.em.createQuery("SELECT c FROM Categorie c WHERE c.id = ?1", Categorie.class);
        List<Categorie> listeCategories = query.setParameter(1, id).getResultList();
        if (listeCategories.size() == 0){
            return null;
        }
        else{
            return listeCategories.get(0);
        }
    }
    
}
