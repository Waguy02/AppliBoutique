/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import dbManager.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Caissier;
import model.Client;
import model.Fournisseur;
import model.Produit;

/**
 *
 * @author ESDRAS
 */
public class RecupListes {

    public static ObservableList<Caissier> listeCaissier() {
        //Manager.ouvertureEntityManager();
        return FXCollections.observableList(Manager.em.createNamedQuery("Caissier.findAll", Caissier.class).getResultList());
    }

    public static ObservableList<Produit> listeProduit() {
        //Manager.ouvertureEntityManager();
        return FXCollections.observableList(Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList());
    }

    public static ObservableList listeClient() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Client.findAll", Client.class).getResultList());
    }
    
    public static Produit produitId(String id) {
        ObservableList<Produit> liste = FXCollections.observableList(Manager.em.createQuery("SELECT p FROM Produit p WHERE p.id = ?1", Produit.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    public static Caissier caissierId(String id) {
        ObservableList<Caissier> liste = FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Caissier c WHERE c.id = ?1", Caissier.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    public static Client clientId(String id) {
        ObservableList<Client> liste = FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Client c WHERE c.id = ?1", Client.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    public static Fournisseur fournisseurId(String id) {
        ObservableList<Fournisseur> liste = FXCollections.observableList(Manager.em.createQuery("SELECT f FROM Fournisseur f WHERE f.id = ?1", Fournisseur.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
}
