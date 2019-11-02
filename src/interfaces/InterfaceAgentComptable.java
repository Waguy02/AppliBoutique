/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import dbManager.Manager;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.TemporalType;
import model.Commande;
import model.Facture;
import model.Produit;

/**
 *
 * @author LOIC KWATE DASSI
 */
public interface InterfaceAgentComptable extends InterfaceGlobal {

    /**
     *
     * @return
     */
    public default ObservableList<Produit> listeProduits() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList());
    }

    /**
     *
     * @param paye
     * @param date1
     * @param date2
     * @return
     * @throws java.lang.Throwable
     */
    public default ObservableList<Facture> listeFacture(boolean paye, Date date1, Date date2) throws Throwable {
        if (date1 == null || date2 == null) {
            throw new NullPointerException();
        }
        return FXCollections.observableList(Manager.em.createQuery("SELECT f FROM Facture f WHERE f.dateEnregistrement BETWEEN ?1 AND ?2", Facture.class).setParameter(1, date1, TemporalType.DATE).setParameter(1, date2, TemporalType.DATE).getResultList());
    }

    /**
     *
     * @param paye
     * @param date1
     * @param date2
     * @return
     * @throws java.lang.Throwable
     */
    public default ObservableList<Commande> listeCommande(boolean paye, Date date1, Date date2) throws Throwable {
        if (date1 == null || date2 == null) {
            throw new NullPointerException();
        }
        return FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Commande c WHERE c.dateEnregistrement BETWEEN ?1 AND ?2", Commande.class).setParameter(1, date1, TemporalType.DATE).setParameter(2, date2, TemporalType.DATE).getResultList());
    }
 
}
