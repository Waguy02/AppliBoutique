/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import javafx.scene.control.TableColumn;

/**
 *
 * @author test
 */
public class CustomColumn<T,S> extends TableColumn{
    Double widthPercentage;

    public Double getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(Double widthPercentage) {
        this.widthPercentage = widthPercentage;
    }

    public CustomColumn() {
    }

    public CustomColumn(String text) {
        super(text);
    }

    
    
    public CustomColumn(Double widthPercentage,String text) {
        super(text);
        this.widthPercentage = widthPercentage;
    }
    
    
    
}
