/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;

/**
 *
 * @author test
 */
public class LabelledTextField extends TextField {
    
    
    private String label;
    private TextField textfield;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TextField getTextfield() {
        return textfield;
    }

    public void setTextfield(TextField textfield) {
        this.textfield = textfield;
    }
    
    public LabelledTextField(String label,TextField tf){
        
        this.setLabel(label);
        this.setTextfield(tf);
        
         Label l=new Label(label);
       l.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(l);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(tf);
            tf.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
    
        
        
    }
    
}
