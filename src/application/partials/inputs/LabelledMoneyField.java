
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import static application.utilities.ViewDimensionner.bindSizes;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author test
 */
@Getter @Setter
public class LabelledMoneyField extends LabelledTextField {
    
    
    private Label deviseLabel;

    public Label getDeviseLabel() {
        return deviseLabel;
    }

    public void setDeviseLabel(Label deviseLabel) {
        this.deviseLabel = deviseLabel;
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

   
    public LabelledMoneyField(String deviseLabel, String label, TextField tf, double labelP, double tP) {
            super();
            
         this.setLabelPercentage(labelP);
        this.setTextPercentage(tP-0.1);
        this.setTextfield(tf);
        this.deviseLabel = new Label(deviseLabel);
        this.deviseLabel.getStyleClass().add("devise");
        bindSizes(this.deviseLabel,this,0.1,1);
        super.init();
        
        this.getChildren().add(this.deviseLabel);
        
        
        
        
        
        this.deviseLabel.getStyleClass().add("devise");
    }

    public LabelledMoneyField(String deviseLabel, String label, TextField tf) {
        
        this.setLabel(new Label(label));
        this.setTextfield(tf);
        this.setTextPercentage(DEFAULT_TP-0.1);
        this.setLabelPercentage(DEFAULT_LP);
        super.init();
        this.deviseLabel = new Label(deviseLabel);
            this.deviseLabel.getStyleClass().add("devise");
            this.getChildren().add(this.deviseLabel);
           
            bindSizes(this.deviseLabel,this,0.1,1);
    
    }

    public LabelledMoneyField(String deviseLabel, String label, double labelPercentage, double textPercentage) {
        super(label, labelPercentage, textPercentage-0.1);
        this.deviseLabel = new Label(deviseLabel);
            this.deviseLabel.getStyleClass().add("devise");
            this.getChildren().add(this.deviseLabel);
            bindSizes(this.deviseLabel,this,0.1,1);
    }

    public LabelledMoneyField(String deviseLabel, String label) {
        super(label,DEFAULT_LP,DEFAULT_TP-0.1);
        this.deviseLabel = new Label(deviseLabel);
            this.deviseLabel.getStyleClass().add("devise");
           this.getChildren().add(this.deviseLabel);
            
           bindSizes(this.deviseLabel,this,0.1,1);

    }
    
    
    public static double DEFAULT_LP=0.3, DEFAULT_TP=0.69;
    
    
}
