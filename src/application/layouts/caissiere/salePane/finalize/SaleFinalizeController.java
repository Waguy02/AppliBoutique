/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane.finalize;

import static application.layouts.admin.mainAdminPane.MainAdminPaneController.CURRENT_DEVISE;
import application.layouts.caissiere.salePane.SalePaneController;
import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledMoneyField;
import application.partials.inputs.LabelledTextField;
import static application.utilities.AlertsManager.showConfirmation;
import static application.utilities.Tools.disable;
import static application.utilities.ViewDimensionner.bindSizes;
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
import lombok.Getter;
import lombok.Setter;
import model.Client;
import model.Facture;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter @Setter 
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
    @FXML
    private HBox titleBar;
    
    private SalePaneController saleController;
    

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
     initTitleBar();
     initTopBar();
     initMoneyTab();
     initActionBox();
    }
    
   public void initSizes(){
       bindSizes(this.titleBar,this.rootVBox,1,0.1);
       bindSizes(this.topBar,this.rootVBox,1,0.1);
       bindSizes(this.contentBOx,this.rootVBox,1,0.6);
       bindSizes(this.actionBox,this.rootVBox,1,0.1);  }
    
    public void initTitleBar(){
        
        
        IconedLabel label=new IconedLabel("Finalisation de vente","handshake.png");
        label.setImgX(80.0);
        label.setImgY(80.0);
        this.titleBar.getChildren().add(label.plot(true));
        this.rootVBox.getChildren().add(1,Separators.maxSeparatorH());
        
    }
    public void initTopBar(){
        LabelledTextField clientField=new LabelledTextField("Client");
        clientField.getTextfield().setText(this.client.toString());
        disable(clientField.getTextfield());
        
        bindSizes(clientField,this.topBar,1,0.8);
        this.topBar.getChildren().addAll(clientField,Separators.maxSeparatorH());
        
    }
    
             private DoubleProperty toPayValue=new SimpleDoubleProperty();
             DoubleProperty paidValue=new SimpleDoubleProperty();
    public void initMoneyTab(){
         LabelledMoneyField toPay=new LabelledMoneyField(CURRENT_DEVISE,"Montant Total");
         Float sumPrice=0.0f;
         for(Produit p:this.saleController.getPreviewItems()){
             sumPrice+=p.getQuantite()*p.getPrixUnitaire();
         }
         System.out.println("Somme : "+sumPrice);
         facture=new Facture();
         facture.setMontant(sumPrice);
         
         disable(toPay);
         toPayValue.set(sumPrice);

         Bindings.bindBidirectional( toPay.textProperty(),toPayValue,new NumberStringConverter());
         Separator sep1=new Separator(Orientation.VERTICAL);
         bindSizes(toPay,this.contentBOx,1,0.2);         
         this.contentBOx.getChildren().addAll(toPay,Separators.maxSeparatorH());
         
         
         
         
         LabelledMoneyField paid=new LabelledMoneyField(CURRENT_DEVISE,"Montant Versé");
         InputBindings.bindQteInput(paid.getTextfield(),facture,"montant");
         paidValue=new SimpleDoubleProperty();
         Bindings.bindBidirectional( paid.textProperty(),paidValue,new NumberStringConverter());
         bindSizes(paid,this.contentBOx,1,0.2);
         
         
         
         this.contentBOx.getChildren().addAll(paid,Separators.maxSeparatorH());
         
         
         
         
         
         
         
         
         LabelledMoneyField remind=new LabelledMoneyField(CURRENT_DEVISE,"Montant Restant");
         DoubleProperty remindValue=new SimpleDoubleProperty();
         remindValue.bind(toPayValue.add(paidValue.negate()));
         remind.textProperty().bind(remindValue.asString());
         disable(remind.getTextfield());
         
         bindSizes(remind,this.contentBOx,1,0.2);
         
         
         this.contentBOx.getChildren().addAll(remind,Separators.maxSeparatorH());
         
         
         
         
        
    }
    


    public void initActionBox(){
        
            
            /*JFXButton previewButton=new JFXButton();
            previewButton.getStyleClass().add("previewButton");
            previewButton.setGraphic(IconedLabel.plot("Aperçu de la facture","preview.png", true));
            */
            
            
            JFXButton confirmButton=new JFXButton();
            confirmButton.setGraphic(IconedLabel.plot("Finaliser","standardConfirm.png",true,50,50));
            confirmButton.getStyleClass().add("previewButton");
            confirmButton.setOnAction(event->
            {
                if(showConfirmation("Voulez-vous vraiment finaliser cette vente?")){
                    this.saleController.validateHandler(event);
                }
                
                
            }
            );
            
            
            
            
            
            
            
           
            JFXButton discardButton=new JFXButton();
            discardButton.setGraphic(IconedLabel.plot("Retour","discard.png", true ,50,50));
            discardButton.getStyleClass().add("previewButton");
            discardButton.setOnAction(event->{
                
                
                
                if(showConfirmation("Voulez-vous modifier les informations de cete vente?")){
                    
                    this.saleController.getIsFinalizing().set(false);
                
                }
                
                
            });
            
            
            
            this.actionBox.getChildren().addAll(Separators.maxSeparatorV(),confirmButton,Separators.maxSeparatorV(),discardButton,Separators.maxSeparatorV());
            
            
}



    
    
}
