/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.usersTab;

import application.components.userCrudBox.UserCrudBoxController;
import application.layouts.admin.usersTab.userTable.UserTableController;
import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledCombo;
import static application.utilities.AlertsManager.showConfirmation;
import static application.utilities.TableViewManager.enableUserSimpleFiltering;
import static application.utilities.Tools.quickAlert;

import application.utilities.ViewDimensionner;
import static application.utilities.ViewDimensionner.bindSizes;
import application.utilities.ViewLoaders;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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

    private LabelledCombo searchCombo;

  
    
    @FXML
    private AnchorPane tableAnchor;

    private UserTableController tableController;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private HBox subActionBox;
    @FXML
    private HBox searchBarHbox;
    @FXML
    private JFXTextField searchBarTextField;
    private JFXButton newUserButton;
    @FXML
    private AnchorPane actionBox;
    @FXML
    private JFXButton addButton;

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

        initSearchAnchor();
        
        
    

    }

    public void initSearchAnchor() {


       ViewDimensionner.bindSizes(searchBarHbox, actionBox, 1, 0.9);
     this.searchBarHbox.getChildren().add(0, (new IconedLabel("", "search.png")).plot(false));
   this.searchBarHbox.getChildren().add(2, Separators.formSeparatorV(50));
    addButton.setGraphic(IconedLabel.plot("Nouvel Utilisateur", "add_64px.png", true));
    
    }

    public void initTableAnchor() {

        bindSizes(this.tableAnchor, this.rootVBox, 1, 0.8);

         FXMLLoader loader = getLoader("layouts/admin/usersTab/userTable/userTable");

        AnchorPane rootTable = (AnchorPane) getView(loader);
        this.tableAnchor.getChildren().add(rootTable);

        bindSizes(rootTable, this.tableAnchor, 1, 1);

        this.tableController = loader.getController();

        this.tableController.setListData(this.admin.listeEmployes(false));
        this.tableController.customInit();
        
        
            enableUserSimpleFiltering(this.tableController.getTableData(),this.tableController.getListData(), this.searchBarTextField);
            
            
            this.tableController.getTableData().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
    @Override
           public void handle(MouseEvent t) {
            

           selectedEmploye=tableController.getTableData().getSelectionModel().getSelectedItem();
           if(selectedEmploye==null)return;

            
                if(t.getClickCount() != 2)        return;
                    ViewLoaders.openUserCrud(selectedEmploye, admin, tableController.getListData(), false,false, true);
            
            
                
            
        }
    });
    }
    private Employe selectedEmploye;

    

    
    
    
    public void initAddButton() {

        
    

        addButton.setOnAction(event -> {

     ViewLoaders.openUserCrud(this.selectedEmploye,this.admin, this.tableController.getListData(), true, false, false);

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

        bindSizes(this.subActionBox, this.rootVBox, 1, 0.1);

        this.initAddButton();
        this.initDeleteButton();
        this.subActionBox.getChildren().addAll( Separators.maxSeparatorV(), deleteButton);

    }

    @FXML
    private void createNewUser(ActionEvent event) {
    }
    
    
    

}
