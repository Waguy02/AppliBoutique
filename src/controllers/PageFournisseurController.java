/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Administrateur;
import model.Fournisseur;
import model.GestionnaireStock;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class PageFournisseurController implements Initializable {

    @FXML
    private JFXTextField nomFournisseur;
    @FXML
    private JFXTextField phoneFournisseur;
    @FXML
    private ScrollPane espaceProduit;
    private List<Produit> listeProduit;
    private VBox vbox;
    private GestionnaireStock gestStock;
    @FXML
    private JFXTextField adresseFournisseur;
    private Stage stage;
    private ObservableList<String> listeFournisseur;
    public PageFournisseurController(Stage stage, List<Produit> listeProduit, GestionnaireStock gestStock, ObservableList<String> listeFournisseur) {
       this.listeProduit = listeProduit;
       this.gestStock = gestStock;
       this.stage = stage;
       this.listeFournisseur = listeFournisseur;
    }

    @FXML
    private JFXButton ajouterFournisseur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vbox = new VBox();
        vbox.setSpacing(10);
        remplirScroll();
        espaceProduit.setContent(vbox);
    }

    @FXML
    private void ajouterFournissuer(ActionEvent event) {
        if(!nomFournisseur.getText().equals("")){
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setAdresse(adresseFournisseur.getText());
            fournisseur.setNom(nomFournisseur.getText());
            fournisseur.setTelephone(phoneFournisseur.getText());
            fournisseur.genererCode();
            //on ajoute le fournisseur dans la base de données
            Administrateur admin = new Administrateur();
            admin.ajouterFournisseur(fournisseur);
            JFXCheckBox box = new JFXCheckBox();
            String code;
            for(Node check : vbox.getChildren()){
                box = (JFXCheckBox) check;
                if (box.isSelected()) {
                    code = box.getText().split(" ")[0];
                    //on ajoute un produit au fournisseur dans la bd sous la forme
                    gestStock.ajouterProduitFournisseur(fournisseur.getId(), code);
                }
            }
            Alertes.information("Opération réussie", "Le fournisseur a correctement été ajouté");
            listeFournisseur.add(fournisseur.getId() + " " + fournisseur.getNom());
            stage.close();
        }
        else{
            Alertes.alerte("Complétez les champs", "Complétez le champ Nom");
        }
    }
    private void remplirScroll() {
        for(Produit prod: listeProduit) {
            vbox.getChildren().add(new JFXCheckBox(prod.toString()));
        }
    }
}
