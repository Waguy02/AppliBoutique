/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.InterfaceAgentComptable;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import dbManager.Manager;
import javax.persistence.TemporalType;
/**
 *
 * @author LOIC KWATE DASSI
 */
public class AgentComptable extends Employe implements InterfaceAgentComptable{

    @Override
    public ObservableList<Produit> listeProduits() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList());
    }

    @Override
    public ObservableList<Facture> listeFacture(boolean paye, Date date1, Date date2) throws Throwable {
        if (date1 == null || date2 == null) throw new NullPointerException();
        return FXCollections.observableList(Manager.em.createQuery("SELECT f FROM Facture f WHERE f.dateEnregistrement BETWEEN ?1 AND ?2", Facture.class).setParameter(1, date1, TemporalType.DATE).setParameter(2, date2,TemporalType.DATE).getResultList());
    }

    @Override
    public ObservableList<Commande> listeCommande(boolean paye, Date date1, Date date2) {
        if (date1 == null || date2 == null) throw new NullPointerException();
        return FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Commande c WHERE c.dateEnregistrement BETWEEN ?1 AND ?2", Commande.class).setParameter(1, date1,TemporalType.DATE).setParameter(2, date2, TemporalType.DATE).getResultList());
    }
    
    public ObservableList<Commande> listeCommande() {
        return FXCollections.observableArrayList(Manager.em.createNamedQuery("Commande.findAll", Commande.class).getResultList());
    }
    public ObservableList<Facture> listeFacture() {
        return FXCollections.observableArrayList(Manager.em.createNamedQuery("Facture.findAll", Facture.class).getResultList());
    }
}
