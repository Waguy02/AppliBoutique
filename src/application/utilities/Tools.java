package application.utilities;

import Init.Init;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;






public class Tools {
  





    public static void quickAlert(Alert.AlertType typeAlerte,String title, String content){

        Alert alert =new Alert(typeAlerte);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public static void quickAlert(Alert.AlertType typeAlerte, String content){
        Alert alert =new Alert(typeAlerte);

        alert.setContentText(content);
        alert.showAndWait();
    }



    public static void hide(Node... elements) {
        for (Node noeud : elements) noeud.setVisible(false);

    }

    public static void disable(Node... elements) {
        for (Node noeud : elements) {
            noeud.setDisable(true);


        }


    }

    public static void enable(Node... elements) {
        for (Node noeud : elements) {
            noeud.setDisable(false);


        }


    }


    public static void blur(Node... elements) {
        for (Node noeud : elements) {
            noeud.setDisable(true);
            noeud.setEffect(new GaussianBlur());

        }


    }

    public static void unBlur(Node... elements) {
        for (Node noeud : elements) {
            noeud.setDisable(false);
            noeud.setEffect(null);

        }


    }

    public static void clear(Node... elements) {
        for (Node noeud : elements) {
            if (noeud.getClass().getSimpleName().equalsIgnoreCase("JFXTextField")) ((JFXTextField) noeud).clear();
            if (noeud.getClass().getSimpleName().equalsIgnoreCase("JFXTextArea")) ((JFXTextArea) noeud).clear();
        }

    }

    public static void clear(JFXComboBox... elements) {
        for (JFXComboBox noeud : elements) noeud.getSelectionModel().clearSelection();

    }

    public static void clear(JFXDatePicker... elements) {
        for (JFXDatePicker noeud : elements) noeud.getEditor().clear();
    }

    public static void show(Node... elements) {

        for (Node noeud : elements) noeud.setVisible(true);
    }

    // cette methode affiche et cache les vues de facon dynamique avec la tableView
    static Boolean boom = true;

    public static void dynamic(TableView node1, AnchorPane node2, int min, int max) {
        //this.visible = !visible;
        if (boom) {

            node1.setMaxWidth(min);
            node1.maxWidth(min);
            node1.setMinWidth(min);
            node1.minWidth(min);
            node2.setVisible(true);
            boom = !boom;

        } else {
            node1.setMaxWidth(max);
            node1.maxWidth(max);
            node1.setMinWidth(max);
            node1.minWidth(max);
            node2.setVisible(false);
            boom = !boom;

        }


    }

   



   

    public static void BindCol(TableColumn col, String propertyName){

        col.setCellValueFactory(new PropertyValueFactory<>(propertyName));

    }









}
