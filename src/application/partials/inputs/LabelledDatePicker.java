/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import static application.utilities.ViewDimensionner.bindSizes;
import com.jfoenix.controls.JFXDatePicker;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author test
 */

@Getter @Setter
public class LabelledDatePicker extends HBox {
    
       public ObjectProperty valueProperty(){
          return this.datePicker.valueProperty();
       }
       
       
       public LabelledDatePicker(){
       
       }
       
       
       
       
       
       double labelPercentage, textPercentage;
       
       
    private Label label;
    private JFXDatePicker datePicker;
    

    public Label getLabel() {
        return label;
    }

    public double getLabelPercentage() {
        return labelPercentage;
    }

    public void setLabelPercentage(double labelPercentage) {
        this.labelPercentage = labelPercentage;
    }

    public double getTextPercentage() {
        return textPercentage;
    }

    public void setTextPercentage(double textPercentage) {
        this.textPercentage = textPercentage;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    
    
    
    public LabelledDatePicker(String label,JFXDatePicker tf,double labelP, double tP){
        
        this.setLabel(new Label(label));
        this.datePicker=tf;

        this.setLabelPercentage(labelP);
        this.setTextPercentage(tP);
                this.init();
    }
    
    
    public LabelledDatePicker(String label,JFXDatePicker tf){
        
       
        this(label,tf,DEFAULT_LP,DEFAULT_TP);
        
    }
     
     
    
    
    public LabelledDatePicker(String label,double labelPercentage,double textPercentage){
        this(label,new JFXDatePicker(),labelPercentage,textPercentage);
        
    }
    
    
    public LabelledDatePicker(String label){
        this(label,new JFXDatePicker(),DEFAULT_LP,DEFAULT_TP);
        
    }
    
    public void init(){
        
        
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(label);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      this.getStyleClass().add("defaultLabelled");
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.datePicker);
            this.datePicker.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
         bindSizes(this.label,this,labelPercentage,1);
        
        bindSizes(sepV,this,1-labelPercentage-textPercentage,1);
      
                bindSizes(this.datePicker,this,this.textPercentage,1);
        
    
        
        
        
    }
    
    public static double DEFAULT_LP=0.3, DEFAULT_TP=0.69;
    
}
