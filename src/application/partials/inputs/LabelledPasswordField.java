/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import static application.utilities.ViewDimensionner.bindSizes;
import javafx.beans.property.StringProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author test
 */

@Getter  @Setter
public class LabelledPasswordField extends HBox {
    
       public StringProperty textProperty(){
           return this.passwordField.textProperty();
       }
       
       
       public LabelledPasswordField(){
       
       }
       
       
       
       
       
       double labelPercentage, textPercentage;
       
       
    private Label label;
    private PasswordField passwordField;

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

    public PasswordField getTextfield() {
        return passwordField;
    }

    public void setTextfield(PasswordField passwordField) {
        this.passwordField = passwordField;
    }
    
    
    
    public LabelledPasswordField(String label,PasswordField tf,double labelP, double tP){
        
        this.setLabel(new Label(label));
        this.setTextfield(tf);

        this.setLabelPercentage(labelP);
        this.setTextPercentage(tP);
                this.init();
    }
    
    
    public LabelledPasswordField(String label,PasswordField tf){
        
       
        this(label,tf,DEFAULT_LP,DEFAULT_TP);
        
    }
     
     
    
    
    public LabelledPasswordField(String label,double labelPercentage,double textPercentage){
        this(label,new PasswordField(),labelPercentage,textPercentage);
        
    }
    
    
    public LabelledPasswordField(String label){
        this(label,new PasswordField(),DEFAULT_LP,DEFAULT_TP);
        
    }
    
    public void init(){
        
        
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(label);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      this.getStyleClass().add("defaultLabelled");
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.passwordField);
            this.passwordField.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
         bindSizes(this.label,this,labelPercentage,1);
        
        bindSizes(sepV,this,1-labelPercentage-textPercentage,1);
      
                bindSizes(this.passwordField,this,this.textPercentage,1);
        
    
        
        
        
    }
    public static double DEFAULT_LP=0.3, DEFAULT_TP=0.69;
    
    
    
}
