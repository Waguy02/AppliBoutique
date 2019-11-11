/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author test
 */
public class AlertsManager {
    
    public static boolean showConfirmation(String message){
        Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("confirmation");
      
        alert.setContentText(message);
      
      
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == null) {
      return false;
      } else if (option.get() == ButtonType.OK) {
          return true;
      } else if (option.get() == ButtonType.CANCEL) {
         return false;
    
   }
        
        return false;
        
        
    }
}
    

