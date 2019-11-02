/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dbManager.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Categorie;
import model.Produit;

/**
 *
 * @author LOIC KWATE DASSI
 */

public interface InterfaceGlobal {
    /**
     * Fonction qui permet de récupérer tous les produits appartenant à une catégorie
     * @param categorie
     * @return 
     */
    default public ObservableList<Produit> listeProduitCategorie(String categorie) {
        return FXCollections.observableList(Manager.em.createNamedQuery("Produit.findByCategorie", Produit.class).setParameter(1, categorie).getResultList());
    }
    
    default public ObservableList<Categorie> listeCategorie() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Categorie.findAll", Categorie.class).getResultList());
    }
}

