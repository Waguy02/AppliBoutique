/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import supermarche.SuperMarche;

/**
 *
 * @author E_DinaBrown
 */
public class alerteDeconnexion {
    private EspaceAdministrateurController es;
    private SuperMarche sup = new SuperMarche();
    private Stage s; 
    @FXML
    private JFXButton oui;

    @FXML
    private JFXButton non;
    @FXML
    void onNonDeconnecter(ActionEvent event) {
        es.stage.close();
    }

    @FXML
    void onOuiDeconnecter(ActionEvent event) throws IOException {
        //Platform.exit();
        sup.getStage().close();
        sup.showAccueil();
        
    }

    public void setSup(SuperMarche aThis) {
        this.sup = aThis;
    }

    
}
