/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.mainTabPane;

import application.layouts.caissiere.productTable.ProductTableController;
import application.layouts.caissiere.salePane.SalePaneController;
import static application.utilities.Tools.quickAlert;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.Caissier;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class MainTabPaneController implements Initializable {

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab productTableTab;

   
    ObservableList<Produit> listeProduit;
    ObservableList<Produit> listeAvailabeProduit=FXCollections.observableArrayList();
    
    private ObservableList<SalePaneController> currentSales = FXCollections.observableArrayList();
    public ObservableList<SalePaneController> getCurrentSales() {
        return currentSales;
    }

    private Caissier emp = new Caissier();

    public Caissier getEmp() {
        return emp;
    }

    public void setEmp(Caissier emp) {
        this.emp = emp;
    }

    public void setCurrentSales(ObservableList<SalePaneController> currentSales) {
        this.currentSales = currentSales;
    }

    public ObservableList<Produit> getListeAvailabeProduit() {
        return listeAvailabeProduit;
    }

    public void setListeAvailabeProduit(ObservableList<Produit> listeAvailabeProduit) {
        this.listeAvailabeProduit = listeAvailabeProduit;
    }

   

   
 
    private ProductTableController productTableController;
    @FXML
    private AnchorPane rootAnchor;
    @FXML
    private BorderPane rootBorder;
    @FXML
    private AnchorPane bottomBar;

    /**
     * Initializes the controller class.
     */
   
    public JFXTabPane getMainTabPane() {
        return mainTabPane;
    }

    public Tab getProductTableTab() {
        return productTableTab;
    }

    public ProductTableController getProductTableController() {
        return productTableController;
    }

    public AnchorPane getRootAnchor() {
        return rootAnchor;
    }

    public BorderPane getRootBorder() {
        return rootBorder;
    }

    public AnchorPane getBottomBar() {
        return bottomBar;
    }

    public void setMainTabPane(JFXTabPane mainTabPane) {
        this.mainTabPane = mainTabPane;
    }

    public void setProductTableTab(Tab productTableTab) {
        this.productTableTab = productTableTab;
    }

    public void setProductTableController(ProductTableController productTableController) {
        this.productTableController = productTableController;
    }

    public void setRootAnchor(AnchorPane rootAnchor) {
        this.rootAnchor = rootAnchor;
    }

    public void setRootBorder(BorderPane rootBorder) {
        this.rootBorder = rootBorder;
    }

    public void setBottomBar(AnchorPane bottomBar) {
        this.bottomBar = bottomBar;
    }

    public ObservableList<Produit> getListeProduit() {
        return listeProduit;
    }

    public void setListeProduit(ObservableList<Produit> listeProduit) {
        this.listeProduit = listeProduit;
    }
    
    
    
    
    public void initProductTab() {
        FXMLLoader loader = getLoader("layouts/caissiere/productTable/productTable");
        AnchorPane root = (AnchorPane) getView(loader);
        this.productTableTab.setContent(root);
        this.setProductTableController(loader.getController());
        
        this.productTableController.setMainController(this);
      this.productTableController.customInit();

    }

    public void makeResponsive() {
        /**
         *
         * Ici la méthode permettant de rendre le layout entièrement responsive
         *
         */

        makeResponsive();
        AnchorPane root = (AnchorPane) this.productTableTab.getContent();
        root.minWidthProperty().bind((mainTabPane.widthProperty()));
    }

    public void initiateSale() {
        Integer currentSaleCounter = this.currentSales.size() + 1;

        FXMLLoader loader = getLoader("layouts/caissiere/salePane/salePane");
        AnchorPane root = (AnchorPane) getView(loader);
        Tab saleTab = new Tab("Vente " + currentSaleCounter.toString(), root);
        SalePaneController controller;
        try {

            controller = loader.getController();
            controller.setMainController(this);
            controller.customInit();
            this.currentSales.add(controller);
            this.mainTabPane.getTabs().add(saleTab);

        } catch (Exception e) {
            quickAlert(AlertType.ERROR, "Echec de l'initialisation de la vente \n "+e.getStackTrace());
            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }
        
        

    }

    public void loadData() {
        this.listeProduit = FXCollections.observableArrayList(this.getEmp().getMapProduits().values());
        for(Produit p :this.listeProduit){
            this.listeAvailabeProduit.add(p.clone());
        }
            
        
        
        
        
    }
     

 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
        initProductTab();

    }
    

}
