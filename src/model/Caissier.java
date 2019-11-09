/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbManager.Manager;
import interfaces.InterfaceCaissier;
import java.util.List;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class Caissier extends Employe implements InterfaceCaissier {

    public Caissier() {
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
    
    public ObservableList<Client> getClientList()
    {
        List<Client> list=Manager.em.createNamedQuery("Client.findAll", Client.class).getResultList();
        return FXCollections.observableArrayList(list);
    }
    /**
     * Hypothèses : - nous supposons ici que les données ont été validées dans
     * l'interface de l'application et cette fonction nous sauvegarderons que
     * les données et assurer leurs persistence. - nous supposerons dans cette
     * méthode que la fonction *generecode* de facture a déjà été appelée.
     *
     * @param facture
     * @param ligneFacture
     * @param lignefactures
     * @throws Throwable
     */
    @Override
    public void effectuerVente(Facture facture, LigneFacture ligneFacture, LigneFacture... lignefactures) throws Exception {
        if (facture == null || ligneFacture == null) {
            throw new NullPointerException();
        }
        float somme = 0;
        Produit produit = Manager.em.find(Produit.class, ligneFacture.getLigneFactureId().getProduitId());
        /**
         * Début de la transaction pour la sauvegarde.
         */
        Manager.em.getTransaction().begin();
        {
            Manager.em.persist(facture);
            this.listeProduit().get(produit.getId() + " - " + produit.getNom()).setQuantite(produit.getQuantite() - ligneFacture.getQuantite());
            // sauvegarde du produit et ligneFacture
            Manager.em.persist(ligneFacture);
            somme = ligneFacture.getPrixUnitaire() * ligneFacture.getQuantite();
            if (lignefactures != null) {
                for (LigneFacture lf : lignefactures) {
                    produit = Manager.em.find(Produit.class, lf.getLigneFactureId().getProduitId());
                    this.listeProduit().get(produit.getId() + " - " + produit.getNom()).setQuantite(produit.getQuantite() - lf.getQuantite());
                    somme += lf.getPrixUnitaire() * lf.getQuantite();
                    Manager.em.persist(lf);
                }
            }
            facture.setMontant(somme);
        }
        Manager.em.getTransaction().commit();
        System.out.println("vente réussie");
    }

    /**
     * Hypothèses: nous supposons ici qu'ici la vente a déjà été effectuée et
     * nous voulons ajouter une produit supplémentaire à ladite vente
     *
     * @param facture
     * @param ligneFacture
     * @throws Throwable
     */
    @Override
    public void ajouterLigneFacture(Facture facture, LigneFacture ligneFacture) throws Throwable {
        if (facture == null || ligneFacture == null) {
            throw new NullPointerException();
        }
        Produit produit = Manager.em.find(Produit.class, ligneFacture.getLigneFactureId().getProduitId());
        /**
         * début de transaction
         */
        Manager.em.getTransaction().begin();
        {
            this.listeProduit().get(produit.getId() + " - " + produit.getNom()).setQuantite(produit.getQuantite() - ligneFacture.getQuantite());
            facture.setMontant(facture.getMontant() + ligneFacture.getPrixUnitaire() * ligneFacture.getQuantite());
            Manager.em.persist(ligneFacture);
        }
        Manager.em.getTransaction().commit();
        System.out.println("ajout avec succès");
    }

    /**
     * Hypothèses: nous supposons ici que la vente a déjà été effectuée et le
     * cassier désire retirer un produit figurant dans ceux de la vente.
     *
     * @param facture
     * @param ligneFacture
     * @throws Throwable
     */
    @Override
    public void retireLigneFacture(Facture facture, LigneFacture ligneFacture) throws Throwable {
        if (facture == null || ligneFacture == null) {
            throw new NullPointerException();
        }
        Produit produit = Manager.em.find(Produit.class, ligneFacture.getLigneFactureId().getProduitId());
        /**
         * Ouverture de la transaction pour la modification
         */
        Manager.em.getTransaction().begin();
        {
            this.listeProduit().get(produit.getId() + " - " + produit.getNom()).setQuantite(produit.getQuantite() + ligneFacture.getQuantite());
            facture.setMontant(facture.getMontant() - ligneFacture.getQuantite() * ligneFacture.getPrixUnitaire());
            Manager.em.remove(ligneFacture);
        }
        Manager.em.getTransaction().commit();
    }

    private ObservableMap<String, Produit> listeProduit() {
        ObservableMap<String, Produit> map = FXCollections.observableHashMap();
        for (Produit p : Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList()) {
            map.put(p.getId() + " - " + p.getNom(), p);
        }
        return map;
    }
}
