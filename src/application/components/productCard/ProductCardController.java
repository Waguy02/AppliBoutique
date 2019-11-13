/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.components.productCard;

import static application.layouts.admin.mainAdminPane.MainAdminPaneController.CURRENT_DEVISE;
import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.AutoCompleteCombo;
import application.partials.inputs.LabelledCombo;
import application.partials.inputs.LabelledMoneyField;
import application.partials.inputs.LabelledTextArea;
import application.partials.inputs.LabelledTextField;
import static application.utilities.AlertsManager.showConfirmation;
import static application.utilities.Tools.hide;
import static application.utilities.Tools.quickAlert;
import static application.utilities.Tools.show;
import application.utilities.ViewDimensionner;
import static application.utilities.ViewDimensionner.bindSizes;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;
import model.Categorie;
import model.Employe;
import model.LigneCommande;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter
@Setter
public class ProductCardController implements Initializable, CustomController {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private Administrateur admin;
    static double labelPercentage = 0.3, inputPercentage = 0.65, halfPercentage = 0.4, halfSepPercentage = 0.2, inputHeight = 0.05;
    private BooleanProperty readingMode = new SimpleBooleanProperty(false),
            editingMode = new SimpleBooleanProperty(false),
            registeringMode = new SimpleBooleanProperty(false);
    private Produit product;
    private Employe employee;

    private HBox topBox = new HBox();
    private VBox contentBox = new VBox();
    private HBox actionBox = new HBox();

    private ObservableList<Produit> productList;
    JFXComboBox produitCombo = new JFXComboBox<>();
    Produit currentProduit;

    private LabelledCombo productListBox;

    private VBox ProductBox;
    private LabelledTextField ProductNameBox;
    private LabelledCombo ProductCategorie;
    private LabelledTextArea ProductDescription;
    private HBox productPricesBox;
    private LabelledMoneyField productSalePrice;
    private LabelledMoneyField productBuyPrice;

    private HBox productAmountsBox;
    private LabelledTextField productQuantityBox;
    private LabelledMoneyField productTotalPriceBox;

    private HBox LabelledTextField;

    private HBox addingToPreviewBox;
    private JFXButton addingToPreviewButton;
    private LabelledMoneyField previewAmount;

    private TableView<LigneCommande> previewTable;

    private JFXButton finalizeButton;
    private JFXButton discardButton;
    private JFXButton editButton;

    @FXML
    private VBox rootVBox;

    public void initComponentsSize() {
        ViewDimensionner.bindSizes(this.topBox, this.rootVBox, 1, 0.1);
        ViewDimensionner.bindSizes(this.contentBox, this.rootVBox, 1, 0.6);
        ViewDimensionner.bindSizes(this.actionBox, this.rootVBox, 1, 0.1);

    }

