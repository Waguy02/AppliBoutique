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
    
    public static final double TINY_HEIGHT=5;
    public static final double NORMAL_HEIGHT=15;
    public static final double LONG_HEIGHT=25;
    
    
    public static final double LONG_WIDTH=120;
    public static final double NORMAL_WIDTH=30;
    public static final double TINY_WIDTH=15;
    
    public static Separator formSeparatorH(){
        Separator result =new Separator(Orientation.HORIZONTAL);
      
        
        result.setMinHeight(NORMAL_HEIGHT);
        return result;
    }
    
    
     public static Separator formSeparatorH(double height){
        Separator result =new Separator(Orientation.HORIZONTAL);
      
        
        result.setMinHeight(height);
        return result;
    }
     
     public static Separator maxSeparatorV(){
         Separator result=new Separator(Orientation.VERTICAL);
         result.setMinWidth(0);
         result.setPrefWidth(100000);
         
         return result;
         
     }
     
     
     public static Separator maxSeparatorH(){
         
         Separator result=new Separator(Orientation.HORIZONTAL);
         result.setPrefHeight(10000);
         return result;
     }
    
    
    public static Separator formSeparatorV(){
        Separator result =new Separator(Orientation.VERTICAL);
      
        
        result.setMinWidth(TINY_WIDTH);
        return result;
    }
    
     public static Separator formSeparatorV(double width){
        Separator result =new Separator(Orientation.VERTICAL);
      
         result.setMinWidth(width);
        return result;
    }
    public static Separator  labelSeparatorV(){
        Separator result =new Separator(Orientation.VERTICAL);
        result.autosize();
        
        result.setMinWidth(5);
        return result;
    }
    
}
