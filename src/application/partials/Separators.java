/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

/**
 *
 * @author test
 */
public class Separators {
    
    
    public static Separator formSeparatorH(){
        Separator result =new Separator(Orientation.HORIZONTAL);
        result.autosize();
        
        result.setMinHeight(15);
        return result;
    }
    
    
    public static Separator formSeparatorV(){
        Separator result =new Separator(Orientation.VERTICAL);
        result.autosize();
        
        result.setMinWidth(15);
        return result;
    }
    
    public static Separator  labelSeparatorV(){
        Separator result =new Separator(Orientation.VERTICAL);
        result.autosize();
        
        result.setMinWidth(5);
        return result;
    }
    
}
