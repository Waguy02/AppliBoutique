/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.stockTab.commandBox;

import static application.layouts.admin.mainAdminPane.MainAdminPaneController.CURRENT_DEVISE;

import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.AutoCompleteCombo;
import application.partials.inputs.LabelledCombo;
import application.partials.inputs.LabelledCheckBox;
import application.partials.inputs.LabelledMoneyField;
import application.partials.inputs.LabelledTextArea;
import application.partials.inputs.LabelledTextField;
import application.partials.table.CustomActionColumn;
import application.partials.table.CustomActionFactories;
import application.partials.table.CustomSimpleColumn;
import application.utilities.TableViewManager;
import static application.utilities.Tools.disable;
import static application.utilities.Tools.quickAlert;
import application.utilities.interfaces.CustomController;
import application.utilities.ViewDimensionner;
import static application.utilities.ViewDimensionner.bindSizes;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;
import model.Categorie;
import model.Commande;
import model.LigneCommande;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter
@Setter
public class CommandBoxController implements Initializable, CustomController {

    static double iconPrefSize = 40.0;
    private Administrateur administrateur;

    private LabelledCombo fournisseurBox;

    private HBox productBox;
    private LabelledCombo productListBox;
    private LabelledCheckBox checkOtherProductBox;

    private VBox newProductBox;
    private LabelledTextField newProductNameBox;
    private LabelledCombo newProductCategorie;
    private LabelledTextArea newProductDescription;
    private HBox productPricesBox;
    private LabelledMoneyField productSalePrice;
    private LabelledMoneyField productBuyPrice;

    private HBox productAmountsBox;
    private LabelledTextField productQuantityBox;
    private LabelledMoneyField productTotalPriceBox;

    private Produit currentProduit;
    private HBox LabelledTextField;

    private HBox addingToPreviewBox;
    private JFXButton addingToPreviewButton;
    private LabelledMoneyField previewAmount;

    private TableView<LigneCommande> previewTable;

    private HBox validationBox;

    private JFXButton finalizeButton;
    private JFXButton discardButton;
    private JFXButton previewButton;

    DoubleProperty totalPrice = new SimpleDoubleProperty();
    DoubleProperty salePrice = new SimpleDoubleProperty();
    DoubleProperty newQte = new SimpleDoubleProperty();
    ObservableList<Produit> listProduits = FXCollections.observableArrayList();
    ObservableList<LigneCommande> listCommandes = FXCollections.observableArrayList();
        
    @FXML
    private VBox rootVBox;

    

    Commande commande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void customInit() {
        this.initComponents();
        this.setDataValidation();
    }

    static double labelPercentage = 0.3, inputPercentage = 0.65, halfPercentage = 0.4, halfSepPercentage = 0.2, inputHeight = 0.05;

    public void initComponents() {
        initFournisseurBox();
        initProductBox();
        this.initNewProductBox();
        this.initProductPricesBox();
        this.initProductAmountsBox();
        this.initAddingToPreviewBox();
        this.initPreviewTable();
        this.initValidationBox();
    }

    public void clearComponents() {

        this.productListBox.getCombo().getSelectionModel().clearSelection();
        this.productBuyPrice.getTextfield().clear();
        this.productQuantityBox.getTextfield().clear();
        this.checkOtherProductBox.getCheckBox().selectedProperty().set(false);
        this.productListBox.getCombo().getSelectionModel().clearSelection();
    }

    public void initFournisseurBox() {
        HBox FBox = new HBox();
        bindSizes(FBox, this.rootVBox, halfPercentage, inputHeight);
        AutoCompleteCombo fournisseurCombo = new AutoCompleteCombo(this.administrateur.listeFournisseur());
        fournisseurBox = new LabelledCombo("", fournisseurCombo, labelPercentage, inputPercentage);
        fournisseurBox.getLabel().setGraphic(IconedLabel.plot("Fournisseur", "provider.png", true, iconPrefSize, iconPrefSize));

        ViewDimensionner.bindSizes(fournisseurBox, FBox, 1, 1);

        FBox.getChildren().addAll(Separators.maxSeparatorV(), fournisseurBox);
        rootVBox.getChildren().add(Separators.maxSeparatorH());
        rootVBox.getChildren().add(FBox);
        rootVBox.getChildren().add(Separators.maxSeparatorH());

    }

    public void initProductBox() {

        this.productBox = new HBox();
        ViewDimensionner.bindSizes(productBox, rootVBox, 1, inputHeight);

        AutoCompleteCombo productCombo = new AutoCompleteCombo(this.administrateur.listeProduits());
        productListBox = new LabelledCombo("", productCombo, labelPercentage, inputPercentage);
        productListBox.getLabel().setGraphic(IconedLabel.plot("Produit", "product.png", true, iconPrefSize, iconPrefSize));
        bindSizes(productListBox, productBox, halfPercentage, 1);

        checkOtherProductBox = new LabelledCheckBox("Autre", 0.3, 0.4);
        bindSizes(checkOtherProductBox, productBox, halfPercentage, 1);

        productListBox.disableProperty().bind(checkOtherProductBox.getSelectedProperty());
        checkOtherProductBox.autosize();
        productBox.getChildren().addAll(productListBox, Separators.maxSeparatorV(), checkOtherProductBox, Separators.maxSeparatorV());

        this.rootVBox.getChildren().addAll(productBox, Separators.formSeparatorH());

    }

