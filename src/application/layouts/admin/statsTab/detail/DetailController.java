/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.statsTab.detail;

import application.partials.IconedLabel;
import application.partials.table.CustomSimpleColumn;
import static application.utilities.TableViewManager.addTableColumns;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Facture;
import model.LigneFacture;
import constantes.Constantes;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class DetailController implements Initializable {

    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private VBox rootVBox;
    @FXML
    private AnchorPane topBar;
    @FXML
    private TableView<LigneFacture> statTable;
    @FXML
    private AnchorPane footBar;

    private String idFacture;
    private Float prixTotal;
    @FXML
    private HBox titleHbox;
    @FXML
    private HBox totalHbox;

    public DetailController(String s, Float p) {
        this.idFacture = s;
        this.prixTotal = p;
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Manager.ouvertureEntityManager();
        initHboxTotal();
        initHboxTitre();
        makeResponsive();
       
        chargerLigneFacture();
        statTable.setItems(listeFacture(idFacture));
        // Manager.fermertureEntityManager();
    }

    private void chargerLigneFacture() {
        rootVBox.minWidthProperty().bind(rootAnchor.widthProperty());
        CustomSimpleColumn<LigneFacture, String> colID = new CustomSimpleColumn("ID", "id", 20.0);
        CustomSimpleColumn<LigneFacture, String> colProd = new CustomSimpleColumn("Produit", 30.0, Constantes.LIGNEFACTURE);
        CustomSimpleColumn<LigneFacture, String> colPU = new CustomSimpleColumn("Prix Unitaire", "prixUnitaire", 30.0);
        CustomSimpleColumn<LigneFacture, Boolean> colQte = new CustomSimpleColumn("Quantite", "quantite", 20.0);

        addTableColumns(statTable, colID, colProd, colPU, colQte);    
    }

    private void initHboxTitre() {
        titleHbox.setAlignment(Pos.CENTER);
        titleHbox.getChildren().add(IconedLabel.plot("Ligne Facture :", "bill.png", true));
        titleHbox.getChildren().add(new Label(idFacture));
    }
    
    private void initHboxTotal() {
        totalHbox.setAlignment(Pos.CENTER);
        totalHbox.getChildren().add(IconedLabel.plot("Total :", "Cash.png", true));
        totalHbox.getChildren().add(new Label(prixTotal.toString()));
    }
    private ObservableList<LigneFacture> listeFacture(String s) {
        return FXCollections.observableList(Manager.em.createQuery("SELECT f FROM LigneFacture f WHERE f.ligneFactureId.factureId = ?1", LigneFacture.class).setParameter(1, s).getResultList());
    }
    
    public void makeResponsive() {
        statTable.minHeightProperty().bind(rootVBox.heightProperty().subtract(topBar.heightProperty()).subtract(footBar.heightProperty()));
    }

}
