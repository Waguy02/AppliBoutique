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
import application.utilities.ViewDimensionner;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.Administrateur;

/**
 * FXML Controller class
 *
 * @author test
 */
public class StockTabController implements Initializable,CustomController {


    
    private Administrateur admin;
    @FXML
    private AnchorPane rootAnchor;

    public Administrateur getAdmin() {
        return admin;
    }

    public void setAdmin(Administrateur admin) {
        this.admin = admin;
    }
    
    
    
    
    
    private ProductTableController productTableController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    public ProductTableController getProductTableController() {
        return productTableController;
    }

    public void setProductTableController(ProductTableController productTableController) {
        this.productTableController = productTableController;
    }

    public AnchorPane getRootAnchor() {
        return rootAnchor;
    }

    public void setRootAnchor(AnchorPane rootAnchor) {
        this.rootAnchor = rootAnchor;
    }
    
    
    
    
    


  @Override
    public void customInit() {
        this.initProductTab();
    }


    
    
    
        public void initProductTab() {
        FXMLLoader loader = getLoader("layouts/caissiere/productTable/productTable");
        AnchorPane root = (AnchorPane) getView(loader);
        
        
        this.rootAnchor.getChildren().add(root);
        
        ViewDimensionner.bindSizes(root,rootAnchor,1,1);
        this.setProductTableController(loader.getController());
        
        this.productTableController.setAdmin(this.admin);
        this.productTableController.setListeProduit(this.admin.listeProduits());
       
        this.productTableController.getManagingMode().set(true);
        
        this.productTableController.customInit();
        

    } 

        
        
        
        
        
        
        
        
        public void onSelectionHandle(){
            
            this.productTableController.getListeProduit().setAll(admin.listeProduits());
            
        }
    
    
    
    
    
    
}
