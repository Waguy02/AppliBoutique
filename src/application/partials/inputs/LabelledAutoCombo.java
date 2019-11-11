/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import application.partials.Separators;
import static application.utilities.ViewDimensionner.bindSizes;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class LabelledAutoCombo extends HBox {
    
    
    private Label label;
    private ComboBox combo;
    
    
    
     public LabelledAutoCombo(String label,ComboBox combo){
         this(label,combo,0.3,0.6);
     }
    public LabelledAutoCombo(String label,ComboBox combo,double labelPercentage,double comboPercentage){
        this.setLabel(new Label(label));
        this.setCombo(combo);
        
        
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(this.label);

      Separator sepV=Separators.labelSeparatorV();
      
      
      this.getChildren().add(sepV);
      
      
      
          this.getChildren().add(combo);
      
          this.combo.getStyleClass().add("defautlInput");
          this.getStyleClass().add("defaultLabelled");
        bindSizes(this.label,this,labelPercentage,1);
        
        bindSizes(sepV,this,1-labelPercentage-comboPercentage,1);
      
                bindSizes(this.combo,this,comboPercentage,1);
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }


    public ComboBox getCombo() {
        return combo;
    }

    public void setCombo(ComboBox combo) {
        this.combo = combo;
    }
          
}
