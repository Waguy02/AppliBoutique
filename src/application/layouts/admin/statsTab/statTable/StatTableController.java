/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.statsTab.statTable;

import application.layouts.admin.statsTab.critere.CritereController;
import application.layouts.admin.statsTab.detail.DetailController;
import application.partials.IconedLabel;
import application.partials.table.CustomSimpleColumn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Facture;
import static application.utilities.TableViewManager.addTableColumns;
import application.utilities.ViewLoaders;
import constantes.Constantes;
import controllers.Alertes;
import java.io.IOException;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import outils.AlertMessages;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class StatTableController implements Initializable {

    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private VBox rootVBox;
    @FXML
    private AnchorPane topBar;
    @FXML
    private HBox searchBarHbox;
    @FXML
    private JFXDatePicker datePickDebut;
    @FXML
    private JFXDatePicker datePickFin;
    @FXML
    private JFXButton rechercher;
    @FXML
    private JFXButton genererGraphe;
    @FXML
    private JFXButton addCriteres;
    @FXML
    private TableView<Facture> statTable;
    @FXML
    private HBox generateHbox;
    @FXML
    private JFXCheckBox sendMail;
    @FXML
    private JFXButton genererRapport;
    @FXML
    private JFXButton afficherFacture;
    @FXML
    private AnchorPane footBar;

    private CritereController critereController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        makeResponsive();
        initFactureTable();
        initButtons();
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void createGraph(ActionEvent event) {
    }

    @FXML
    private void addCriteres(ActionEvent event) {
        FXMLLoader loader = ViewLoaders.getLoader("layouts/admin/statsTab/critere/critere");
        try {
            System.out.println("Je suis entré");
            loader.setControllerFactory(c -> {
                System.out.println("Je suis entré");
                return new DetailController(fac.getId(), fac.getMontant());
            });
            TableViewMa
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initFactureTable() {
        rootVBox.minWidthProperty().bind(rootAnchor.widthProperty());
        CustomSimpleColumn<Facture, Date> colDate = new CustomSimpleColumn("Date", "dateEnregistrement", 15.0);
        CustomSimpleColumn<Facture, Float> colTotal = new CustomSimpleColumn("Total", "montant", 25.0);
        CustomSimpleColumn<Facture, String> colID = new CustomSimpleColumn("ID", "id", 15.0);
        CustomSimpleColumn<Facture, String> colCaissier = new CustomSimpleColumn("Caissier", 20.0, Constantes.CAISSIER);
        CustomSimpleColumn<Facture, String> colClient = new CustomSimpleColumn("Client", 20.0, Constantes.CLIENT);
        CustomSimpleColumn<Facture, Boolean> colPaye = new CustomSimpleColumn("Soldée ?", "paye", 5.0);

        addTableColumns(statTable, colID, colCaissier, colClient, colDate, colTotal, colPaye);
    }

    public void makeResponsive() {
        statTable.minHeightProperty().bind(rootVBox.heightProperty().subtract(topBar.heightProperty()).subtract(footBar.heightProperty()));
    }

    public void initButtons() {
        rechercher.setGraphic(IconedLabel.plot("Rechercher", "Search.png", true));
        genererGraphe.setGraphic(IconedLabel.plot("Générer le graphe", "stats.png", true));
        addCriteres.setGraphic(IconedLabel.plot("Plus de filtres", "plus.png", true));
        sendMail.setGraphic(IconedLabel.plot("Envoyer par mail", "mail.png", true));
        genererRapport.setGraphic(IconedLabel.plot("Générer le rapport", "pdf.png", true));
        afficherFacture.setGraphic(IconedLabel.plot("Détails", "bill.png", true));

    }

    @FXML
    private void genererRapport(ActionEvent event) {

    }

    @FXML
    private void afficherFacture(ActionEvent event) {
        Facture fac = statTable.getSelectionModel().getSelectedItem();
        afficherLigneFacture(fac, "layouts/admin/statsTab/detail/detail", "Ligne Facture");

    }

    private void afficherLigneFacture(Facture fac, String source, String titre) {
        FXMLLoader loader = ViewLoaders.getLoader(source);
        try {
            if (fac != null) {
                System.out.println("Je suis entré");
                loader.setControllerFactory(c -> {
                    System.out.println("Je suis entré");
                    return new DetailController(fac.getId(), fac.getMontant());
                });
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle(titre);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } else {
                Alertes.alerte(AlertMessages.selectElement[0], AlertMessages.selectElementMessage[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
