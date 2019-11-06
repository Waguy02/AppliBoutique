/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane;

import application.layouts.caissiere.mainTabPane.MainTabPaneController;
import application.layouts.caissiere.salePane.preview.SalePreviewTableController;
import application.partials.AutoCompleteCombo;
import application.partials.IconedLabel;
import application.partials.inputs.LabelledAutoCombo;
import application.partials.inputs.LabelledTextField;
import application.utilities.InputBindings;
import static application.utilities.Tools.disable;
import static application.utilities.Tools.quickAlert;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class SalePaneController implements Initializable,CustomController {

    
    private MainTabPaneController mainController;
    private SalePreviewTableController salePreviewController;
    
    
    

    public SalePreviewTableController getSalePreviewController() {
        return salePreviewController;
    }

    public void setSalePreviewController(SalePreviewTableController salePreviewController) {
        this.salePreviewController = salePreviewController;
    }

    public ObservableList<Produit> getPreviewItems() {
        return previewItems;
    }

    public void setPreviewItems(ObservableList<Produit> previewItems) {
        this.previewItems = previewItems;
    }

    public ComboBox<Produit> getProdCombo() {
        return prodCombo;
    }

    public void setProdCombo(ComboBox<Produit> prodCombo) {
        this.prodCombo = prodCombo;
    }

    public JFXButton getAddItemButton() {
        return addItemButton;
    }

    public void setAddItemButton(JFXButton addItemButton) {
        this.addItemButton = addItemButton;
    }

    public JFXTextField getQteField() {
        return qteField;
    }

    public void setQteField(JFXTextField qteField) {
        this.qteField = qteField;
    }
    
    
    
    
    
    
    
    
    
    
    
    private ObservableList<Produit> previewItems=FXCollections.observableArrayList();
    @FXML
    

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

    public HBox getProductAddBox() {
        return productAddBox;
    }

    public void setProductAddBox(HBox productAddBox) {
        this.productAddBox = productAddBox;
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
    private HBox productAddBox;
    
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
        this.initPreviewTable();
    }
    
    ComboBox<Produit> prodCombo;
    JFXButton addItemButton;
    JFXTextField qteField ;
    
    public void initAddProductBar(){
    
        prodCombo= new AutoCompleteCombo("nom",this.mainController.getListeAvailabeProduit());
        this.productAddBox.getChildren().add(new LabelledAutoCombo("Produit ",prodCombo));
         this.mainController.getListeAvailabeProduit().addListener(new ListChangeListener<Produit>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produit> c) {
            clearProductBar();
            }


        });
        
        
        
        
        qteField =new JFXTextField();
         this.productAddBox.getChildren().add(new LabelledTextField("Quantité",qteField));
        
         InputBindings.bindQteInput(qteField, prodCombo, "quantite", Produit.class);
        
        
        addItemButton=new JFXButton("",IconedLabel.plot("Ajouter","add_64px.png",true));
        this.productAddBox.getChildren().add(addItemButton);
        
        this.initAddButton();
    }
    
    public void clearProductBar()
    {
     this.prodCombo.getSelectionModel().clearSelection();
     disable(this.qteField);
        
        
    }
    
    public void initAddButton(){
        this.addItemButton.setOnAction(event->{
            Produit newP=this.prodCombo.getValue().clone();
                        
            Integer qte=Integer.valueOf(this.qteField.getText());
            
            if(qte==0){
                
                quickAlert(Alert.AlertType.ERROR,"Veuillez préciser la quantité"); return ;}
            
            
            
            
            
            
            
            
            newP.setQuantite(qte);
            
            boolean contains=false;
            
            for(Produit p:this.previewItems){
             if(p.getId().equals(newP.getId())){
              contains=true;
              p.setQuantite(p.getQuantite()+newP.getQuantite());
             
              }
            }
            
            if(!contains){
                
             this.previewItems.add(newP);   
            }
           //Mise à jour des produits disponibles :
         
           Produit p0=null;
           for(Produit availableP:this.mainController.getListeAvailabeProduit()){
             
                if(availableP.getId().equals(newP.getId())){
                    p0=availableP;break;
                                }   
            }
           
            p0.setQuantite(p0.getQuantite()-newP.getQuantite());
            this.mainController.getListeAvailabeProduit().remove(p0);
            this.mainController.getListeAvailabeProduit().add(p0);
        
            
    }
            );
        
        }
       
        
    
    
    
    
    public void initPreviewTable(){
        FXMLLoader loader = getLoader("layouts/caissiere/salePane/preview/salePreviewTable");
        AnchorPane root = (AnchorPane) getView(loader);
        this.setSalePreviewController(loader.getController());
        
        this.getSalePreviewController().setBaseController(this);
        root.minWidthProperty().bind(this.previewAnchor.widthProperty());
        root.maxWidthProperty().bind(this.previewAnchor.widthProperty());
        root.minHeightProperty().bind(this.previewAnchor.heightProperty());
        root.maxHeightProperty().bind(this.previewAnchor.heightProperty());
        
        this.previewAnchor.getChildren().add(root);
        
        this.salePreviewController.setPreviewList(previewItems);
        this.salePreviewController.customInit();
        
        //Détection des retraits dans la previewTable
        
        this.salePreviewController.getPreviewList().addListener(new ListChangeListener<Produit>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Produit> c) {
               
               while (c.next()) {
                   
                   for (Produit remitem : c.getRemoved()) {
                       System.out.println("ELEMNT REMOVED : "+remitem.getId());
                        for(Produit p :prodCombo.getItems()){
                       
                               if(p.getId().equals(remitem.getId())){
                       
                                  p.setQuantite(p.getQuantite()+remitem.getQuantite());
                               }
                     }
                     }
                 }
                            }
         
     
            }
     
);
        
        
        
        
        
        
      
    }
    









    

}

