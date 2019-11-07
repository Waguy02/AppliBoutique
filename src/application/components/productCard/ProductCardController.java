/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.components.productCard;

import application.partials.inputs.LabelledAutoCombo;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Employe;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class ProductCardController implements Initializable,CustomController {

    
    
    private Produit product;
    
    private Employe employee;
    @FXML
    private BorderPane rootBorder;
    @FXML
    private AnchorPane topAnchor;
    @FXML
    private HBox topHBOX;
    @FXML
    private VBox rootVBOx;
    
    private ObservableList<Produit> productList;
    
    
    JFXComboBox produitCombo=new JFXComboBox<> ();
    public Produit getProduct() {
        return product;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public Employe getEmployee() {
        return employee;
    }

    public void setEmployee(Employe employee) {
        this.employee = employee;
    }

    public BorderPane getRootBorder() {
        return rootBorder;
    }

    public void setRootBorder(BorderPane rootBorder) {
        this.rootBorder = rootBorder;
    }

    public AnchorPane getTopAnchor() {
        return topAnchor;
    }

    public void setTopAnchor(AnchorPane topAnchor) {
        this.topAnchor = topAnchor;
    }

    public HBox getTopHBOX() {
        return topHBOX;
    }

    public void setTopHBOX(HBox topHBOX) {
        this.topHBOX = topHBOX;
    }

    public VBox getRootVBOx() {
        return rootVBOx;
    }

    public void setRootVBOx(VBox rootVBOx) {
        this.rootVBOx = rootVBOx;
    }

    public ObservableList<Produit> getProductList() {
        return productList;
    }

    public void setProductList(ObservableList<Produit> productList) {
        this.productList = productList;
    }
    
    
    
    
    
  
    
    
    public void initTop(){
            produitCombo.setItems(productList);
            LabelledAutoCombo combo=new LabelledAutoCombo("Produit", produitCombo);
            this.topAnchor.getChildren().add(combo);    
               System.out.println("Header initialisé");
    
    }
    
    
   
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void customInit() {
  
        this.initTop();
    }
    
}
