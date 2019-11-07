/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.table;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author test
 */
public class CustomActionColumn<T,S> extends CustomColumn {
   
    

    
    public Double getWidthPercentage() {
        return widthPercentage;
    }

    public void setWidthPercentage(Double widthPercentage) {
        this.widthPercentage = widthPercentage;
    }


    public CustomActionColumn(String text, Double widthPercentage) {
        super(text);
        this.widthPercentage = widthPercentage;
       
    }
    
    
        
    
    
    
    
    
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
    
}
