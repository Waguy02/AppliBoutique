package controllers;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ESDRAS
 */
public class Alertes {
    public static void alerte(String entete,String mess){
        Alert alerte=new Alert(Alert.AlertType.WARNING);
        alerte.setTitle(entete);
        alerte.setHeaderText(null);
        alerte.setContentText(mess);
        alerte.showAndWait();
    }
    public static void information(String entete, String mess) {
        Alert alerte=new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle(entete);
        alerte.setHeaderText(null);
        alerte.setContentText(mess);
        alerte.showAndWait();
    }
    public static String textInput(String titre, String entete, String mess, String prompt) {
        TextInputDialog inDialog = new TextInputDialog(prompt);
        inDialog.setTitle(titre);
        inDialog.setHeaderText(entete);
        inDialog.setContentText(mess);
        Optional<String> textIn = inDialog.showAndWait();
        if(textIn.isPresent()) {
            return textIn.get();
        }
        return null;
    }
    public static boolean confirmation(String entete, String mess) {
        Alert dialogC = new Alert(AlertType.CONFIRMATION);
        dialogC.setTitle(entete);
        dialogC.setHeaderText(null);
        dialogC.setContentText(mess);
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK){
            return true;
        }
        return false;
        
    }
}
