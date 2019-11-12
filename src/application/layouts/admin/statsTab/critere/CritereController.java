/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.statsTab.critere;

import application.partials.IconedLabel;
import application.partials.inputs.AutoCompleteCombo;
import application.partials.inputs.LabelledAutoCombo;
import application.utilities.RecupListes;
import com.jfoenix.controls.JFXButton;
import constantes.Constantes;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class CritereController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private VBox rootVBox;
    @FXML
    private AnchorPane topBar;
    @FXML
    private HBox titleHbox;
    @FXML
    private FlowPane listeCriteres;
    @FXML
    private AnchorPane footBar;
    @FXML
    private HBox totalHbox;
    @FXML
    private JFXButton okButton;

    private LabelledAutoCombo prodLabel;
    private LabelledAutoCombo caissierLabel;
    private LabelledAutoCombo clientLabel;
    private ComboBox retardCombo;
    private ComboBox payeCombo;
    private TextField joursAvant;

    public CritereController(){
        
    }

    public FlowPane getListeCriteres() {
        return listeCriteres;
    }

    public void setListeCriteres(FlowPane listeCriteres) {
        this.listeCriteres = listeCriteres;
    }

    public LabelledAutoCombo getProdLabel() {
        return prodLabel;
    }

    public void setProdLabel(LabelledAutoCombo prodLabel) {
        this.prodLabel = prodLabel;
    }

    public LabelledAutoCombo getCaissierLabel() {
        return caissierLabel;
    }

    public void setCaissierLabel(LabelledAutoCombo caissierLabel) {
        this.caissierLabel = caissierLabel;
    }

    public LabelledAutoCombo getClientLabel() {
        return clientLabel;
    }

    public void setClientLabel(LabelledAutoCombo clientLabel) {
        this.clientLabel = clientLabel;
    }

    public ComboBox getRetardCombo() {
        return retardCombo;
    }

    public void setRetardCombo(ComboBox retardCombo) {
        this.retardCombo = retardCombo;
    }

    public ComboBox getPayeCombo() {
        return payeCombo;
    }

    public void setPayeCombo(ComboBox payeCombo) {
        this.payeCombo = payeCombo;
    }

    public TextField getJoursAvant() {
        return joursAvant;
    }

    public void setJoursAvant(TextField joursAvant) {
        this.joursAvant = joursAvant;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHboxTitre();
        initButtons();
        initFilters();
    }

    private void initHboxTitre() {
        titleHbox.setAlignment(Pos.CENTER);
        titleHbox.getChildren().add(IconedLabel.plot("Filtres", "Filter.png", true));
    }

    private void initButtons() {
        okButton.setGraphic(IconedLabel.plot("Valider", "Ok.png", true));

    }

    private void initFilters() {
        //filtre par produit
        AutoCompleteCombo prodCombo = new AutoCompleteCombo("nom", RecupListes.listeProduit());
        prodLabel = new LabelledAutoCombo("Produit ", prodCombo);
        this.listeCriteres.getChildren().add(prodLabel);

        //filtre par caissier
        AutoCompleteCombo caissierCombo = new AutoCompleteCombo("nom", RecupListes.listeCaissier());
        caissierLabel = new LabelledAutoCombo("Caissier ", caissierCombo);
        this.listeCriteres.getChildren().add(caissierLabel);

        //filtre par client
        AutoCompleteCombo clientCombo = new AutoCompleteCombo("nom", RecupListes.listeClient());
        clientLabel = new LabelledAutoCombo("Client ", clientCombo);
        this.listeCriteres.getChildren().add(clientLabel);

        //filtre par facture en retard
        retardCombo = new ComboBox();
        ArrayList<String> liste = new ArrayList<String>();
        liste.add(Constantes.TOUS);
        liste.add(Constantes.RETARD);
        liste.add(Constantes.OK);
        retardCombo.setItems(FXCollections.observableList(liste));
        this.listeCriteres.getChildren().add(retardCombo);

        //filtrer par facture paye ou pas
        payeCombo = new ComboBox();
        ArrayList<String> liste2 = new ArrayList<String>();
        liste2.add(Constantes.TOUS);
        liste2.add(Constantes.PAYE);
        liste2.add(Constantes.IMPAYE);
        payeCombo.setItems(FXCollections.observableList(liste2));
        this.listeCriteres.getChildren().add(payeCombo);

        //filtre sur le nombre de jours avant le retard
        joursAvant = new TextField();
        joursAvant.setPromptText("Moins de x jours avant le retard");
        this.listeCriteres.getChildren().add(joursAvant);
    }

    @FXML
    private void valider(ActionEvent event) {

    }
}
