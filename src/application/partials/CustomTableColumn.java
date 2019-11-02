/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author test
 */
public class CustomTableColumn<S,T> extends TableColumn{
    
    
    Double widthPercentage;

    public Double getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(Double widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public CustomTableColumn(String text){
        super(text);
    }
    
    public CustomTableColumn(String text,String targetProperty){
        super(text);
        this.setCellValueFactory(new PropertyValueFactory<>(targetProperty));
    }
    
    
    public CustomTableColumn(String text,String targetProperty,Double widthPercentage){
        this(text,targetProperty);
        this.setWidthPercentage(widthPercentage);
    }
}
