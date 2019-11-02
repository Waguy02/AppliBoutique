/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import dbManager.Manager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fournisseur;
import model.GestionnaireStock;
import model.Produit;
import outils.AlertMessages;
import outils.Constante;
import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class PageAjoutProduitController implements Initializable {

    List<Produit> listeProduit;
    private VBox vbox;
    @FXML
    private JFXButton ajouterFournisseur;
    @FXML
    private ScrollPane espaceProduit;
    @FXML
    private JFXComboBox<String> nomFour;
    private GestionnaireStock gestStock;
    private Stage stage;
    private int valTab = 0;
    PageAjoutProduitController(Stage stage, List<Produit> liste, GestionnaireStock gestStock) {
        listeProduit = liste;
        this.gestStock = gestStock;
        this.stage = stage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox = new VBox();
        vbox.setSpacing(10);
        remplirScroll();
        chargerFournisseur();
        espaceProduit.setContent(vbox);
        if (SuperMarche.langApp.equals(Constante.EN)) {
            valTab = 1;
        }
        if (SuperMarche.langApp.equals(Constante.FR)) {
            valTab = 0;
        }
    }

    private void chargerFournisseur() {
        List<Fournisseur> listeFournisseur = Manager.em.createNamedQuery("Fournisseur.findAll", Fournisseur.class).getResultList();
        //charger les listes de fournisseur de la bd dans listeFournisseur
        for (Fournisseur four : listeFournisseur) {
            nomFour.getItems().add(four.toString());
        }
    }

    @FXML
    private void ajouterFournissuer(ActionEvent event) {
        if (nomFour.getSelectionModel().getSelectedItem() != null) {
            String codeFournisseur = nomFour.getSelectionModel().getSelectedItem().split(" ")[0];
            if (!codeFournisseur.equals("")) {
                //on ajoute le fournisseur dans la base de données
                JFXCheckBox box = new JFXCheckBox();
                String code;
                for (Node check : vbox.getChildren()) {
                    box = (JFXCheckBox) check;
                    if (box.isSelected()) {
                        code = box.getText().split(" ")[0];
                        //on ajoute un produit au fournisseur dans la bd sous la forme
                        Produit prod = Manager.em.find(Produit.class, code);
                        if(!prod.listeIdFournisseur().contains(nomFour.getSelectionModel().getSelectedItem()))
                            gestStock.ajouterProduitFournisseur(codeFournisseur, code);
                    }
                }
                Alertes.information(AlertMessages.succes[valTab], AlertMessages.ajoutProduits[valTab]);
                stage.close();
            }
        }
        else{
            Alertes.alerte(AlertMessages.select[valTab], AlertMessages.selectFournisseur[valTab]);
        }
    }

    private void remplirScroll() {
        for (Produit prod : listeProduit) {
            vbox.getChildren().add(new JFXCheckBox(prod.toString()));
        }
    }

}
