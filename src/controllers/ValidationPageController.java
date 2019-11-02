/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.Employe;
import outils.AlertMessages;
import outils.Constante;
import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class ValidationPageController implements Initializable {

    private Stage stage;
    @FXML
    private JFXTextField newP;
    @FXML
    private JFXTextField oldP;
    private Employe emp;

    public ValidationPageController(Stage stage, Employe e) {
        this.stage = stage;
        emp = e;
    }

    @FXML
    private JFXButton validateButton;
    @FXML
    private JFXButton cancelButton;
    private int valTab = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (SuperMarche.langApp.equals(Constante.EN)) {
            valTab = 1;
        }
        if (SuperMarche.langApp.equals(Constante.FR)) {
            valTab = 0;
        }
    }

    @FXML
    private void changeSettings(ActionEvent event) {
        if (emp.getMotDePasse().equals(oldP.getText())) {
            if (emp.modifierMotDePasse(newP.getText())) {
                
                Alertes.information(AlertMessages.succes[valTab], AlertMessages.modifPassword[valTab]);
                stage.close();
            } else {
                Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.echecOp[valTab]);
            }
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
        stage.close();
    }

}
