/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.stockTab.commandBox;

import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledAutoCombo;
import application.partials.inputs.LabelledCheckBox;
import application.partials.inputs.LabelledTextField;
import application.utilities.interfaces.CustomController;
import application.utilities.interfaces.ViewDimensionner;
import static application.utilities.interfaces.ViewDimensionner.bindSizes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Administrateur;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class CommandBoxController implements Initializable,CustomController {
    
    
    private Administrateur administrateur;
    
    private LabelledAutoCombo fournisseurBox;
    
    private HBox productBox;
        private LabelledAutoCombo productListBox;
        private LabelledCheckBox  checkOtherProductBox;
        
        
        
    private VBox newProductBox;
        private LabelledTextField newProductNameBox;
        private LabelledAutoCombo newProductCategorie;
        private LabelledTextField newProduitDescription;
    private HBox productPricesBox;
        private HBox productSalePrice;
        private HBox prodcutBuyPrice;
   
   
     
   private HBox productQuantityBox;
   
   private HBox addingToPreviewBox;
        private JFXButton addingToPreviewButton;
        private LabelledTextField previewAmount;
   
   private TableView<Produit> previewTable;
   
   
   
   
   private HBox validaTationBox;
   
    private JFXButton finalizeButton;
    private JFXButton discardButton;
    private JFXButton  previewButton;
    
    
    
    @FXML
    private VBox rootVBox;

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

    public HBox getFournisseurBox() {
        return fournisseurBox;
    }

    public void setFournisseurBox(LabelledAutoCombo fournisseurBox) {
        this.fournisseurBox = fournisseurBox;
    }

    public HBox getProductBox() {
        return productBox;
    }

    public void setProductBox(HBox productBox) {
        this.productBox = productBox;
    }

    public LabelledAutoCombo getProductListBox() {
        return productListBox;
    }

    public void setProductListBox(LabelledAutoCombo productListBox) {
        this.productListBox = productListBox;
    }

    public LabelledCheckBox getCheckOtherProductBox() {
        return checkOtherProductBox;
    }

    public void setCheckOtherProductBox(LabelledCheckBox checkOtherProductBox) {
        this.checkOtherProductBox = checkOtherProductBox;
    }

    public VBox getNewProductBox() {
        return newProductBox;
    }

    public void setNewProductBox(VBox newProductBox) {
        this.newProductBox = newProductBox;
    }

    public LabelledTextField getNewProductNameBox() {
        return newProductNameBox;
    }

    public void setNewProductNameBox(LabelledTextField newProductNameBox) {
        this.newProductNameBox = newProductNameBox;
    }

    public LabelledAutoCombo getNewProductCategorie() {
        return newProductCategorie;
    }

    public void setNewProductCategorie(LabelledAutoCombo newProductCategorie) {
        this.newProductCategorie = newProductCategorie;
    }

    public HBox getProductPricesBox() {
        return productPricesBox;
    }

    public void setProductPricesBox(HBox productPricesBox) {
        this.productPricesBox = productPricesBox;
    }

    public HBox getProductSalePrice() {
        return productSalePrice;
    }

    public void setProductSalePrice(HBox productSalePrice) {
        this.productSalePrice = productSalePrice;
    }

    public HBox getProdcutBuyPrice() {
        return prodcutBuyPrice;
    }

    public void setProdcutBuyPrice(HBox prodcutBuyPrice) {
        this.prodcutBuyPrice = prodcutBuyPrice;
    }

    public HBox getProductQuantityBox() {
        return productQuantityBox;
    }

    public void setProductQuantityBox(HBox productQuantityBox) {
        this.productQuantityBox = productQuantityBox;
    }

    public HBox getAddingToPreviewBox() {
        return addingToPreviewBox;
    }

    public void setAddingToPreviewBox(HBox addingToPreviewBox) {
        this.addingToPreviewBox = addingToPreviewBox;
    }

    public JFXButton getAddingToPreviewButton() {
        return addingToPreviewButton;
    }

    public void setAddingToPreviewButton(JFXButton addingToPreviewButton) {
        this.addingToPreviewButton = addingToPreviewButton;
    }

    public LabelledTextField getPreviewAmount() {
        return previewAmount;
    }

    public void setPreviewAmount(LabelledTextField previewAmount) {
        this.previewAmount = previewAmount;
    }

    public TableView<Produit> getPreviewTable() {
        return previewTable;
    }

    public void setPreviewTable(TableView<Produit> previewTable) {
        this.previewTable = previewTable;
    }

    public HBox getValidaTationBox() {
        return validaTationBox;
    }

    public void setValidaTationBox(HBox validaTationBox) {
        this.validaTationBox = validaTationBox;
    }

    public JFXButton getFinalizeButton() {
        return finalizeButton;
    }

    public void setFinalizeButton(JFXButton finalizeButton) {
        this.finalizeButton = finalizeButton;
    }

    public JFXButton getDiscardButton() {
        return discardButton;
    }

    public void setDiscardButton(JFXButton discardButton) {
        this.discardButton = discardButton;
    }

    public JFXButton getPreviewButton() {
        return previewButton;
    }

    public void setPreviewButton(JFXButton previewButton) {
        this.previewButton = previewButton;
    }

    public VBox getrootVBox() {
        return rootVBox;
    }

    public void setrootVBox(VBox rootVBox) {
        this.rootVBox = rootVBox;
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void customInit() {
      
    }
    
    
    
    
    
    
    
    
    
    public void initFournisseurBox(){
        JFXComboBox fournisseurCombo=new JFXComboBox(this.administrateur.listeFournisseur());
        fournisseurBox=new LabelledAutoCombo("",fournisseurCombo);
        fournisseurBox.getLabel().setGraphic(IconedLabel.plot("Fournisseur", "provider.png", true));
        ViewDimensionner.bindSizes(fournisseurBox,this.rootVBox, 1,0.1);
       rootVBox.getChildren().add(fournisseurCombo);
       rootVBox.getChildren().add(Separators.formSeparatorV());
        
        
        
    }
    
    
    
    public void initProductBox(){
        
        this.productBox=new HBox();
        ViewDimensionner.bindSizes(productBox, rootVBox, 1, 0.1);
        
       JFXComboBox productCombo =new JFXComboBox(this.administrateur.listeProduits());
       productListBox=new LabelledAutoCombo("",productCombo);
       productListBox.getLabel().setGraphic(IconedLabel.plot("Produit", "product.png", true));
       bindSizes(productListBox,productBox,1,0.5);
       productBox.getChildren().add(productListBox);       
       
       productBox.getChildren().add(Separators.formSeparatorH());
       
      checkOtherProductBox=new LabelledCheckBox("Autre");
      bindSizes(checkOtherProductBox,productBox,1,0.3);
      
      productBox.getChildren().add(checkOtherProductBox);
       
      productBox.disableProperty().bindBidirectional(checkOtherProductBox.getSelectedProperty());
      
      
      this.rootVBox.getChildren().addAll(productBox,Separators.formSeparatorH());
       
    }
    
    
    
    
    public void initNewProductBox(){
        
            this.newProductBox=new VBox();
            bindSizes(newProductBox,rootVBox,0.9,0.3);
            
            
            
             HBox nameAndCatBox=new HBox();
             newProductBox.getChildren().add(nameAndCatBox);
             
             bindSizes(nameAndCatBox,newProductBox,1,0.3);
             
             
             
             newProductNameBox=new LabelledTextField("Nom du produit");
            bindSizes(newProductNameBox,nameAndCatBox,0.4,0.1);
             
             newProductCategorie=new LabelledAutoCombo("Categore",new JFXComboBox(administrateur.listeCategorie()));
            bindSizes(newProductCategorie,nameAndCatBox,0.4,0.1); 
             
            nameAndCatBox.getChildren().addAll(newProductNameBox,Separators.formSeparatorH(),newProductCategorie);
             
             
             
             newProductBox.getChildren().add(nameAndCatBox);
            
            

            
    
    
        }
    
    
}
