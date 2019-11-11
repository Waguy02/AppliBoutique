/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.inputs;

import application.partials.Separators;
import static application.utilities.ViewDimensionner.bindSizes;
import javafx.beans.property.StringProperty;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author test
 */
@Getter  @Setter
public class LabelledTextField extends HBox {

    public StringProperty textProperty() {
        return this.textfield.textProperty();
    }

    public LabelledTextField() {

    }

    double labelPercentage, textPercentage;

    private Label label;
    private TextField textfield;

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

    public TextField getTextfield() {
        return textfield;
    }

    public void setTextfield(TextField textfield) {
        this.textfield = textfield;
    }

    public LabelledTextField(String label, TextField tf, double labelP, double tP) {

        this.setLabel(new Label(label));
        this.setTextfield(tf);

        this.setLabelPercentage(labelP);
        this.setTextPercentage(tP);
        this.init();
    }

    public LabelledTextField(String label, TextField tf) {

        this(label, tf, DEFAULT_LP, DEFAULT_TP);

    }

    public LabelledTextField(String label, double labelPercentage, double textPercentage) {
        this(label, new TextField(), labelPercentage, textPercentage);

    }

    public LabelledTextField(String label) {
        this(label, new TextField(), DEFAULT_LP, DEFAULT_TP);

    }

    public void init() {

        this.label.getStyleClass().add("defaultLabel");

        this.getChildren().add(label);

        //Separator sepV = new Separator(Orientation.VERTICAL
        Separator sepV=Separators.maxSeparatorV();
        sepV.getStyleClass().add("defaultSeparatorV");

        this.getStyleClass().add("defaultLabelled");
        this.getChildren().add(sepV);

        this.getChildren().add(this.textfield);
        this.textfield.getStyleClass().add("defaultInput");
        this.getStyleClass().add("defaultLabelled");

        bindSizes(this.label, this, labelPercentage, 1);

      //  bindSizes(sepV, this, 1 - labelPercentage - textPercentage, 1);

        bindSizes(this.textfield, this, this.textPercentage, 1);

    }

    
    
    
    
    
    
    
    
    
    public static double DEFAULT_LP=0.3, DEFAULT_TP=0.69;
    
    
    
}


