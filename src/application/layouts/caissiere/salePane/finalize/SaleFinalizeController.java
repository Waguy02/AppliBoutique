/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane.finalize;

import application.partials.IconedLabel;
import application.partials.inputs.LabelledTextField;
import static application.utilities.Tools.disable;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Client;
import model.Facture;

/**
 * FXML Controller class
 *
 * @author test
 */
public class SaleFinalizeController implements Initializable,CustomController {

    @FXML
    private HBox topBar;
    @FXML
    private VBox contentBOx;
    @FXML
    private HBox actionBox;
    
    
    private Client client;
    private Facture facture;
    @FXML
    private VBox rootVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    public void initFactureInfo(){
        
        
    }
    
    public void initTopBar(){
        LabelledTextField clientField=new LabelledTextField("Client");
        clientField.getTextfield().setText(this.client.toString());
        disable(clientField.getTextfield());
        
        
        clientField.minHeightProperty().bind(this.topBar.heightProperty());
        clientField.minWidthProperty().bind(this.topBar.widthProperty());
        this.topBar.getChildren().add(clientField);
        
    }
    
    public void initMoneyTab(){
         LabelledTextField toPay=new LabelledTextField("MontantTotal");
         toPay.textProperty().set(String.valueOf(this.facture.getMontant()));
         disable(toPay);
         Separator sep1=new Separator(Orientation.VERTICAL);
         
         
         
         LabelledTextField paid=new LabelledTextField("Montant Restant");
         InputBindings.
         
         
         
         
         
         
         
         
         
         Labelled
         
        
        
    }


    public void initActionBox(){
        
        
            JFXButton previewButton=new JFXButton();
            previewButton.setGraphic(IconedLabel.plot("Aperçu","preview.png", true));
            this.actionBox.getChildren().add(previewButton);
            
            
            
            
            
            
            JFXButton confirmButton=new JFXButton();
            confirmButton.setGraphic(IconedLabel.plot("Finalise","standardConfirm.png",true));
           
            
            
            
            
            
            JFXButton discardButton=new JFXButton();
            discardButton.setGraphic(IconedLabel.plot("Annuler","discard.p", true));
            
            
            
            
            
            
            
            
            
            Separator separator1,separator2;
            
            separator1=new Separator(Orientation.VERTICAL);
            separator2=new Separator(Orientation.VERTICAL);
            
            separator1.minWidthProperty().bind(this.rootVBox.widthProperty().multiply(0.2));
            separator2.minWidthProperty().bind(this.rootVBox.widthProperty().multiply(0.2));
            
            
            
            
            
            this.actionBox.getChildren().addAll(previewButton,separator1,confirmButton,separator2,discardButton);
            
            
            
            
}

    @Override
    public void customInit() {
        this.topBar.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.2));
        this.contentBOx.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.6));
        this.actionBox.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.2));
    }


}
