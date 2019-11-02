/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnement;

import controllers.Alertes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import supermarche.SuperMarche;


/**
 * FXML Controller class
 *
 * @author ESDRAS
 */
public class AbonnementController implements Initializable {

    @FXML
    private Label tempsRestant;
    @FXML
    private Label numeroAbo;
    @FXML
    private TextField cle1;
    @FXML
    private TextField cle2;
    @FXML
    private TextField cle3;
    @FXML
    private TextField cle4;
    @FXML
    private TextField cle5;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<String> choixAbonnement;
    private int tmp = 0;
    @FXML
    private Label dateFin;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chargerFichier();
        ObservableList<String> options = FXCollections.observableArrayList("Mensuel", "Trimestriel", "Semestriel", "Annuel");
        choixAbonnement.getItems().setAll(options);
        choixAbonnement.getSelectionModel().select(0);
    }

    public void chargerFichier() {
        File fichier = new File(Constantes.DIRECTORY);
        if (fichier.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
                Caracteristiques car = (Caracteristiques) ois.readObject();
                Date d = new Date();
                long time = (car.getDateFin().getTime() - d.getTime()) / (86400000);
                if (time < 0)
                    time = 0;
                tempsRestant.setText("" + time);
                numeroAbo.setText("" + car.getNumeroAbonnement());
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                dateFin.setText(sdf.format(car.getDateFin()));
            } catch (IOException ex) {
                Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                Alertes.alerte("Erreur", "Problème lors du chargement du module, veuillez contacter les concepteurs");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                Alertes.alerte("Erreur", "Problème lors du chargement du module, veuillez contacter les concepteurs");
            }
        } else {
            tempsRestant.setText("" + 0);
            numeroAbo.setText("" + 1);
            dateFin.setText("Aucune Date");
        }
    }
    
    @FXML
    private void valider(ActionEvent event) {
        if (verifierCle()) {
            int a = Integer.parseInt(tempsRestant.getText());
            int b = (a > 0) ? a : 0;
            //Serialisons notre fichier
            File fichier = new File(Constantes.DIRECTORY);
            int choix = choixAbonnement.getSelectionModel().getSelectedIndex();
            switch (choix) {
                case 0:
                    b = b + 30;
                    break;
                case 1:
                    b = b + 90;
                    break;
                case 2:
                    b = b + 182;
                    break;
                case 3:
                    b = b + 365;
                    break;
                default:
                    b = -1;
            }
            if (b != -1) {
                Calendar calendar = Calendar.getInstance(), calendar2 = Calendar.getInstance();
                calendar.add(Calendar.DATE, b);
                Date dateFinD = calendar.getTime();
                Caracteristiques car = new Caracteristiques(calendar2.getTime(), dateFinD, Integer.parseInt(numeroAbo.getText()) + 1);
                try {
                    ObjectOutputStream oosCarac = new ObjectOutputStream(new FileOutputStream(fichier));
                    oosCarac.writeObject(car);
                    oosCarac.close();
                    SuperMarche app = new SuperMarche();
                    vider();
                    Stage stage = new Stage();
                    app.start(stage);
                    chargerFichier();
                    
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(AbonnementController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                GenerateurCle.alerte("Erreur", "Problème lors de l'abonnement, Veuillez réessayer ou alors contacter les concepteurs du logiciel");
            }

        } else {
            GenerateurCle.alerte("Clé invalide", "Clé invalide, Veuillez réessayer ou alors contacter les concepteurs du logiciel");
        }
    }

    private void vider() {
        cle1.setText("");
        cle2.setText("");
        cle3.setText("");
        cle4.setText("");
        cle5.setText("");
        valider.setDisable(true);
    }

    private boolean verifierCle() {
        try {
            int ac = choixAbonnement.getSelectionModel().getSelectedIndex();
            String[] liste = GenerateurCle.generer(Integer.parseInt(numeroAbo.getText()), ac);
            if (cle1.getText().equals(liste[0]) && cle2.getText().equals(liste[1]) && cle3.getText().equals(liste[2])
                    && cle4.getText().equals(liste[3]) && cle5.getText().equals(liste[4])) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            GenerateurCle.alerte("Problème lors de la vérification", "Impossible de vérifier la validité de votre clé, "
                    + "veuillez remplir correctement les champs et réessayer");
        }
        return false;
    }
}
