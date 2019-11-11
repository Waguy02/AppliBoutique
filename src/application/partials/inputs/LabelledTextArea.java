/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import static application.utilities.ViewDimensionner.bindSizes;
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
    
    private double labelPercentage=0.3, textPercentage=0.69;

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
    
    
    
    public LabelledTextArea(String label,double labelP, double tP){
        this.textArea=new JFXTextArea();
        this.setLabel(new Label(label));
        
        this.setLabelPercentage(labelP);
        this.setTextPercentage(tP);
        this.init();
        
    }
    
    public void init(){
        
        this.textArea.getStyleClass().add("defautlInput");
       this.label.getStyleClass().add("defaultLabel");
       
      this.getChildren().add(label);

      Separator sepV=new Separator(Orientation.VERTICAL);
      sepV.getStyleClass().add("defaultSeparatorV");
      
      
      this.getChildren().add(sepV);
      
      
      
      this.getChildren().add(this.textArea);
            this.textArea.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");
      
    
     bindSizes(this.label,this,labelPercentage,1);
        
        bindSizes(sepV,this,1-labelPercentage-textPercentage,1);
      
                bindSizes(this.textArea,this,this.textPercentage,1);
        
    
        
        
    }
    public LabelledTextArea(String label,TextArea tf,double labelP, double tP){
        
        this.setLabel(new Label(label));
        this.setTextArea(tf);
           
        this.setLabelPercentage(labelP);
        this.setTextPercentage(tP);
 
        
        this.init();
        
    }
    

        public LabelledTextArea(String label,TextArea tf){
        this(label,tf,DEFAULT_LP,DEFAULT_TP);
        
    }
        
        
        public LabelledTextArea(String label){
        this(label,new JFXTextArea(),DEFAULT_LP,DEFAULT_TP);
        
    }
    

    public static double DEFAULT_LP=0.3, DEFAULT_TP=0.69;
}
