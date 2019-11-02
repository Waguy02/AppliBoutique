/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.Facture;
import model.LigneFacture;
import model.Produit;

/**
 *
 * @author LOIC KWATE DASSI
 */
public interface InterfaceCaissier extends InterfaceGlobal {
    
    /**
     * fonction pour ajouter une facture et ses lignes factures
     * @param facture
     * @param ligneFacture
     * @param lignefactures
     * @throws java.lang.Throwable
    */
    public void effectuerVente(Facture facture, LigneFacture ligneFacture, LigneFacture... lignefactures) throws Exception;
    /**
     * Dans cette fonction nous considérons que la vente est déjà effectuée et nous voulons ajouter un produit à la vente
     * @param facture
     * @param ligneFacture
     * @throws Throwable 
     */
    public void ajouterLigneFacture(Facture facture, LigneFacture ligneFacture) throws Throwable;
    
    /**
     * @param facture
     * @param ligneFacture
     * @throws Throwable 
     */
    public void retireLigneFacture(Facture facture, LigneFacture ligneFacture) throws Throwable;
    
}

