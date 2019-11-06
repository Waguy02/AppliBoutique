/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import Init.Init;
import application.components.productCard.ProductCardController;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
       AnchorPane root=FXMLLoader.load(Init.class.getResource("/application/"+path+".fxml"));
   return root;
    }
    catch(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
    return null;
    }
    
}


      public static void openProductCard(Produit product, ObservableList<Produit> productList){
        ProductCardController controller=null;
          try {
            FXMLLoader loader=getLoader("components/productCard/productCard");
            Parent root=(AnchorPane)getView(loader);
            Stage stage = new Stage();
            stage.setTitle("Informations sur le produit");
            stage.setScene(new Scene(root,800 ,600));
            
            controller=loader.getController();
            
            controller.setProductList(productList);
            
            // Hide this current window (if this is what you want)
            controller.customInit();
            stage.showAndWait();
        }
        catch ( Exception e) {
            e.printStackTrace();
        }

    
      }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
