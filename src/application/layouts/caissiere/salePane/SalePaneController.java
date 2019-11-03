/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane;

import application.layouts.caissiere.mainTabPane.MainTabPaneController;
import application.partials.AutoCompleteCombo;
import application.partials.LabelledAutoCombo;
import application.partials.LabelledTextField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class SalePaneController implements Initializable {

    
    private MainTabPaneController mainController;

    public MainTabPaneController getMainController() {
        return mainController;
    }

    public void setMainController(MainTabPaneController mainController) {
        this.mainController = mainController;
    }

    public AnchorPane getAddingAnchor() {
        return addingAnchor;
    }

    public void setAddingAnchor(AnchorPane addingAnchor) {
        this.addingAnchor = addingAnchor;
    }

    public HBox getProductAddBOx() {
        return productAddBOx;
    }

    public void setProductAddBOx(HBox productAddBOx) {
        this.productAddBOx = productAddBOx;
    }

    public HBox getProductNameSearcBox() {
        return productNameSearcBox;
    }

    public void setProductNameSearcBox(HBox productNameSearcBox) {
        this.productNameSearcBox = productNameSearcBox;
    }

    public AnchorPane getPreviewAnchor() {
        return previewAnchor;
    }

    public void setPreviewAnchor(AnchorPane previewAnchor) {
        this.previewAnchor = previewAnchor;
    }

    public AnchorPane getValidationAnchor() {
        return validationAnchor;
    }

    public void setValidationAnchor(AnchorPane validationAnchor) {
        this.validationAnchor = validationAnchor;
    }
    @FXML
    private AnchorPane addingAnchor;
    @FXML
    private HBox productAddBOx;
    private HBox productNameSearcBox;
    @FXML
    private AnchorPane previewAnchor;
    @FXML
    private AnchorPane validationAnchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void customInit(){
        this.initAddProductBar();
    }
    
    
    
    public void initAddProductBar(){
    
        ComboBox<Produit> prodCombo= new AutoCompleteCombo("nom",this.mainController.getListeProduit());
   
    
    JFXTextField qteField=new JFXTextField();
    //qteField.getStyleClass().add("defaultTextField");
    
    qteField.textProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if(newValue.isEmpty()||newValue==null) qteField.setText("0");
            
            else{
                
                try{
                    Integer val=Integer.valueOf(qteField.getText());
                    if(val<=0){qteField.setText("0");return;}
                    Integer max=prodCombo.getValue().getQuantite();
                    if(val>=max){qteField.setText(String.valueOf(max));
                    return;
                    }
                
                }
             catch(Exception e){
                 e.printStackTrace();;
                 qteField.setText("0");
             
             }
             
            }
                
                
                
            }
        
        
    });
    
    prodCombo.valueProperty().addListener(new ChangeListener<Produit>(){
            @Override
            public void changed(ObservableValue<? extends Produit> observable, Produit oldValue, Produit newValue) {
              qteField.setText("0");
            }
    
    
    });
        
        
        
         this.productAddBOx.getChildren().add(new LabelledAutoCombo("Produit ",prodCombo));
         
         this.productAddBOx.getChildren().add(new LabelledTextField("Quantité",qteField));
        
        
        
        
        
        
    }
    
    
    
        
    
}

