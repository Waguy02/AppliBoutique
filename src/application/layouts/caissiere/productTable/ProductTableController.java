/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.productTable;

import application.layouts.caissiere.mainTabPane.MainTabPaneController;
import application.partials.CustomTableColumn;
import application.partials.IconedLabel;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Caissier;
import model.Produit;
import static application.utilities.TableViewManager.addTableColumns;
import static application.utilities.TableViewManager.enableProductSimpleFiltering;
import com.jfoenix.controls.JFXButton;
import java.util.function.Predicate;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author test
 */
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

    
    
    private MainTabPaneController mainController;

    public HBox getSearchBarHbox() {
        return searchBarHbox;
    }

    public void setSearchBarHbox(HBox searchBarHbox) {
        this.searchBarHbox = searchBarHbox;
    }

    public JFXTextField getSearchBarTextField() {
        return searchBarTextField;
    }

    public void setSearchBarTextField(JFXTextField searchBarTextField) {
        this.searchBarTextField = searchBarTextField;
    }

    public TableView<Produit> getProductTable() {
        return productTable;
    }

    public void setProductTable(TableView<Produit> productTable) {
        this.productTable = productTable;
    }

    public AnchorPane getRootAnchor() {
        return rootAnchor;
    }

    public void setRootAnchor(AnchorPane rootAnchor) {
        this.rootAnchor = rootAnchor;
    }

    public VBox getRootVBox() {
        return rootVBox;
    }

    public void setRootVBox(VBox rootVBox) {
        this.rootVBox = rootVBox;
    }

    public MainTabPaneController getMainController() {
        return mainController;
    }

    public void setMainController(MainTabPaneController mainController) {
        this.mainController = mainController;
    }

    public AnchorPane getTopBar() {
        return topBar;
    }

    public void setTopBar(AnchorPane topBar) {
        this.topBar = topBar;
    }

    public JFXButton getNewSaleButton() {
        return newSaleButton;
    }

    public void setNewSaleButton(JFXButton newSaleButton) {
        this.newSaleButton = newSaleButton;
    }
    
    
    
    @FXML
    private AnchorPane topBar;
    @FXML
    private JFXButton newSaleButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
      
        
        
    }
    
    public void customInit(){
          newSaleButton.setGraphic(IconedLabel.plot("Nouvelle Vente","add_64px.png",true));
        initProductTable();
        makeResponsive();
        initSearchBar();
      
        
        
    }

   
    
    public void initProductTable() {
        rootVBox.minWidthProperty().bind(rootAnchor.widthProperty());

        CustomTableColumn<Produit, String> colID = new CustomTableColumn("ID", "id", 15.0),
                colCategorie = new CustomTableColumn("Categorie", "categorie",30.0),
                colLibelle = new CustomTableColumn("nom", "Nom", 30.0);
        CustomTableColumn<Produit, Integer> colQuantite = new CustomTableColumn("Quantite", "quantite", 20.0);

        addTableColumns(productTable, colID, colLibelle, colCategorie, colQuantite);

     
System.out.println(this.mainController);        
      enableProductSimpleFiltering(productTable,this.mainController.getListeProduit(),this.searchBarTextField);

    }

    public void makeResponsive() {

        productTable.minHeightProperty().bind(rootVBox.heightProperty().subtract(topBar.heightProperty()));
    }
    
    
    
    public void initSearchBar(){
        
        this.searchBarHbox.getChildren().add(0,(new IconedLabel("","search.png")).plot(false));
        
        
        Predicate predicate=produit->produit==null;
    }

   
    
   
    
    
    
    
    
    
    
    @FXML
    private void startNewSale(ActionEvent event) {
        
        
        this.mainController.initiateSale();
    }

}

