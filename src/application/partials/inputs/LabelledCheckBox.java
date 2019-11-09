/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import application.partials.Separators;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class LabelledCheckBox extends HBox {
  private Label label;
  
  public  BooleanProperty getSelectedProperty(){
      return this.checkBox.selectedProperty();
  }
  private CheckBox checkBox;

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(JFXCheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public LabelledCheckBox(String label, JFXCheckBox checkBox) {
        this.label=new Label(label);
        this.checkBox = checkBox;
        this.init();
    }

    public LabelledCheckBox(String label) {
        this.label=new Label(label);
        this.checkBox=new JFXCheckBox();
        this.init();
    }
  
  
  
    
    public void init(){
        
         
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(this.label);

      this.getChildren().add(Separators.labelSeparatorV());
      
      this.getChildren().add(this.checkBox);
            this.getCheckBox().getStyleClass().add("defaultInput");
        
            this.getStyleClass().add("defaultLabelled");
      
    
        
    }
    
    
}
