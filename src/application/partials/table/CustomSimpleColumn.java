/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.LigneFacture;
import constantes.Constantes;
import dbManager.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Caissier;
import model.Client;
import model.Facture;
import model.Fournisseur;
import model.LigneCommande;
import model.Produit;
/**
 *
 * @author test
 */
public class CustomSimpleColumn<S,T> extends CustomColumn{
    
    
   
    public CustomSimpleColumn(String text){
        super(text);
    }
    
    public CustomSimpleColumn(String text,String targetProperty){
        super(text);
        this.setCellValueFactory(new PropertyValueFactory<>(targetProperty));
    }
    
    public CustomSimpleColumn(String text,String targetProperty,Double widthPercentage){
        this(text,targetProperty);
        this.setWidthPercentage(widthPercentage);
        
    }
    
    public CustomSimpleColumn(String text,Double widthPercentage, String classe){
        super(text);
        switch(classe){
            case (Constantes.LIGNEFACTURE):
                this.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<
                LigneFacture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneFacture, String> param) {
                    Produit prod = produitId(param.getValue().getLigneFactureId().getProduitId());
                    if(prod != null){
                        return prod.nomProperty();
                    }
                    else{
                        return null;
                    }             
                }
                });
                break;
            case (Constantes.CAISSIER):
                this.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<
                Facture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Facture, String> param) {
                    Caissier cai = caissierId(param.getValue().getEmployeId());
                    if(cai != null){
                        return cai.nomProperty();
                    }
                    else{
                        return null;
                    }             
                }
                });
                break;
            case (Constantes.CLIENT):
                this.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<
                Facture, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Facture, String> param) {
                    Client cl = clientId(param.getValue().getClientId());
                    if(cl != null){
                        return cl.nomProperty();
                    }
                    else{
                        return null;
                    }             
                }
                });
                break;
            case (Constantes.LIGNECOMMANDE):
                this.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<
                LigneCommande, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneCommande, String> param) {
                    Fournisseur fo = fournisseurId(param.getValue().getFournisseurid());
                    if(fo != null){
                        return fo.nomProperty();
                    }
                    else{
                        return null;
                    }             
                }
                });
            default:
                break;
        }                
        this.setWidthPercentage(widthPercentage);
    }
    
    private Produit produitId(String id) {
        ObservableList<Produit> liste = FXCollections.observableList(Manager.em.createQuery("SELECT p FROM Produit p WHERE p.id = ?1", Produit.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    private Caissier caissierId(String id) {
        ObservableList<Caissier> liste = FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Caissier c WHERE c.id = ?1", Caissier.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    private Client clientId(String id) {
        ObservableList<Client> liste = FXCollections.observableList(Manager.em.createQuery("SELECT c FROM Client c WHERE c.id = ?1", Client.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
    private Fournisseur fournisseurId(String id) {
        ObservableList<Fournisseur> liste = FXCollections.observableList(Manager.em.createQuery("SELECT f FROM Fournisseur f WHERE f.id = ?1", Fournisseur.class).setParameter(1, id).getResultList());
        if (liste.size() > 0)
            return liste.get(0);
        else
            return null;
    }
}
