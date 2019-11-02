/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dbManager.Manager;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.LigneFacture;

/**
 *
 * @author E_DinaBrown
 */
public class LigneFactureController implements Initializable {
      
    private String idFacture;
    private Float prixTotal;
    
    @FXML
    private TableView<LigneFacture> ligneFactureId;
    
    @FXML
    private TableColumn<LigneFacture, String> produitColumn;

    @FXML
    private TableColumn<LigneFacture, Float> prixUnitaireColumn;

    @FXML
    private TableColumn<LigneFacture, Integer> quantiteColumn;

    @FXML
    private TableColumn<LigneFacture, Date> dateEnregistrementColumn;

    @FXML
    private Text prixTotalLigneFacture;

    @FXML
    private Text idLigneFacture;

    public LigneFactureController(String s, Float p) {
        this.idFacture = s;
        this.prixTotal = p;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Manager.ouvertureEntityManager();
        
        idLigneFacture.setText(idFacture);
        prixTotalLigneFacture.setText(prixTotal.toString());
        chargerLigneFacture();
        ligneFactureId.setItems(listeFacture(idFacture));
        // Manager.fermertureEntityManager();
    }   
     
    private void chargerLigneFacture() {
       produitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneFacture, String>, ObservableValue<String>>() {
           @Override
           public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneFacture, String> param) {
               return param.getValue().getLigneFactureId().produitIdProperty();
           }
       });
       prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
       quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
//       dateEnregistrementColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnregistrement"));
    }
    
    private ObservableList<LigneFacture> listeFacture(String s) {
        return FXCollections.observableList(Manager.em.createQuery("SELECT f FROM LigneFacture f WHERE f.ligneFactureId.factureId = ?1", LigneFacture.class).setParameter(1, s).getResultList());
    }
    
}
