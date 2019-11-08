/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.test;

import Init.Init;
import application.utilities.ViewLoaders;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author test
 */
public class TestAdmin extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage.setMaximized(true);
         //  Parent root=(AnchorPane)getView(getLoader("layouts/caissiere/mainTabPane/mainTabPane"));
    AnchorPane rootLayout=(AnchorPane)ViewLoaders.getView("layouts/admin/mainAdminPane/mainAdminPane");
    primaryStage.setScene(new Scene(rootLayout));  
    
    
    rootLayout.sceneProperty().addListener(new ChangeListener<Scene>() {
                @Override
                public void changed(ObservableValue<? extends Scene> observable,
                        Scene oldValue, Scene newValue) {
                    rootLayout.prefWidthProperty().bind(newValue.widthProperty());
                    rootLayout.prefHeightProperty().bind(newValue.heightProperty());
                }
            });
     //
     primaryStage.show();
        
       
    }
    
      public static void main(String args[]){
        Init.globalInit();
        launch(args);
    }

}
