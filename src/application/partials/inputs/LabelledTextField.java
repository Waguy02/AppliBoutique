/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.StringProperty;
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
    
       public StringProperty textProperty(){
           return this.textfield.textProperty();
       }
       
       
       
       
       
       
       
       
       
       
       
    private Label label;
    private TextField textfield;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
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
        this.setLabel(new Label(label));
        this.init();
        
    }
    
    public void init(){

       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(label);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.textfield);
            this.textfield.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
    
        
        
    }
    public LabelledTextField(String label,TextField tf){
        
        this.setLabel(new Label(label));
        this.setTextfield(tf);
        this.init();
        
    }
    
}
