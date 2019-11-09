/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities.interfaces;

import javafx.scene.layout.Region;

/**
 *
 * @author test
 */
public class ViewDimensionner {






public static void bindSizes(Region child,Region parent, double widthPercentage, double heightPercentage){
    
    child.minWidthProperty().bind(parent.widthProperty().multiply(widthPercentage));
    child.maxWidthProperty().bind(parent.widthProperty().multiply(widthPercentage));
    
    child.minHeightProperty().bind(parent.heightProperty().multiply(heightPercentage));
    child.maxHeightProperty().bind(parent.heightProperty().multiply(heightPercentage));
}







    
}
