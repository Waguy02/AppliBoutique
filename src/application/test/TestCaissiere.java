/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.test;

import Init.Init;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author test
 */
public class TestCaissiere extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
       primaryStage.setMaximized(true);
         //  Parent root=(AnchorPane)getView(getLoader("layouts/caissiere/mainTabPane/mainTabPane"));
    AnchorPane rootLayout=(AnchorPane)FXMLLoader.load(Init.class.getResource("/application/layouts/caissiere/mainTabPane/mainTabPane.fxml"));
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
