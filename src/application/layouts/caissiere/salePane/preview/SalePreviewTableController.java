/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane.preview;

import application.layouts.caissiere.salePane.SalePaneController;
import application.partials.table.CustomActionColumn;
import application.partials.table.CustomActionFactories;
import application.partials.table.CustomSimpleColumn;
import application.utilities.TableViewManager;
import application.utilities.ViewLoaders;
import application.utilities.interfaces.CustomController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Produit;

/**
 * FXML Controller class
 *
 * @author test
 */
public class SalePreviewTableController implements Initializable , CustomController{
    
    private SalePaneController baseController;

    public SalePaneController getBaseController() {
        return baseController;
    }

    public void setBaseController(SalePaneController baseController) {
        this.baseController = baseController;
    }
    
    @FXML
    private TableView<Produit> previewTable;
    
    private ObservableList<Produit> previewList;

    public TableView<Produit> getPreviewTable() {
        return previewTable;
    }

    public void setPreviewTable(TableView<Produit> previewTable) {
        this.previewTable = previewTable;
    }

    public ObservableList<Produit> getPreviewList() {
        return previewList;
    }

    public void setPreviewList(ObservableList<Produit> previewList) {
        this.previewList = previewList;
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void customInit(){
    this.initPreviewTable();
    
    }   
    
    
    public void initPreviewTable(){
    CustomSimpleColumn<Produit, String> colID = new CustomSimpleColumn("ID", "id", 0.15),
    colLibelle = new CustomSimpleColumn("nom", "Nom",0.30);
   CustomSimpleColumn<Produit, Integer> colQuantite = new CustomSimpleColumn("Quantite", "quantite", 0.15);
   CustomSimpleColumn<Produit, Double> colPu = new CustomSimpleColumn("Prix Unitaire", "prixUnitaire", 0.15);
   CustomSimpleColumn<Produit, Double> colPt = new CustomSimpleColumn("Prix Total", "prixTotal", 0.15);
   CustomActionColumn<Produit,String> colRm=new CustomActionColumn("Action",0.1);
   colRm.setCellFactory(CustomActionFactories.<Produit>removeActionCallback(previewTable));
   
   
   TableViewManager.addTableColumns(previewTable,colID,colLibelle,colQuantite,colPu,colPt,colRm);
 this.previewTable.setItems(previewList);
 makeTableClickable();
       
    }
    

private DoubleProperty sumProperty=new SimpleDoubleProperty();



public void openProductCard(Produit product){
    ViewLoaders.openProductCard(product,this.baseController.getProdCombo().getItems(),true,false,false);
    
}

static Produit selectedProduct;
    public void makeTableClickable(){

        ContextMenu cm = new ContextMenu();
        cm.getStyleClass().add("default_context");
        MenuItem menu = new MenuItem("Ouvrir la fiche du produit");
        cm.getItems().add(menu);

        menu.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                           openProductCard(selectedProduct);
                        }});


        this.previewTable.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
    @Override
           public void handle(MouseEvent t) {
            cm.hide();

           selectedProduct=previewTable.getSelectionModel().getSelectedItem();
           if(selectedProduct==null)return;

            if(t.getButton() == MouseButton.SECONDARY) {
            System.out.println("Bouton droit");   
                cm.show(previewTable, t.getScreenX(), t.getScreenY());
            }
        }
    });







    }


    
}
