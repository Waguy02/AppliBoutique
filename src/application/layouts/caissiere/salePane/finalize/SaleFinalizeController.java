/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane.finalize;

import application.partials.IconedLabel;
import application.partials.inputs.LabelledTextField;
import static application.utilities.Tools.disable;
import application.utilities.inputs.InputBindings;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
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

    public HBox getTopBar() {
        return topBar;
    }

    public void setTopBar(HBox topBar) {
        this.topBar = topBar;
    }

    public VBox getContentBOx() {
        return contentBOx;
    }

    public void setContentBOx(VBox contentBOx) {
        this.contentBOx = contentBOx;
    }

    public HBox getActionBox() {
        return actionBox;
    }

    public void setActionBox(HBox actionBox) {
        this.actionBox = actionBox;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public VBox getRootVBox() {
        return rootVBox;
    }

    public void setRootVBox(VBox rootVBox) {
        this.rootVBox = rootVBox;
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
     initSizes();
     initTopBar();
     initMoneyTab();
     initActionBox();
    }
    
   public void initSizes(){
       this.topBar.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.2));
       this.topBar.maxWidthProperty().bind(this.rootVBox.widthProperty());
       this.topBar.minWidthProperty().bind(this.rootVBox.widthProperty());
       
       
       
       this.contentBOx.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.6));
       this.contentBOx.minWidthProperty().bind(this.rootVBox.widthProperty()); 
       this.contentBOx.maxWidthProperty().bind(this.rootVBox.widthProperty()); 
       
       this.actionBox.minHeightProperty().bind(this.rootVBox.heightProperty().multiply(0.2));
        this.actionBox.minWidthProperty().bind(this.rootVBox.widthProperty());
        this.actionBox.maxWidthProperty().bind(this.rootVBox.widthProperty());
        
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
         DoubleProperty toPayValue=new SimpleDoubleProperty();
         Bindings.bindBidirectional( toPay.textProperty(),toPayValue,new NumberStringConverter());
         Separator sep1=new Separator(Orientation.VERTICAL);
         this.contentBOx.getChildren().add(toPay);
         this.contentBOx.getChildren().add(sep1);
         
         
         
         LabelledTextField paid=new LabelledTextField("Montant Versé");
         InputBindings.bindQteInput(paid.getTextfield(), facture,"montant");
         DoubleProperty paidValue=new SimpleDoubleProperty();
         Bindings.bindBidirectional( paid.textProperty(),paidValue,new NumberStringConverter());
         Separator sep2=new Separator(Orientation.VERTICAL);
         this.contentBOx.getChildren().add(paid);
         this.contentBOx.getChildren().add(sep2);
         
         
         
         
         
         
         
         LabelledTextField remind=new LabelledTextField("Montant Restant");
         DoubleProperty remindValue=new SimpleDoubleProperty();
         remindValue.bind(toPayValue.add(paidValue.negate()));
         remind.textProperty().bind(remindValue.asString());
         disable(remind);
         Separator sep3=new Separator(Orientation.VERTICAL);
         
         this.contentBOx.getChildren().add(remind);
         this.contentBOx.getChildren().add(sep3);
         
         
         
        
    }


    public void initActionBox(){
        
        
            JFXButton previewButton=new JFXButton();
            previewButton.setGraphic(IconedLabel.plot("Aperçu de la facture","preview.png", true));
            
            JFXButton confirmButton=new JFXButton();
            confirmButton.setGraphic(IconedLabel.plot("Finaliser","standardConfirm.png",true));
           
            JFXButton discardButton=new JFXButton();
            discardButton.setGraphic(IconedLabel.plot("Annuler","discard.png", true));
            
            
            
            Separator separator1,separator2;
            
            separator1=new Separator(Orientation.VERTICAL);
            separator2=new Separator(Orientation.VERTICAL);
            
            separator1.minWidthProperty().bind(this.rootVBox.widthProperty().multiply(0.2));
            separator2.minWidthProperty().bind(this.rootVBox.widthProperty().multiply(0.2));
            
            
            this.actionBox.getChildren().addAll(previewButton,separator1,confirmButton,separator2,discardButton);
            
            
}



    
    
}