    public void initTop() {

        IconedLabel ic = new IconedLabel("Informations sur le produit", "product.png");
        ic.setImgX(80.0);
        ic.setImgY(80.0);
        HBox title = ic.plot(false);
        title.getStyleClass().add("titleBar");
        bindSizes(title, this.topBox, 0.4, 1);

        produitCombo.setItems(productList);
        LabelledCombo lbProd = new LabelledCombo("Produit", produitCombo);
        bindSizes(lbProd, this.topBox, 0.3, 0.5);

        
        
        produitCombo.valueProperty().addListener((ob, old, newV) -> {

            if (newV == null) {
                currentProduit = null;
                this.ProductNameBox.getTextfield().clear();
                this.ProductCategorie.getCombo().getSelectionModel().clearSelection();
                this.ProductDescription.getTextArea().clear();

                this.productSalePrice.getTextfield().clear();
                this.productQuantityBox.getTextfield().clear();

            } else {
                this.isEditing.set(false);
                
                currentProduit = (Produit) newV;

                this.ProductNameBox.getTextfield().setText(this.currentProduit.getNom());
                this.ProductCategorie.getCombo().setValue(this.currentProduit.getCategorie());
                this.ProductDescription.getTextArea().setText(this.currentProduit.getDescription());

                this.productSalePrice.getTextfield().setText(String.valueOf(this.currentProduit.getPrixUnitaire()));
                this.productQuantityBox.getTextfield().setText(String.valueOf(this.currentProduit.getQuantite()));

            }

        });
        
     
        this.editButton=new JFXButton();
        editButton.setGraphic(IconedLabel.plot("Modifier", "preview.png", false));
                editButton.getStyleClass().add("editingButton");
                
        bindSizes(this.editButton,this.topBox,0.2,0.5);


        editButton.setOnAction(event
                -> {
            if (isEditing.get()) {

                Boolean invalid = this.ProductNameBox.getTextfield().getText().isEmpty()
                        || this.ProductCategorie.getCombo().getValue() == null
                        || this.productSalePrice.getTextfield().getText().isEmpty();

                if (invalid) {
                    quickAlert(AlertType.ERROR, "Données incorrectes", "Veuillez remplir correctement les champs");
                } else {

                    if (showConfirmation("Voulez-vous vraiment modifier les informations de ce Produit")) {

                        try {
                            this.updateModification();
                        } catch (Exception e) {
                            {
                                this.isEditing.set(true);

                            }
                            e.printStackTrace();
                            quickAlert(AlertType.ERROR, "Données incorrectes", "Erreur survenue lors de l'enregistrement des informations");

                        }
                    }

                }
                isEditing.set(false);

            } else {
             System.out.println("Mode edition apres le click");
                this.isEditing.set(true);

            }

            // On désactive le button en mode lecture;    
        }
        );

        this.topBox.getChildren().addAll(title, Separators.maxSeparatorV(), lbProd, Separators.maxSeparatorV(), this.editButton, Separators.maxSeparatorV());
        this.rootVBox.getChildren().addAll(Separators.maxSeparatorH(), this.topBox, Separators.maxSeparatorH());

        
        
        
        
        
        
        this.isEditing.addListener((observable,old,newV)-> {
              System.out.println("CHANGEMENT DE MODE");
            if (newV) {
                editButton.setGraphic(null);
                editButton.setGraphic(IconedLabel.plot("Enregistrer", "preview.png", false));
                editButton.getStyleClass().add("addingButton");
            
                show(this.discardButton);
                
               
            } else {
                
                editButton.setGraphic(null);
                editButton.setGraphic(IconedLabel.plot("Modifier", "standardConfirm.png", false));
                editButton.getStyleClass().add("editingButton");
                hide(this.discardButton);
                
            }
        });
        
        
        
        
        
        
        
    }

    public void updateModification() {
        this.currentProduit.setCategorie((Categorie) this.ProductCategorie.getCombo().getValue());
        this.currentProduit.setNom(this.ProductNameBox.getTextfield().getText());
        this.currentProduit.setDescription(this.ProductDescription.getTextArea().getText());
        this.currentProduit.setPrixUnitaire(Float.valueOf(this.productSalePrice.getTextfield().getText()));
        this.currentProduit.setQuantite(Integer.valueOf(this.productQuantityBox.getTextfield().getText()));

        this.admin.modifierProduit(currentProduit);
    }

    public void initContent() {
        this.rootVBox.getChildren().addAll(this.contentBox, Separators.maxSeparatorH());
        this.initProductNameAndDescription();
        this.initProductPriceAndQte();
    }

