/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.productTable;

import application.layouts.admin.mainAdminPane.MainAdminPaneController;
import application.layouts.admin.stockTab.commandBox.CommandBoxController;
import application.layouts.caissiere.mainCaissierPane.MainCaissierPaneController;
import application.partials.table.CustomSimpleColumn;
import application.partials.IconedLabel;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Produit;
import static application.utilities.TableViewManager.addTableColumns;
import static application.utilities.TableViewManager.enableProductSimpleFiltering;
import static application.utilities.Tools.blur;
import static application.utilities.Tools.unBlur;
import application.utilities.ViewLoaders;
import com.jfoenix.controls.JFXButton;
import java.util.function.Predicate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;

/**
 * FXML Controller class
 *
 * @author test
 */

@Getter @Setter
public class ProductTableController implements Initializable {

    @FXML
    private HBox searchBarHbox;
    @FXML
    private JFXTextField searchBarTextField;
    @FXML
    private TableView<Produit> productTable;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private VBox rootVBox;

    private AnchorPane commandAnchor;

    private Administrateur admin;


     private MainCaissierPaneController mainController;
    private MainAdminPaneController mainAdminPaneController;

    private ObservableList<Produit> listeProduit;

    @FXML
    private AnchorPane topBar;

    @FXML
    private JFXButton newSaleButton;

    private JFXButton newCommandButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void customInit() {

        initNewSaleButton();
        initNewCommandButton();
        initModes();
        initCommandBox();

        initProductTable();
        makeResponsive();
        initSearchBar();

    }

    public void initProductTable() {
        rootVBox.minWidthProperty().bind(rootAnchor.widthProperty());
        CustomSimpleColumn<Produit, String> colID = new CustomSimpleColumn("ID", "id", 0.15);
        CustomSimpleColumn<Produit, String> colCategorie = new CustomSimpleColumn("Categorie", "categorie", 0.30), colLibelle = new CustomSimpleColumn("nom", "Nom", 0.30);
        CustomSimpleColumn<Produit, Integer> colQuantite = new CustomSimpleColumn("Quantite", "quantite", 0.30);

        addTableColumns(productTable, colID, colLibelle, colCategorie, colQuantite);

        System.out.println(this.searchBarTextField);

        enableProductSimpleFiltering(productTable,listeProduit, this.searchBarTextField);
        this.makeTableClickable();
    }

    public void makeResponsive() {

        productTable.minHeightProperty().bind(rootVBox.heightProperty().subtract(topBar.heightProperty()));
    }
    
    

    public void initSearchBar() {

        this.searchBarHbox.getChildren().add(0, (new IconedLabel("", "search.png")).plot(false));

        Predicate predicate = produit -> produit == null;
    }

    @FXML
    private void startNewSale(ActionEvent event) {

        this.mainController.initiateSale();
    }

    private BooleanProperty saleMode = new SimpleBooleanProperty(false), managingMode = new SimpleBooleanProperty(false);

    public JFXButton getNewCommandButton() {
        return newCommandButton;
    }

    public void setNewCommandButton(JFXButton newCommandButton) {
        this.newCommandButton = newCommandButton;
    }

    public BooleanProperty getSaleMode() {
        return saleMode;
    }

    public void setSaleMode(BooleanProperty saleMode) {
        this.saleMode = saleMode;
    }

    public BooleanProperty getManagingMode() {
        return managingMode;
    }

    public void setManagingMode(BooleanProperty managingMode) {
        this.managingMode = managingMode;
    }

    public void initModes() {

        // Les boutons sont associés à des modes spécifiques. Le bouton nouvel vente est associé à saleMode en l'occcurence
        this.newCommandButton.visibleProperty().bind(managingMode);
        this.newSaleButton.visibleProperty().bind(saleMode);

    }

    public void initNewSaleButton() {
        newSaleButton.setGraphic(IconedLabel.plot("Nouvelle Vente", "add_64px.png", true));
    }

    public void initNewCommandButton() {
        this.newCommandButton = new JFXButton();
        newCommandButton.setGraphic(IconedLabel.plot("Nouvelle Commande", "download.png", true));
        this.searchBarHbox.getChildren().add(newCommandButton);
        this.newCommandButton.setOnAction(event -> openCommandBox());

    }

    private CommandBoxController commandController;
    private BooleanProperty isActiveCommand = new SimpleBooleanProperty(false);

    public void initCommandBox() {

        isActiveCommand.addListener((observable, oldValue, newValue) -> {
            if (newValue) {

                Stage addStage = new Stage();
                addStage.setOnCloseRequest(event -> isActiveCommand.set(false));
                blur(this.rootVBox);

                FXMLLoader loader = ViewLoaders.getLoader("layouts/admin/stockTab/commandBox/commandBox");

                this.commandAnchor = (AnchorPane) ViewLoaders.getView(loader);
                
                this.commandAnchor.setStyle("-fx-border-style:solid inside;-fx-border-radius:1em");
                this.commandController = loader.getController();

                commandController.setAdministrateur(this.admin);

                addStage.setTitle("Nouvelle commande de produits");
                addStage.initModality(Modality.APPLICATION_MODAL);

                addStage.setScene(new Scene(this.commandAnchor));
                Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                addStage.setMinWidth(screenBounds.getWidth() * 0.8);
                addStage.setMinHeight(screenBounds.getHeight() * 0.9);
                this.commandController = loader.getController();
                addStage.show();

            } else {

                unBlur(this.rootVBox);

            }

        });
        this.isActiveCommand.set(false);

    }

    public void openCommandBox() {

        this.isActiveCommand.set(true);
        this.commandController.customInit();

    }
    
    
    
    
    
    
    Produit selectedProduct;
    
    Boolean readingMode=false,editingMode=false,registeringMode=false;
    public void makeTableClickable(){

        ContextMenu cm = new ContextMenu();
        cm.getStyleClass().add("default_context");
        MenuItem menu = new MenuItem("Ouvrir la fiche du produit");
        
                MenuItem menu2 = new MenuItem("Vendre ");
        cm.getItems().add(menu);
        cm.getItems().add(menu2);
        
        
        
        
        
        if(this.saleMode.get()){
            readingMode=true;
            
            
        }
        
        if(this.managingMode.get()){
            editingMode=true;
            registeringMode=true;
                
        }
        menu.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                           ViewLoaders.openProductCard(selectedProduct,listeProduit,readingMode,editingMode,registeringMode);
                        }});


        this.productTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    @Override
           public void handle(MouseEvent t) {
            cm.hide();

           selectedProduct=productTable.getSelectionModel().getSelectedItem();
           if(selectedProduct==null)return;

            if(t.getButton() == MouseButton.SECONDARY) {
            System.out.println("Bouton droit");   
                cm.show(productTable, t.getScreenX(), t.getScreenY());
            }
        }
    });


        menu2.setOnAction(event->{
            
            openProductSale(selectedProduct);
            
        });





    }
    
    
    
    
    
    public void openProductSale(Produit product){
        
        
    }
    
    
    

}