    public void initNewProductBox() {

        this.newProductBox = new VBox();
        bindSizes(newProductBox, rootVBox, 1, 3 * inputHeight);

        HBox nameAndCatBox = new HBox();

        bindSizes(nameAndCatBox, newProductBox, 1, 0.3);

        newProductNameBox = new LabelledTextField("Nom du produit");
        bindSizes(newProductNameBox, nameAndCatBox, halfPercentage, 1);

        newProductCategorie = new LabelledCombo("Categorie", new AutoCompleteCombo<Categorie>(administrateur.listeCategorie()), labelPercentage, inputPercentage);
        bindSizes(newProductCategorie, nameAndCatBox, halfPercentage, 1);

        nameAndCatBox.getChildren().addAll(newProductNameBox, Separators.maxSeparatorV(), newProductCategorie, Separators.maxSeparatorV());
        newProductBox.getChildren().add(nameAndCatBox);
        newProductBox.getChildren().add(Separators.formSeparatorH());

        this.newProductDescription = new LabelledTextArea("Description du produit");
        bindSizes(newProductDescription, newProductBox, 1, 0.6);
        this.newProductBox.getChildren().add(newProductDescription);

        this.rootVBox.getChildren().addAll(newProductBox, Separators.maxSeparatorH());
        newProductBox.disableProperty().bind(checkOtherProductBox.getSelectedProperty().not());

        /**
         * Ajout des informations sur le produit courant
         */
        this.productListBox.getCombo().valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("New Produit");
            if (newValue == null) {
                this.newProductNameBox.getTextfield().clear();
                this.newProductCategorie.getCombo().getSelectionModel().clearSelection();
                this.newProductDescription.getTextArea().clear();
                this.productSalePrice.getTextfield().clear();
            } else {
                System.out.println("NEWVALUE : " + newValue);
                try {
                    currentProduit = ((Produit) newValue).clone();
                } catch (Exception e) {
                }

                this.newProductNameBox.getTextfield().setText(currentProduit.getNom());
                this.newProductDescription.getTextArea().setText(currentProduit.getDescription());
                this.newProductCategorie.getCombo().getSelectionModel().select(currentProduit.getCategorie());
                this.productSalePrice.getTextfield().setText(String.valueOf(currentProduit.getPrixUnitaire()));
            }

        });

    }

    public void initProductPricesBox() {

        this.productPricesBox = new HBox();
        bindSizes(productPricesBox, rootVBox, 1, inputHeight);

        productBuyPrice = new LabelledMoneyField(CURRENT_DEVISE, "Prix d'achat", labelPercentage, inputPercentage);
        productSalePrice = new LabelledMoneyField(CURRENT_DEVISE, "Prix de vente", labelPercentage, inputPercentage);
        productSalePrice.getTextfield().disableProperty().bind(checkOtherProductBox.getSelectedProperty().not());

        bindSizes(productBuyPrice, productPricesBox, halfPercentage, 1);
        bindSizes(productSalePrice, productPricesBox, halfPercentage, 1);

        productPricesBox.getChildren().addAll(productBuyPrice, Separators.maxSeparatorV(), productSalePrice, Separators.maxSeparatorV());

        this.rootVBox.getChildren().addAll(productPricesBox, Separators.maxSeparatorH());

    }

    public void initProductAmountsBox() {
        this.productAmountsBox = new HBox();
        bindSizes(productAmountsBox, rootVBox, 1.0, inputHeight);

        this.productQuantityBox = new LabelledTextField("Quantité");
        bindSizes(productQuantityBox, productAmountsBox, halfPercentage, 1);

        this.productTotalPriceBox = new LabelledMoneyField(CURRENT_DEVISE, "prix total");
        bindSizes(productTotalPriceBox, productAmountsBox, halfPercentage, 1);
        disable(this.productTotalPriceBox);
        ;
        this.productAmountsBox.getChildren().addAll(this.productQuantityBox, Separators.maxSeparatorV(), this.productTotalPriceBox, Separators.maxSeparatorV());

        this.rootVBox.getChildren().addAll(this.productAmountsBox, Separators.maxSeparatorH());

    }

    public void initAddingToPreviewBox() {
        this.addingToPreviewBox = new HBox();
        bindSizes(addingToPreviewBox, rootVBox, 1, 0.05);

        this.addingToPreviewButton = new JFXButton();
        this.addingToPreviewButton.getStyleClass().add("addingButton");
        addingToPreviewButton.setGraphic(IconedLabel.plot("Ajouter à la commmande", "add2.png", true, iconPrefSize, iconPrefSize));
        this.addingToPreviewButton.setOnAction((event) -> {

            if (currentProduit == null) {
                currentProduit = new Produit();
                currentProduit.setCategorie((Categorie) this.newProductCategorie.getCombo().getValue());
                currentProduit.setNom(this.newProductNameBox.getTextfield().getText());
                currentProduit.setDescription(this.newProductDescription.getTextArea().getText());
                currentProduit.setPrixUnitaire(Integer.valueOf(this.productSalePrice.getTextfield().getText()));
                
            }
            currentProduit.setQuantite(Integer.valueOf(this.productQuantityBox.getTextfield().getText()));
             //currentProduit.setPrixAchat(Double.valueOf(this.productBuyPrice.getTextfield().getText()));
            try {
                //

                listProduits.add(currentProduit);

                LigneCommande l = new LigneCommande();
                clearComponents();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                quickAlert(AlertType.ERROR, "Veuillez remplir les champs correctement");
            }

        });

        this.previewAmount = new LabelledMoneyField(CURRENT_DEVISE, "Montant total de la commande");
        this.previewAmount.getTextfield().setEditable(false);
        bindSizes(this.previewAmount, this.addingToPreviewBox, 0.4, 1);

        this.addingToPreviewBox.getChildren().addAll(addingToPreviewButton, Separators.maxSeparatorV(), previewAmount, Separators.maxSeparatorV());

        this.rootVBox.getChildren().addAll(this.addingToPreviewBox, Separators.maxSeparatorH());

    }

    public void initPreviewTable() {

        this.previewTable = new TableView();
        previewTable.setItems(this.listCommandes);
        bindSizes(this.previewTable, this.rootVBox, 0.98, 0.3);
        CustomSimpleColumn<Produit, String> nomCol = new CustomSimpleColumn("Produit", "", 0.3);

        CustomSimpleColumn<Produit, Integer> qteCol = new CustomSimpleColumn("Quantite", "quantite", 0.1);

        CustomSimpleColumn<Produit, Double> prixAchatCol = new CustomSimpleColumn("Prix d'achat unitaire", "prixAchat", 0.2);
        CustomSimpleColumn<Produit, Double> prixTotalAchatCol = new CustomSimpleColumn("Prix Total", "prixTotal", 0.2);

       

        CustomActionColumn removeProductCol = new CustomActionColumn("", 0.2);
        removeProductCol.setCellFactory(CustomActionFactories.<Produit>removeActionCallback(previewTable));

        TableViewManager.addTableColumns(previewTable, nomCol, qteCol, prixAchatCol, prixTotalAchatCol,removeProductCol);

        this.rootVBox.getChildren().addAll(previewTable, Separators.formSeparatorH());

        BooleanBinding bb = new BooleanBinding() {
            {
                super.bind(previewTable.getItems());
            }

            @Override
            protected boolean computeValue() {
                return (previewTable.getItems().isEmpty());
            }
        };

            this.fournisseurBox.disableProperty().bind(bb.not());
    

    }

    public void initValidationBox() {

        this.validationBox = new HBox();

        bindSizes(this.validationBox, this.rootVBox, 1, 0.1);

        this.discardButton = new JFXButton("");
        this.discardButton.getStyleClass().add("discardButton");
        discardButton.setGraphic(IconedLabel.plot("Annuler la commande", "discard.png", false, iconPrefSize, iconPrefSize));
        bindSizes(discardButton, this.validationBox, 0.2, 1);

        this.finalizeButton = new JFXButton("");
        finalizeButton.setGraphic(IconedLabel.plot("Finaliser la commande", "forward.png", false, iconPrefSize, iconPrefSize));
        bindSizes(this.finalizeButton, this.validationBox, 0.2, 1);
        this.finalizeButton.getStyleClass().add("finalizeButton");

        this.previewButton = new JFXButton("");
        previewButton.setGraphic(IconedLabel.plot("Prévisualiser", "preview.png", false, iconPrefSize, iconPrefSize));
        bindSizes(previewButton, this.validationBox, 0.2, 1);
        this.previewButton.getStyleClass().add("previewButton");

        this.validationBox.getChildren().addAll(Separators.maxSeparatorV(), previewButton, Separators.maxSeparatorV(), finalizeButton, Separators.maxSeparatorV(), discardButton, Separators.maxSeparatorV());

        this.rootVBox.getChildren().add(validationBox);

    }

    public void setDataValidation() {

        Bindings.bindBidirectional(this.productBuyPrice.textProperty(), salePrice, new NumberStringConverter());
        Bindings.bindBidirectional(this.productQuantityBox.textProperty(), newQte, new NumberStringConverter());
        Bindings.bindBidirectional(this.productTotalPriceBox.textProperty(), totalPrice, new NumberStringConverter());
        totalPrice.bind(salePrice.multiply(newQte));

        
        
        
    }

}
