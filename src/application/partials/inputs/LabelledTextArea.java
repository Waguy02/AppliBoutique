/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import com.jfoenix.controls.JFXTextArea;
import javafx.beans.property.StringProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class LabelledTextArea extends HBox {
    
       public StringProperty textProperty(){
           return this.textArea.textProperty();
       }
       
       
       
       
       
       
       
       
       
       
       
    private Label label;
    private TextArea textArea;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(TextArea TextArea) {
        this.textArea = TextArea;
    }
    
    
    public LabelledTextArea(String label){
        this.textArea=new JFXTextArea();
        this.setLabel(new Label(label));
        this.init();
        
    }
    
    public void init(){
        
        
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(label);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.textArea);
            this.textArea.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
    
        
        
    }
    public LabelledTextArea(String label,TextArea tf){
        
        this.setLabel(new Label(label));
        this.setTextArea(tf);
        this.init();
        
    }
    
}
