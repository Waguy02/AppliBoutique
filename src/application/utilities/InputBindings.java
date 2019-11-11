/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import static application.utilities.Tools.disable;
import static application.utilities.Tools.enable;
import static java.lang.System.out;
import java.lang.reflect.Method;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author test
 */
public class InputBindings {

    
    /**
     * 
     * Cette méthode permet de lier un comboBox BOx à un textField de type choix de quantite
     * @param qteField
     * @param combo
     * @param targetProperty
     * @param dataClass 
     */
public static void bindQteInput(TextField qteField, ComboBox combo, String targetProperty,Class dataClass){
        
    targetProperty = targetProperty.substring(0, 1).toUpperCase().concat(targetProperty.substring(1, targetProperty.length() ));
    String getter = "get" + targetProperty;
    
      Method getterMethod;
                   try {
                        
                    getterMethod =dataClass.getMethod(getter, null);        
                }
                catch (Exception e) {
                    
                    System.out.println(e.getMessage()+" : METHODE NON RETROUVEE PAR REFLEXION");
                    e.printStackTrace();
                    return;
                    }

    
    
    
    
        
      qteField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
               
                if(newValue)return; // On sort en cas d'édition
                if(qteField.getText().isEmpty()||qteField.getText()==null) qteField.setText("0");
                else{
                
                try{
                    Integer val=Integer.valueOf(qteField.getText());
                    if(val<=0){qteField.setText("0");return;}
                        
                    out.println(combo.getValue());
                    
                    
                    Integer max=(Integer)getterMethod.invoke(combo.getValue(),null);
                    
                    
                    if(val>=max){qteField.setText(String.valueOf(max));
                    
                    
                    
                    
                    return;
                    }
                
                }
             catch(Exception e){
                 
                 qteField.setText("0");
             
             }
               
      
      
        
                }}}
);
    
 combo.valueProperty().addListener(new ChangeListener(){
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            if(newValue==null) disable(qteField);
            else{ enable(qteField);
           Integer max;
            try{
            max=(Integer)getterMethod.invoke(newValue,null);
            }
            catch(Exception e){
                max=0;
            }
           qteField.setText(max.toString());
                    };}
        });
         disable(qteField);
         
         System.out.println("Association effectuée avec suscès");
}    


}
