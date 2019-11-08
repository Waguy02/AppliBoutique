/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.stockTab;

import application.layouts.caissiere.productTable.ProductTableController;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import application.utilities.interfaces.CustomController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author test
 */
public class StockTabController implements Initializable,CustomController {

    @FXML
    private AnchorPane rootVBox;
    @FXML
    private StackPane rootStack;
    @FXML
    private BorderPane rootBorder;
    
    private ProductTableController productTableController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void customInit() {
        initTableController();
    }

    public AnchorPane getRootVBox() {
        return rootVBox;
    }

    public void setRootVBox(AnchorPane rootVBox) {
        this.rootVBox = rootVBox;
    }

    public StackPane getRootStack() {
        return rootStack;
    }

    public void setRootStack(StackPane rootStack) {
        this.rootStack = rootStack;
    }

    public BorderPane getRootBorder() {
        return rootBorder;
    }

    public void setRootBorder(BorderPane rootBorder) {
        this.rootBorder = rootBorder;
    }

    public ProductTableController getProductTableController() {
        return productTableController;
    }

    public void setProductTableController(ProductTableController productTableController) {
        this.productTableController = productTableController;
    }
    
    
    
    
    
    
    public void initTableController(){
        
        
        
    }
    
    
    
        public void initProductTab() {
        FXMLLoader loader = getLoader("layouts/caissiere/productTable/productTable");
        AnchorPane root = (AnchorPane) getView(loader);
        this.rootBorder.setCenter(root);
        this.setProductTableController(loader.getController());
        //this.productTableController.setListeProduit(this.getListeProduit());
     
        this.productTableController.customInit();

    }
    
    
    
    
    
    
    
}
