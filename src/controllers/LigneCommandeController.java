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
import model.LigneCommande;

/**
 *
 * @author E_DinaBrown
 */
public class LigneCommandeController implements Initializable{

    private String idCommande;
    private Float totalCommande;
    
    @FXML
    private TableView<LigneCommande> ligneCommandeId;
    
    @FXML
    private TableColumn<LigneCommande, String> produitColumn;

    @FXML
    private TableColumn<LigneCommande, Float> prixUnitaireColumn;

    @FXML
    private TableColumn<LigneCommande, Integer> quantiteColumn;

    @FXML
    private TableColumn<LigneCommande, Date> dateEnregistrementColumn;

    @FXML
    private TableColumn<LigneCommande, String> fournisseurColumn;

    @FXML
    private Text prixTotalLigneComande;

    @FXML
    private Text idLigneCommande;
    
    public LigneCommandeController(String s, Float p) {
        this.idCommande = s;
        this.totalCommande = p;
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Manager.ouvertureEntityManager();
        
        idLigneCommande.setText(idCommande);
        prixTotalLigneComande.setText(totalCommande.toString());
        chargerLigneCommande();
        ligneCommandeId.setItems(listeCommande(idCommande));
        
        // Manager.fermertureEntityManager();
    }
    
    private void chargerLigneCommande() {
        produitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneCommande, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneCommande, String> param) {
                return param.getValue().getLigneCommandeId().produitidProperty();
            }
        });
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
//        dateEnregistrementColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        fournisseurColumn.setCellValueFactory(new PropertyValueFactory<>("fournisseurid"));
    }
    
    private ObservableList<LigneCommande> listeCommande(String s) {
        return FXCollections.observableList(Manager.em.createQuery("SELECT f FROM ligneCommande f WHERE f.ligneCommandeId.commandeid = ?1", LigneCommande.class).setParameter(1, s).getResultList());
    }
    
}
