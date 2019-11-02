/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Client;
import outils.Outils;
import dbManager.Manager;
import java.util.Date;
import model.Employe;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class Main extends Application {

    @Override
    public void init() {
        Outils.chargementPropertiesIndex();
        Manager.ouvertureEntityManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLAccueil.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
//        Employe emp = new Employe();
//        emp.genererCode();
//        emp.setAdresse("default");
//        emp.setDateEmbauche(new Date());
//        emp.setGrade(Outils.ADMINISTRATEUR);
//        emp.setMotDePasse("admin");
//        emp.setNom("admin");
//        emp.setNumeroCni("0000000000");
//        emp.setTelephone("23700000000");
//        emp.setTelephoneProche("23700000000");
//        Manager.em.getTransaction().begin();
//        Manager.em.persist(emp);
//        Manager.em.getTransaction().commit();
    }

    @Override
    public void stop() {
        Outils.enregistrementPropertiesIndex();
        Manager.fermertureEntityManager();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
