/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class LabelledTextField extends HBox {
    
    
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
    
    
    public LabelledTextField(String label){
        this.textfield=new JFXTextField();
        this.setLabel(label);
        this.init();
        
    }
    
    public void init(){
        
         Label l=new Label(label);
       l.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(l);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.textfield);
            this.textfield.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
    
        
        
    }
    public LabelledTextField(String label,TextField tf){
        
        this.setLabel(label);
        this.setTextfield(tf);
        this.init();
        
    }
    
}