/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class LabelledAutoCombo extends HBox {
    
    
    private String label;
    private ComboBox combo;
    
    
    
    
    public LabelledAutoCombo(String label,ComboBox combo){
        this.setLabel(label);
        this.setCombo(combo);
        
       Label l=new Label(label);
       l.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(l);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
          this.getChildren().add(combo);
      
        this.getStyleClass().add("defaultLabelled");
      
    
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ComboBox getCombo() {
        return combo;
    }

    public void setCombo(ComboBox combo) {
        this.combo = combo;
    }
          
}
