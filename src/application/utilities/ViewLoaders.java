/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import Init.Init;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