    public void initProductNameAndDescription() {

        this.ProductBox = new VBox();
        bindSizes(ProductBox, rootVBox, 1, 5 * inputHeight);

        HBox nameAndCatBox = new HBox();

        bindSizes(nameAndCatBox, ProductBox, 1, 0.3);

        ProductNameBox = new LabelledTextField("Nom du produit");
        bindSizes(ProductNameBox, nameAndCatBox, halfPercentage, 1);

        ProductCategorie = new LabelledCombo("Categorie", new AutoCompleteCombo<Categorie>(FXCollections.observableArrayList()), labelPercentage, inputPercentage);
        bindSizes(ProductCategorie, nameAndCatBox, halfPercentage, 1);

        nameAndCatBox.getChildren().addAll(ProductNameBox, Separators.maxSeparatorV(), ProductCategorie, Separators.maxSeparatorV());
        ProductBox.getChildren().add(nameAndCatBox);
        ProductBox.getChildren().add(Separators.maxSeparatorH());

        this.ProductDescription = new LabelledTextArea("Description du produit");
        bindSizes(ProductDescription, ProductBox, 1, 0.6);
        this.ProductBox.getChildren().add(ProductDescription);

        this.contentBox.getChildren().addAll(ProductBox, Separators.maxSeparatorH());

        this.ProductDescription.getTextArea().disableProperty().bind(this.isEditing.not());
        this.ProductCategorie.getCombo().disableProperty().bind(this.isEditing.not());
        this.ProductNameBox.getTextfield().disableProperty().bind(this.isEditing.not());

    }

    public void initProductPriceAndQte() {
        this.productPricesBox = new HBox();
        bindSizes(productPricesBox, rootVBox, 1, inputHeight);
        productSalePrice = new LabelledMoneyField(CURRENT_DEVISE, "Prix de vente", labelPercentage, inputPercentage);

        bindSizes(productSalePrice, productPricesBox, halfPercentage, 1);

        this.productQuantityBox = new LabelledTextField("Quantité en stock", 2 * labelPercentage, inputPercentage / 2);
        
        
        bindSizes(productQuantityBox, productPricesBox, halfPercentage, 1);
        
        
        
        
        productPricesBox.getChildren().addAll(productSalePrice, Separators.maxSeparatorV(), this.productQuantityBox);

        this.contentBox.getChildren().addAll(productPricesBox, Separators.maxSeparatorH());

        productSalePrice.getTextfield().disableProperty().bind(this.isEditing.not());
        productQuantityBox.getTextfield().setEditable(false);

    }

    @Override
    public void customInit() {
        this.initComponentsSize();
        this.initTop();
        this.initContent();
        this.initActionBox();
        this.initProduct();
    }

    static double iconPrefSize = 60.0;

    public void initActionBox() {
        this.actionBox = new HBox();

        bindSizes(this.actionBox, this.rootVBox, 1, 0.1);

        this.discardButton = new JFXButton("");
        this.discardButton.getStyleClass().add("discardButton");
        discardButton.setGraphic(IconedLabel.plot("Annuler", "standardConfirm.png", false, iconPrefSize, iconPrefSize));
        bindSizes(discardButton, this.actionBox, 0.2, 0.7);

        
        this.discardButton.setOnAction(event->
        
        {
            
            if(showConfirmation("Voulez-vous vraiment annuler les modifications en cours sur le produit ?"))this.isEditing.set(false);
            
        });
        
        
        
        
        
        
        
        this.finalizeButton = new JFXButton("");
        this.finalizeButton.getStyleClass().add("discardButton");
        finalizeButton.setGraphic(IconedLabel.plot("retour", "standardConfirm.png", false, iconPrefSize, iconPrefSize));
        finalizeButton.setOnAction(event->{
            if(this.isEditing.get()){
                 if(!showConfirmation("Voulez-vous vraiment annuler les modifications en cours sur le produit ?"))return;
            }
           this.isOpen.set(false);
        });
        
        bindSizes(discardButton, this.actionBox, 0.2, 0.7);
        hide(discardButton);
        this.actionBox.getChildren().addAll(Separators.maxSeparatorV(), discardButton, Separators.maxSeparatorV(), finalizeButton,Separators.maxSeparatorV());

        this.rootVBox.getChildren().add(actionBox);
    }

    private BooleanProperty isEditing = new SimpleBooleanProperty();
private BooleanProperty isOpen=new SimpleBooleanProperty(true);
    
    
    public void initMode() {
        if (!this.registeringMode.get() && !this.editingMode.get()) {
            hide(this.editButton);
        }
    }
    
    public void initProduct(){
        
        if(this.currentProduit!=null){
            this.produitCombo.setValue(currentProduit);
        }
    }
            

            }
