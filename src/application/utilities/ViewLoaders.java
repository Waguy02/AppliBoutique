/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import Init.Init;
import application.components.productCard.ProductCardController;
import application.components.userCrudBox.UserCrudBoxController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Administrateur;
import model.Employe;
import model.Produit;

/**
 *
 * @author test
 */
public class ViewLoaders {
  
    
    
    
public static FXMLLoader getLoader(String path){
    
    FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Init.class.getResource("/application/"+path+".fxml"));
   
    
     
return loader;
}


public static Node getView(FXMLLoader loader){
    try{
    return loader.load();
    }
    catch(Exception e){
        e.printStackTrace();
        return null;
    }
}




public static Node getView(String path){
    try{
      Node root=FXMLLoader.load(Init.class.getResource("/application/"+path+".fxml"));
   return root;
    }
    catch(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
    return null;
    }
    
}


public static void openProductCard(Produit product, ObservableList<Produit> productList,boolean readingMode,boolean editingMode,boolean registeringMode){
        ProductCardController controller=null;
          try {
            FXMLLoader loader=getLoader("components/productCard/productCard");
            Parent root=(AnchorPane)getView(loader);
            root.getStyleClass().add("-fx-padding:20 20 20 20");
            Stage stage = new Stage();
            stage.setTitle("Informations sur le produit");
               Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setMinWidth(screenBounds.getWidth() * 0.7);
            stage.setMinHeight(screenBounds.getHeight() * 0.9);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            
            controller=loader.getController();
            controller.setCurrentProduit(product);
            controller.setProductList(productList);
            controller.getReadingMode().set(readingMode);
            controller.getRegisteringMode().set(registeringMode);
            controller.getEditingMode().set(editingMode);
            controller.getIsOpen().addListener((o,x,y)->{
                if(!y)stage.close();
            });
            controller.customInit();
            stage.showAndWait();
        }
        catch ( Exception e) {
            e.printStackTrace();
        }

    
      }
    
    
    
public static void openUserCrud(Employe current,Employe manager, ObservableList<Employe> list,boolean regMode,boolean editMode,boolean viewMode){
        
            Stage addStage = new Stage();
            addStage.setTitle("Ajout d'utilisateur");
            addStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = getLoader("components/userCrudBox/userCrudBox");
            AnchorPane root = (AnchorPane) getView(loader);
            root.getStyleClass().add("actionForm");
            addStage.setScene(new Scene(root));
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            addStage.setMinWidth(screenBounds.getWidth() * 0.6);
            addStage.setMinHeight(screenBounds.getHeight() * 0.9);
            UserCrudBoxController crudController = loader.getController();
            
            crudController.getREGISTERING_MODE().set(regMode);
            crudController.getEDITING_MODE().set(editMode);
            crudController.getREADING_MODE().set(viewMode);
            crudController.setManager(manager);
            crudController.setCurrentEmploye(current);
            crudController.customInit();
           

            addStage.show();
            crudController.getOPEN_PROPERTY().addListener((observale, oldValue, newValue) -> {;
                if (!newValue) {
                    addStage.close();
                }
            }
            );

        

    }
    




public static void openFournBox(){
    
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
