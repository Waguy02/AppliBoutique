/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import javafx.collections.ObservableList;
import model.Employe;
import model.Fournisseur;
import model.Produit;

/**
 *
 * @author LOIC KWATE DASSI
 */
public interface InterfaceAdministrateur extends InterfaceAgentComptable{

    /**
     *
     * @param employe
     */
    public void creerEmploye(Employe employe);

    /**
     *
     * @param produit
     */
    public void modifierProduit(Produit produit);

    /**
     *
     * @return
     */
    public ObservableList<Employe> listeEmployes();
    /**
     *
     * @return
     */
    public ObservableList<Fournisseur> listeFournisseur();

    /**
     *
     * @param fournisseur
     */
    public void ajouterFournisseur(Fournisseur fournisseur);
    
    /**
     * 
     * @param idFournisseur
     * @param idProduit 
     */
    public void ajouterProduitFournisseur(String idFournisseur, String idProduit);
    
    /**
     * cette fonction permet de chercher les produits par catégories
     * @param categorie
     * @return 
     */
}
