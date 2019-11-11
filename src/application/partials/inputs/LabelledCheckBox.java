/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import application.partials.Separators;
import static application.utilities.ViewDimensionner.bindSizes;
import com.jfoenix.controls.JFXCheckBox;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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
    
    
    double labelPercentage,checkPercentage;

    public LabelledCheckBox(String label, JFXCheckBox checkBox,double labelPercentage,double checkPercentage) {
        this.label=new Label(label);
        this.checkBox = checkBox;
        this.labelPercentage=labelPercentage;
        this.checkPercentage=checkPercentage;
        
        this.init();
    }
    
    
    public LabelledCheckBox(String label, JFXCheckBox checkBox) {
        this(label,checkBox, 0.4,0.4);
    }

    public LabelledCheckBox(String label) {
        this(label,new JFXCheckBox());
        
        
    }
  
    
    
    public LabelledCheckBox(String label,double labelPercentage,double checkPercentage) {
        this(label,new JFXCheckBox(),labelPercentage,checkPercentage);
    }

    
  
    
    public void init(){
        
         
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(this.label);

      
      Separator sep=Separators.labelSeparatorV();
      this.getChildren().add(sep);
      
      this.getChildren().add(this.checkBox);
            this.getCheckBox().getStyleClass().add("defaultInput");
        
                this.getStyleClass().add("defaultLabelled");
                this.getStyleClass().add("defaultLabelled");
    
            
            bindSizes(this.label,this,labelPercentage,1);
        
        bindSizes(sep,this,1-labelPercentage-checkPercentage,1);
      
                bindSizes(this.checkBox,this,checkPercentage,1);
        
    }
    
    
}
