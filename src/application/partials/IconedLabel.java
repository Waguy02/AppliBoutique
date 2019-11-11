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

    public static HBox plot(String textLabel, String urlImage, boolean ImageBefore) {

        return new IconedLabel(textLabel, urlImage).plot(ImageBefore);

    }

    public static HBox plot(String textLabel, String urlImage, boolean ImageBefore, String styleClass,double imgX,double imgY ) {

        IconedLabel il= new IconedLabel(textLabel, urlImage);
        
        il.getResult().getStyleClass().addAll(styleClass.split(" "));
        il.setImgX(imgX);
        il.setImgY(imgY);
        return il.plot(ImageBefore);
    }

    
    
    
    
    
    
      public static HBox plot(String textLabel, String urlImage, boolean ImageBefore,double imgX,double imgY ) {
  
          
          return plot(textLabel,urlImage, ImageBefore,"",imgX,imgY);
    }
      
      
        public static HBox plot(String textLabel, String urlImage, boolean ImageBefore,String styleClass ) {

            IconedLabel il= new IconedLabel(textLabel, urlImage);
        
        il.getResult().getStyleClass().addAll(styleClass.split(" "));
        return il.plot(ImageBefore);
        
    }
    
    
    
    
    
    
    
    
    public HBox getResult() {
        return result;
    }

    public void setResult(HBox result) {
        this.result = result;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Label getLb() {
        return lb;
    }

    public void setLb(Label lb) {
        this.lb = lb;
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

   private HBox result = new HBox();
    ImageView img;
    Label lb;
    
    
    private Double imgX=40.0;
    private Double imgY=40.0;

    public Double getImgX() {
        return imgX;
    }

    public void setImgX(Double imgX) {
        this.imgX = imgX;
    }

    public Double getImgY() {
        return imgY;
    }

    public void setImgY(Double imgY) {
        this.imgY = imgY;
    }
    
    
    
    public HBox plot(boolean imageBefore) {

        result.getStylesheets().add(GLOBAL_CSS_SHEET);
        result.getStyleClass().add("defaultIconed");
         lb = new Label(this.textLabel);
        lb.getStyleClass().add("primary-label");
        String width = String.valueOf(this.textLabel.length() * 15);
        lb.setStyle("-fx-min-width :" + width);
        //System.out.println("URL : "+getImagePath(this.iconURL));

        try {
            img = new ImageView(getImagePath(this.iconURL));
            img.setFitWidth(this.imgX);
            img.setFitHeight(this.imgX);

            img.getStyleClass().add("image");
        } catch (Exception e) {
            System.out.println("Erreur de chargement d'image " + e.getMessage());
            img = new ImageView();

        }
        if (imageBefore) {
            result.getChildren().addAll(img, lb);
        } else {
            result.getChildren().addAll(lb, img);
        }

        return result;

    }

}
