/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import application.utilities.RecupListes;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.LigneFacture;
import constantes.Constantes;
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
                    Produit prod = RecupListes.produitId(param.getValue().getLigneFactureId().getProduitId());
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
                    Caissier cai = RecupListes.caissierId(param.getValue().getEmployeId());
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
                    Client cl = RecupListes.clientId(param.getValue().getClientId());
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
                    Fournisseur fo = RecupListes.fournisseurId(param.getValue().getFournisseurid());
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
    
    
}
