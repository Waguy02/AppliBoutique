/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane;

import application.layouts.caissiere.mainCaissierPane.MainCaissierPaneController;
import application.layouts.caissiere.salePane.finalize.SaleFinalizeController;
import application.layouts.caissiere.salePane.preview.SalePreviewTableController;
import application.partials.inputs.AutoCompleteCombo;
import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledAutoCombo;
import application.partials.inputs.LabelledTextField;
import application.utilities.inputs.InputBindings;
import static application.utilities.Tools.disable;
import static application.utilities.Tools.quickAlert;
import static application.utilities.ViewDimensionner.bindSizes;
import static application.utilities.ViewLoaders.getLoader;
import static application.utilities.ViewLoaders.getView;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Client;
import model.Facture;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class SalePaneController implements Initializable, CustomController {

    private MainCaissierPaneController mainController;
    private SalePreviewTableController salePreviewController;

    @FXML
    private VBox rootVBox;
    @FXML
    private HBox validationBox;

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

    public void setProdCombo(JFXComboBox<Produit> prodCombo) {
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

    private ObservableList<Produit> previewItems = FXCollections.observableArrayList();

    public MainCaissierPaneController getMainController() {
        return mainController;
    }

    public void setMainController(MainCaissierPaneController mainController) {
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

    public JFXComboBox<Client> getClientCombo() {
        return clientCombo;
    }

    public void setClientCombo(JFXComboBox<Client> clientCombo) {
        this.clientCombo = clientCombo;
    }
    DoubleProperty sum = new SimpleDoubleProperty();
    BooleanProperty finalizable = new SimpleBooleanProperty(), finalizable1 = new SimpleBooleanProperty(), finalizable2 = new SimpleBooleanProperty();

    @FXML
    private AnchorPane addingAnchor;

    @FXML
    private HBox productAddBox;

    private HBox productNameSearcBox;
    @FXML
    private AnchorPane previewAnchor;
    @FXML
    private AnchorPane validationAnchor;

    private JFXButton validationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void customInit() {

        this.previewAnchor.minHeightProperty().bind(rootVBox.heightProperty().multiply(0.5));
        this.validationAnchor.maxHeightProperty().bind(rootVBox.heightProperty().multiply(0.1));
        this.initAddProductBar();
        this.initPreviewTable();
        this.initValidationAnchor();
    }

    JFXComboBox<Produit> prodCombo;

    JFXComboBox<Client> clientCombo;
    JFXButton addItemButton;
    JFXTextField qteField;

    public void initAddProductBar() {
        bindSizes(this.productAddBox,this.addingAnchor,0.5,1);
        prodCombo = new AutoCompleteCombo("nom", this.mainController.getListeAvailabeProduit());
        LabelledAutoCombo lbAuto=new LabelledAutoCombo("Produit ", prodCombo);
        this.productAddBox.getChildren().add(lbAuto);
        
        bindSizes(lbAuto,this.productAddBox,1,0.5);
        this.mainController.getListeAvailabeProduit().addListener(new ListChangeListener<Produit>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produit> c) {
                clearProductBar();
            }

        });
        qteField = new JFXTextField();
        this.productAddBox.getChildren().add(new LabelledTextField("Quantité", qteField));

        InputBindings.bindQteInput(qteField, prodCombo, "quantite", Produit.class);

        addItemButton = new JFXButton("", IconedLabel.plot("Ajouter", "add_64px.png", true));
        this.productAddBox.getChildren().add(addItemButton);

        this.initAddButton();
    }

    public void clearProductBar() {
        this.prodCombo.getSelectionModel().clearSelection();
        disable(this.qteField);

    }

    public void initAddButton() {
        this.addItemButton.setOnAction(event -> {
            Produit newP = this.prodCombo.getValue().clone();

            Integer qte = Integer.valueOf(this.qteField.getText());

            if (qte == 0) {

                quickAlert(Alert.AlertType.ERROR, "Veuillez préciser la quantité");
                return;
            }

            newP.setQuantite(qte);

            boolean contains = false;

            for (Produit p : this.previewItems) {
                if (p.getId().equals(newP.getId())) {
                    contains = true;
                    p.setQuantite(p.getQuantite() + newP.getQuantite());

                }
            }

            if (!contains) {

                this.previewItems.add(newP);
            }
            //Mise à jour des produits disponibles :

            Produit p0 = null;
            for (Produit availableP : this.mainController.getListeAvailabeProduit()) {

                if (availableP.getId().equals(newP.getId())) {
                    p0 = availableP;
                    break;
                }
            }

            p0.setQuantite(p0.getQuantite() - newP.getQuantite());
            this.mainController.getListeAvailabeProduit().remove(p0);
            this.mainController.getListeAvailabeProduit().add(p0);

        }
        );

    }

    public void initPreviewTable() {

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
                        sum.set(sum.get() - remitem.getPrixTotal());
                        System.out.println("ELEMNT REMOVED : " + remitem.getId());
                        for (Produit p : prodCombo.getItems()) {

                            if (p.getId().equals(remitem.getId())) {

                                p.setQuantite(p.getQuantite() + remitem.getQuantite());
                            }
                        }
                    }

                    for (Produit addItem : c.getAddedSubList()) {
                        sum.set(sum.get() + addItem.getPrixTotal());

                    }
                }
            }

        }
        );

    }

    public void initValidationAnchor() {
        LabelledTextField total = new LabelledTextField("Total");
        total.getTextfield().textProperty().bind(sum.asString());
        bindSizes(total,this.validationAnchor,0.2,1);
        this.validationBox.getChildren().add(total);
        
        this.validationBox.getChildren().add(Separators.maxSeparatorV());

        this.clientCombo = new AutoCompleteCombo("nom", this.mainController.getCaissier().getClientList());
        LabelledAutoCombo cl=new LabelledAutoCombo("Client", this.clientCombo);
        this.validationBox.getChildren().add(cl);
        bindSizes(cl,this.validationAnchor,0.2,1);   
        
        
        this.validationBox.getChildren().add(Separators.maxSeparatorV());

        this.validationButton = new JFXButton("");
        this.validationButton.setGraphic(IconedLabel.plot("Suivant", "forward.png", false,60,60));
        this.validationBox.getChildren().add(validationButton);
        bindSizes(validationButton,this.validationBox,0.2,1);
        this.validationButton.getStyleClass().add("actionButton");

        initValidationButton();     

    }

    public void initValidationButton() {

        this.sum.addListener((observable, oldValue, newValue) -> {
            if ((Double) newValue > 0.0) {
                finalizable1.set(true);
            } else {
                finalizable2.set(false);
            }
        });
        
        
        this.clientCombo.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue == null) {
                    finalizable2.set(false);
                } else {
                    finalizable2.set(true);
                }
            }
        });
        
        finalizable.bind(finalizable1.and(finalizable2));

        this.validationButton.disableProperty().bind(finalizable.not());

    
    
    this.validationButton.setOnAction(event->finalizeHandler(event));
    
    }
    
    public void finalizeHandler(Event event){
    
        Facture facture=new Facture();
        
        Stage finalizeStage=new Stage();
        finalizeStage.setTitle("Finalisation de vente");
        finalizeStage.initModality(Modality.APPLICATION_MODAL);
      
        
        
        
        
        
        FXMLLoader loader=getLoader("layouts/caissiere/salePane/finalize/saleFinalize");
        Parent root=(AnchorPane)getView(loader);
        
        Rectangle2D primaryScreen = Screen.getPrimary().getVisualBounds();
        
        
         Scene scene=new Scene(root,primaryScreen.getWidth()*0.8,primaryScreen.getHeight()*0.9);
         
        finalizeStage.setScene(scene);
        
        SaleFinalizeController finalizeController=loader.getController();
        
        
        finalizeController.setFacture(facture);
        finalizeController.setClient(this.clientCombo.getValue());
        finalizeController.customInit();
        
        finalizeStage.show();
        
        
        
        
    }
    

}
