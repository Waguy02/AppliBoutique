/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnement;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.logging.Level;

import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import supermarche.SuperMarche;

/**
 *
 * @author ESDRAS
 */
public class LaunchApp extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        File fichier = new File(Constantes.DIRECTORY);
        if (!fichier.exists()) {
            load(stage);
        } else {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
            Caracteristiques car = (Caracteristiques) ois.readObject();
            Date d = new Date();
            long time = (car.getDateFin().getTime() - d.getTime());
            if (time >= 0) {
                SuperMarche app = new SuperMarche();
                app.start(stage);
            } else {
                load(stage);
            }
        }
    }
    private void load(Stage stage) {
        Abonnement app = new Abonnement();
        try {
            app.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(LaunchApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
