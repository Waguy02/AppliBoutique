/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author test
 */
public class CustomSimpleColumn<S,T> extends CustomColumn{
    
    
   
    public CustomSimpleColumn(String text){
        super(text);
    }
    
    public CustomSimpleColumn(String text,String targetProperty){
        super(text);
        this.setCellValueFactory(new PropertyValueFactory<>(targetProperty));
    }
    
    
    public CustomSimpleColumn(String text,String targetProperty,Double widthPercentage){
        this(text,targetProperty);
        this.setWidthPercentage(widthPercentage);
        
    }
}
