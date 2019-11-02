/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Categorie;
import model.Commande;
import model.LigneCommande;
import model.Produit;

/**
 *
 * @author LOIC KWATE DASSI
 */
public interface InterfaceGestionnaireStock extends InterfaceGlobal {

    /**
     * fonction pour ajouter un achat du magasin
     *
     * @param commande
     * @param ligneCommande
     * @param ligneCommandes
     * @throws java.lang.Throwable
     */
    public void EffectuerAchat(Commande commande, LigneCommande ligneCommande, LigneCommande... ligneCommandes) throws Throwable;

    /**
     *
     * @param commande
     * @param ligneCommande
     * @throws Throwable
     */
    public void ajouterLigneCommande(Commande commande, LigneCommande ligneCommande) throws Throwable;

    /**
     *
     * @param commande
     * @param ligneCommande
     * @throws Throwable
     */
    public void retirerLigneCommande(Commande commande, LigneCommande ligneCommande) throws Throwable;
    
    /**
     * 
     * @param produit 
     * @throws java.lang.Throwable 
     */
    public void ajouterIdentiteProduit(Produit produit) throws Throwable;
    
    /**
     * 
     * @param idFournisseur
     * @param idProduit 
     */
    public void ajouterProduitFournisseur(String idFournisseur, String idProduit);
    
    /**
     * Fonction qui permet d'ajouter une catégorie
     * @param categorie 
     * @throws java.lang.Exception 
     */
    public void ajouterCategorie(String categorie) throws Exception;
    public Categorie selectCategorie(String id);
}
