/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials;

import static application.utilities.PathesManager.GLOBAL_CSS_SHEET;
import static application.utilities.PathesManager.getImagePath;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author test
 */
public class IconedLabel {
    
    
    public static HBox plot(String textLabel,String urlImage,boolean ImageBefore){
        
            return new IconedLabel(textLabel,urlImage).plot(ImageBefore);
    
    
                }
    
    
    
    private String textLabel, iconURL;
    
    

    public String getTextLabel() {
        return textLabel;
    }

    public IconedLabel(String textLabel, String iconURL) {
        this.textLabel = textLabel;
        this.iconURL = iconURL;
    }
    

    public void setTextLabel(String textLabel) {
        this.textLabel = textLabel;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
    
    
    
    public HBox plot(boolean imageBefore){
        HBox result=new HBox();
        result.getStylesheets().add(GLOBAL_CSS_SHEET);
                Label lb=new Label(this.textLabel);
        lb.getStyleClass().add("defaultLabel");
        String width=String.valueOf(this.textLabel.length()*15);
        lb.setStyle("-fx-min-width :"+width);
        System.out.println("URL : "+getImagePath(this.iconURL));
        ImageView img;
 try{
      img=new ImageView(getImagePath(this.iconURL));
       img.getStyleClass().add("image");
 }
 catch(Exception e){
     System.out.println(e.getMessage());
     e.printStackTrace();
     img=new ImageView();
     
 }
        if(imageBefore)result.getChildren().addAll(img,lb);else result.getChildren().addAll(lb,img);
        
        return result;
        
    }
    
    
}
