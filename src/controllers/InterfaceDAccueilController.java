/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dbManager.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import static java.util.ResourceBundle.getBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Administrateur;
import model.Employe;
import outils.AlertMessages;
import outils.Constante;
import outils.Outils;
import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author E_DinaBrown
 */
public class InterfaceDAccueilController implements Initializable {

    @FXML
    private JFXTextField nomUtilisateur;

    @FXML
    private JFXPasswordField motDePasse;

    @FXML
    private JFXButton seConnecter;

    private ObservableList<Employe> listeEmp;
    private Stage stage;
    @FXML
    private Label name;
    @FXML
    private Label passw;
    @FXML
    private JFXComboBox<String> langueCombo;
    @FXML
    private Label language;
    private int valTab = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        langueCombo.getItems().add(Constante.FR);
        langueCombo.getItems().add(Constante.EN);
        langueCombo.getSelectionModel().select(SuperMarche.langApp);
        if (SuperMarche.langApp.equals(Constante.FR)) {
            name.setText("Nom D'Utilisateur :");
            passw.setText("Mot De Passe :");
            language.setText("Langue :");
            seConnecter.setText("Se Connecter");
        }
        if (SuperMarche.langApp.equals(Constante.EN)) {
            name.setText("Username :");
            passw.setText("Password :");
            language.setText("Language :");
            seConnecter.setText("Log in");
        }
        langueCombo.valueProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals(Constante.FR)) {
                    name.setText("Nom D'Utilisateur :");
                    passw.setText("Mot De Passe :");
                    language.setText("Langue :");
                    seConnecter.setText("Se Connecter");
                    SuperMarche.langApp = Constante.FR;
                }
                if (newValue.equals(Constante.EN)) {
                    name.setText("Username :");
                    passw.setText("Password :");
                    language.setText("Language :");
                    seConnecter.setText("Log in");
                    SuperMarche.langApp = Constante.EN;
                }
            }
        });
        stage = SuperMarche.stage;
        init();
        Administrateur admin = new Administrateur();
//        chargerEmploye(admin);
        listeEmp = admin.listeEmployes();
        if (SuperMarche.langApp.equals(Constante.EN)) {
            valTab = 1;
        }
        if (SuperMarche.langApp.equals(Constante.FR)) {
            valTab = 0;
        }
    }

    public void init() {
        Outils.chargementPropertiesIndex();
        Manager.ouvertureEntityManager();
    }

    public void chargerEmploye(Administrateur admin) {
        Employe[] emp = new Employe[4];
        String[] grade = {Outils.ADMINISTRATEUR, Outils.AGENTCOMPTABLE, Outils.CAISSIER, Outils.GESTIONNAIRESTOCK};
        String[] noms = {"admin", "esdras", "brown", "loic"};
        for (int i = 0; i < 4; i++) {
            emp[i] = new Employe();
            emp[i].setDateEmbauche(new Date());
            emp[i].setAdresse("Etoug Ebe");
            emp[i].setGrade(grade[i]);
            emp[i].setMotDePasse("admin");
            emp[i].setNom(noms[i]);
            emp[i].setNumeroCni("dfhgfhj");
            emp[i].setTelephone("3213156");
            emp[i].setTelephoneProche("6546546");
            emp[i].genererCode();
            admin.creerEmploye(emp[i]);
        }
    }

    @FXML
    void onConnecter(ActionEvent event) {
        boolean valid = true;

        if (validerChamps()) {
            for (Employe emp : listeEmp) {
                if (emp.equals(nomUtilisateur.getText(), motDePasse.getText())) {
                    valid = false;
                    if (emp.getGrade().equals(Outils.ADMINISTRATEUR)) {
                        chargerVue("/vues/espaceAdministrateur.fxml", "Espace Administrateur", nomUtilisateur.getText(), emp.getId());
                    }
                    if (emp.getGrade().equals(Outils.CAISSIER)) {
                        chargerVue("/vues/caissiere.fxml", "Espace Caissière", nomUtilisateur.getText(), emp.getId());
                    }
                    if (emp.getGrade().equals(Outils.AGENTCOMPTABLE)) {
                        System.out.println("agentComptable");
                        EspaceComptableController.lequel = "12";
                        chargerVue("/vues/espaceComptable.fxml", "Espace Comptable", nomUtilisateur.getText(), emp.getId());
                    }
                    if (emp.getGrade().equals(Outils.GESTIONNAIRESTOCK)) {
                        GestionnaireDeStocksPrincipaleController.lequel = "12";
                        chargerVue("/vues/GestionnaireDeStocksPrincipale.fxml", "Espace Gestionnaire de Stock", nomUtilisateur.getText(), emp.getId());
                    }
                    break;
                }
            }
        }
        if (valid) {
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.alerte(AlertMessages.incorrect[valTab], AlertMessages.incorrectMessage[valTab]);
        }
    }

    private boolean validerChamps() {
        return nomUtilisateur.getText() != null && motDePasse != null;
    }

    public static void chargerRessources(FXMLLoader loader, String chemin) {
        Locale fr = new Locale(SuperMarche.langApp);
        ResourceBundle bundle = getBundle(chemin, fr);
        loader.setResources(bundle);
    }

    private void chargerVue(String vue, String nom, String gest, String id) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(vue));
        try {
            if (nom.equals("Espace Administrateur")) {
                chargerRessources(loader, "langAdmin");
                loader.setControllerFactory(c -> {
                    return new EspaceAdministrateurController(gest, id);
                });
            }
            if (nom.equals("Espace Gestionnaire de Stock")) {
                chargerRessources(loader, "langStock");
                loader.setControllerFactory(c -> {
                    return new GestionnaireDeStocksPrincipaleController(gest, id);
                });
            }
            if (nom.equals("Espace Comptable")) {
                chargerRessources(loader, "langComp");
                loader.setControllerFactory(c -> {
                    return new EspaceComptableController(gest, id);
                });
            }
            if (nom.equals("Espace Caissière")) {
                chargerRessources(loader, "langCaisse");
                loader.setControllerFactory(c -> {
                    return new CaissiereController(gest, id);
                });
            }
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setTitle(nom);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceDAccueilController.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.erreurChargement[valTab]);
        }
    }
    /*public void showAlerteDeconnexion() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vues/alerteDeconnexion.fxml"));
        AnchorPane pane = loader.load();
        alerteDeconnexion con = loader.getController();
        stage = new Stage();
        con.setSup(this);
        Scene scene = new Scene(pane);
        stage.setTitle("Alerte");
        stage.setScene(scene);
        stage.show();
    }*/

}
