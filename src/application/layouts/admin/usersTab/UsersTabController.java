/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.usersTab;

import application.layouts.admin.usersTab.userCrudBox.UserCrudBoxController;
import application.layouts.admin.usersTab.userTable.UserTableController;
import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledAutoCombo;
import static application.utilities.AlertsManager.showConfirmation;
import static application.utilities.Tools.quickAlert;

import application.utilities.ViewDimensionner;
import static application.utilities.ViewDimensionner.bindSizes;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;
import model.Employe;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter
@Setter
public class UsersTabController implements Initializable, CustomController {

    @FXML
    private VBox rootVBox;

    private Administrateur admin;

    private LabelledAutoCombo searchCombo;

    @FXML
    private HBox actionBox;
    @FXML
    private AnchorPane tableAnchor;

    private UserTableController tableController;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private HBox subActionBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void customInit() {

        initActionBox();
        initTableAnchor();
        initSubActionBox();
    }

    public void initActionBox() {

        ViewDimensionner.bindSizes(actionBox, rootVBox, 1, 0.1);

        initSearchCombo();

    }

    public void initSearchCombo() {

        this.searchCombo = new LabelledAutoCombo("", new JFXComboBox(), 0.3, 0.7);
        this.searchCombo.getLabel().setGraphic(IconedLabel.plot("Recherche", "search.png", true));
        bindSizes(this.searchCombo, this.actionBox, 0.5, 0.8);

        this.actionBox.getChildren().add(searchCombo);

    }

    public void initTableAnchor() {

        bindSizes(this.tableAnchor, this.rootVBox, 1, 0.69);

        FXMLLoader loader = getLoader("layouts/admin/usersTab/userTable/userTable");

        AnchorPane rootTable = (AnchorPane) getView(loader);
        this.tableAnchor.getChildren().add(rootTable);

        bindSizes(rootTable, this.tableAnchor, 1, 1);

        this.tableController = loader.getController();

        this.tableController.setListData(this.admin.listeEmployes(false));
        this.tableController.customInit();

    }

    private JFXButton addButton;

    public void initAddButton() {

        addButton = new JFXButton();
        addButton.setGraphic(IconedLabel.plot("Ajouter un utilisateur", "add2.png", true));

        addButton.setOnAction(event -> {

            Stage addStage = new Stage();
            addStage.setTitle("Ajout d'utilisateur");
            addStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = getLoader("layouts/admin/usersTab/userCrudBox/userCrudBox");
            AnchorPane root = (AnchorPane) getView(loader);
            addStage.setScene(new Scene(root));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            addStage.setMinWidth(screenBounds.getWidth() * 0.5);
            addStage.setMinHeight(screenBounds.getHeight() * 0.9);
            UserCrudBoxController addController = loader.getController();

            addController.getREGISTERING_MODE().set(true);
            addController.setAdmin(this.admin);
            addController.customInit();

            addStage.show();
            addController.getOPEN_PROPERTY().addListener((observale, oldValue, newValue) -> {;
                if (!newValue) {
                    addStage.close();
                }
            }
            );

        });

    }

    private JFXButton deleteButton;

    public void initDeleteButton() {

        deleteButton = new JFXButton();
        deleteButton.setGraphic(IconedLabel.plot("Supprimer l'utilisateur", "discard.png", true));
        deleteButton.visibleProperty().bind(this.tableController.getTableData().getSelectionModel().selectedItemProperty().isNotNull());
        deleteButton.setOnAction(event -> {

            Employe currentEmploye = this.tableController.getTableData().getSelectionModel().getSelectedItem();
            if (showConfirmation("Voulez vous vraiment supprimer cet utilisateur")) {
                this.tableController.getTableData().getItems().remove(currentEmploye);
            }
            this.admin.supprimerEmploye(currentEmploye);
            quickAlert(AlertType.INFORMATION, "Employe supprimé avec succès");

        });

    }

    public void initSubActionBox() {

        bindSizes(this.subActionBox, this.rootVBox, 1, 0.7);

        this.initAddButton();
        this.initDeleteButton();
        this.subActionBox.getChildren().addAll(addButton, Separators.maxSeparatorV(), deleteButton);

    }

}
