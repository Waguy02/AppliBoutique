/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.statsTab.critere;

import application.partials.IconedLabel;
import application.partials.inputs.AutoCompleteCombo;
import application.partials.inputs.LabelledAutoCombo;
import com.jfoenix.controls.JFXButton;
import dbManager.Manager;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Caissier;
import model.Client;
import model.Produit;

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
    private ObservableList list;
    private TableView table;
    
    public CritereController(TableView table, ObservableList list) {
        this.list = list;
        this.table = table;
    }

    public ObservableList getList() {
        return list;
    }

    public void setList(ObservableList list) {
        this.list = list;
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
        AutoCompleteCombo prodCombo = new AutoCompleteCombo("nom", listeProduit());
        prodLabel = new LabelledAutoCombo("Produit ", prodCombo);
        this.listeCriteres.getChildren().add(prodLabel);

        //filtre par caissier
        AutoCompleteCombo caissierCombo = new AutoCompleteCombo("nom", listeCaissier());
        caissierLabel = new LabelledAutoCombo("Caissier ", caissierCombo);
        this.listeCriteres.getChildren().add(caissierLabel);

        //filtre par client
        AutoCompleteCombo clientCombo = new AutoCompleteCombo("nom", listeClient());
        clientLabel = new LabelledAutoCombo("Client ", clientCombo);
        this.listeCriteres.getChildren().add(clientLabel);

        //filtre par facture en retard
        retardCombo = new ComboBox();
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("");
        liste.add("En retard");
        liste.add("Ok");
        retardCombo.setItems(FXCollections.observableList(liste));
        this.listeCriteres.getChildren().add(retardCombo);

        //filtrer par facture paye ou pas
        payeCombo = new ComboBox();
        ArrayList<String> liste2 = new ArrayList<String>();
        liste2.add("");
        liste2.add("Payé");
        liste2.add("Impayé");
        payeCombo.setItems(FXCollections.observableList(liste2));
        this.listeCriteres.getChildren().add(payeCombo);

    }

    private ObservableList<Caissier> listeCaissier() {
        //Manager.ouvertureEntityManager();
        return FXCollections.observableList(Manager.em.createNamedQuery("Caissier.findAll", Caissier.class).getResultList());
    }

    private ObservableList<Produit> listeProduit() {
        //Manager.ouvertureEntityManager();
        return FXCollections.observableList(Manager.em.createNamedQuery("Produit.findAll", Produit.class).getResultList());
    }

    private ObservableList listeClient() {
        return FXCollections.observableList(Manager.em.createNamedQuery("Client.findAll", Client.class).getResultList());
    }

    @FXML
    private void valider(ActionEvent event) {

    }

    public void enableProductSimpleFiltering(TableView table, ObservableList list) {
        FilteredList flListe = new FilteredList(list, p -> true);
        Produit pr = (Produit) (prodLabel.getCombo().getSelectionModel().getSelectedItem());
        if(pr != null){
            
        }
        /*searchBar.textProperty().addListener(((observable, oldValue, newValue) -> {
            Predicate p0 = p -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                Produit prod = (Produit) p;
                if ((prod).getNom().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getId().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getCategorie().getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            };
            flListe.setPredicate(p0);
        }));
        SortedList<Produit> sortedData = new SortedList<>(flListe);
        table.setItems(sortedData);*/
    }
}
