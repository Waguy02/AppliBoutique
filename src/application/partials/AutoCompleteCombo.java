/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials;

import com.jfoenix.controls.JFXComboBox;
import java.lang.reflect.Method;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import model.Produit;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author test
 */
public class AutoCompleteCombo<T> extends JFXComboBox {

    private ObservableList<T> data;

    private String targetProperty;

    public ObservableList<T> getData() {
        return data;
    }

    public void setData(ObservableList<T> data) {
        this.data = data;
    }

    public String getTargetProperty() {
        return targetProperty;
    }

    public void setTargetProperty(String targetProperty) {
        this.targetProperty = targetProperty;
    }

    
    public void configure() {
        this.setItems(this.data);
        targetProperty = targetProperty.substring(0, 1).toUpperCase().concat(targetProperty.substring(1, targetProperty.length() ));
        String getter = "get" + targetProperty;
        String setter = "set" + targetProperty;
   this.setEditable(true);
        
            
   
        System.out.println("Getter : "+getter +" - ----  -"+"Setter : "+setter);
     

     /*   this.setConverter(new StringConverter<T>() {
            Method getterMethod, setterMethod;

            @Override
            public String toString(T object) {
                
                if (object == null) {
                    return null;
                }
                try {
                    getterMethod = object.getClass().getMethod(getter, null);


                if (getterMethod.invoke(object,null) == null) {
                    return "NO VALUE";
                } else {
                    
                   return getterMethod.invoke(object,null).toString();
                }
                
                 
            
                
                } catch (Exception e) {
                    
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                                        return "NO VALUE";

                }
            }

            @Override
            public T fromString(String string) {
                return (T)new Object();
            }

        });
        */
        
        
        TextFields.bindAutoCompletion(this.getEditor(), data);

    }
    
    
    public AutoCompleteCombo(String targetProperty, ObservableList<T> data){
        
        this.targetProperty=targetProperty;
        this.data=data;
        this.configure();
        this.getStyleClass().add("defaultCombo");
        
    }
    
    public AutoCompleteCombo(ObservableList<T> data){
        this("nom",data);
        
        
    }
}
