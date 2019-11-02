/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static application.utilities.Tools.quickAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dbManager.Manager;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Administrateur;
import model.Employe;
import outils.AlertMessages;
import outils.Constante;

import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author E_DinaBrown
 */
public class EspaceAdministrateurController implements Initializable {

    public static Stage stage;
    private SuperMarche sup = new SuperMarche();

    @FXML
    private JFXButton stocks;

    @FXML
    private JFXButton ventes;

    @FXML
    private JFXButton ajouterUtilisateur;

    @FXML
    private JFXButton listeUtilisateurs;

    @FXML
    private AnchorPane listeUtilisateur;

    @FXML
    private TableView<Employe> utilisateurs;

    @FXML
    private TableColumn<Employe, String> nomUtilisateur;

    @FXML
    private TableColumn<Employe, String> poste;

    @FXML
    private TableColumn<Employe, String> IdEmployeColumn;

    @FXML
    private AnchorPane nouvelUtilisateur;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXComboBox<String> poste1;


    @FXML
    private JFXButton deconnexion;

    @FXML
    private JFXButton changePassword;
    @FXML
    private Label nomUser;
    @FXML
    private JFXTextField adresse;

    @FXML
    private JFXTextField telephone;

    @FXML
    private JFXTextField proche;

    @FXML
    private JFXTextField cni;
    private ObservableList<Employe> listeUser = null;
    private String nomU;
    private String id;
    private Administrateur emp = new Administrateur();
    @FXML
    private JFXTextField rechercher;
    
    public EspaceAdministrateurController(String nomU, String id) {
        this.nomU = nomU;
        this.id = id;
        Employe employe = Manager.em.find(Employe.class, id);
        this.emp.setAdresse(employe.getAdresse());
        this.emp.setDateEmbauche(employe.getDateEmbauche());
        this.emp.setGrade(employe.getGrade());
        this.emp.setId(employe.getId());
        this.emp.setMotDePasse(employe.getMotDePasse());
        this.emp.setNom(employe.getNom());
        this.emp.setNumeroCni(employe.getNumeroCni());
        this.emp.setTelephone(employe.getTelephone());
        this.emp.setTelephoneProche(employe.getTelephoneProche());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stage = SuperMarche.stage;
        nomUser.setText(nomU);
        poste1.getItems().addAll("CAISSIER", "AGENTCOMPTABLE", "GESTIONNAIRESTOCK","ADMINISTRATEUR");
        chargerTableUtilisateur();
        recherche();
    }

    public SuperMarche getSup() {
        return sup;
    }

    public void setSup(SuperMarche sup) {
        this.sup = sup;
    }

    public void recherche() {
        listeUser = FXCollections.observableArrayList(this.emp.listeEmployes());
        FilteredList<Employe> flProduit = new FilteredList(listeUser, p -> true);
        utilisateurs.setItems(listeUser);
        /*rechercher.setOnKeyReleased(keyEvent
                -> {
            flProduit.setPredicate(p -> p.nomProperty().getValue().toLowerCase().contains(rechercher.getText().toLowerCase().trim()));//filter table by first name
        });*/
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            flProduit.setPredicate(p -> {
                if (newValue== null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                Employe prod = (Employe)p;
                if(prod.getNom().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getId().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
        });
        SortedList<Employe> sortedData = new SortedList<>(flProduit);
        utilisateurs.setItems(sortedData);
    }
    @FXML
    void onAjouterUtilisateur(ActionEvent event) {
        listeUtilisateur.setVisible(false);
        nouvelUtilisateur.setVisible(true);
        poste1.getItems().clear();
        poste1.getItems().addAll("CAISSIER", "AGENTCOMPTABLE", "GESTIONNAIRESTOCK","ADMINISTRATEUR");
    }

    @FXML
    void onListeUtilisateurs(ActionEvent event) {
        listeUtilisateur.setVisible(true);
        nouvelUtilisateur.setVisible(false);
        //chargerTableUtilisateur();
        //utilisateurs.setItems(emp.listeEmployes());
        rechercher.setText("");
    }

    @FXML
    void onStocks(ActionEvent event) throws IOException {
        GestionnaireDeStocksPrincipaleController.lequel="ad";
        chargerVue("/vues/GestionnaireDeStocksPrincipale.fxml", "Espace Gestionnaire de Stock", nomU, id);
    }

    @FXML
    void Enregistrement(ActionEvent event) {
        
        if(nom.getText().equals("")||poste1.getValue().equals("")){
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.information(AlertMessages.information[valTab], AlertMessages.renseigneNom[valTab]);
        }
        else{
            Employe emplo=new Employe();
            emplo.genererCode();
            emplo.setTelephone(telephone.getText());
            emplo.setAdresse(adresse.getText());
            emplo.setGrade(poste1.getValue());
            emplo.setNom(nom.getText());
            emplo.setTelephoneProche(proche.getText());
            emplo.setNumeroCni(cni.getText());
            emplo.setMotDePasse("supermarche");
            emplo.setDateEmbauche(new Date());
            System.out.print(emplo);
            emp.creerEmploye(emplo);
            nom.setText("");
            cni.setText("");
            adresse.setText("");
            telephone.setText("");
            proche.setText("");
            
            quickAlert(AlertType.INFORMATION,"Utilisateur enregistré avec succès");
            listeUser.add(emplo);
        }
    }
    @FXML
    void onVentes(ActionEvent event) throws IOException {
        EspaceComptableController.lequel="ad";
        chargerVue("/vues/espaceComptable.fxml", "Espace Comptable", nomU, id);
    }

    @FXML
    void onChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vues/validationPage.fxml"));
            InterfaceDAccueilController.chargerRessources(loader, "langValider");
            Stage stage = new Stage();
            loader.setControllerFactory(c -> {
                return new ValidationPageController(stage, emp);
            });
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setTitle("Paramètres");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceAdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.erreurChargement[valTab]);
        }
    }

    @FXML
    void onDeconnexion(ActionEvent event) throws IOException {
        int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
        if (Alertes.confirmation(AlertMessages.confirmation[valTab], AlertMessages.demandeQuitter[valTab])) {
            Manager.fermertureEntityManager();
            sup.showAccueil();
        }
    }

    private void chargerTableUtilisateur() {
        IdEmployeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        poste.setCellValueFactory(new PropertyValueFactory<>("grade"));
    }

    private void chargerVue(String vue, String nom, String gest, String id) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(vue));
        try {
            if (nom.equals("Espace Administrateur")) {
                GestionnaireDeStocksPrincipaleController con = loader.getController();
                InterfaceDAccueilController.chargerRessources(loader, "langStock");
                loader.setControllerFactory(c -> {
                    return new EspaceAdministrateurController(gest, id);
                });
            }
            if (nom.equals("Espace Gestionnaire de Stock")) {
                InterfaceDAccueilController.chargerRessources(loader, "langStock");
                loader.setControllerFactory(c -> {
                    return new GestionnaireDeStocksPrincipaleController(gest, id);
                });
            }
            if (nom.equals("Espace Comptable")) {
                InterfaceDAccueilController.chargerRessources(loader, "langComp");
                loader.setControllerFactory(c -> {
                    return new EspaceComptableController(gest, id);
                });
            }
            if (nom.equals("Espace Caissière")) {
                InterfaceDAccueilController.chargerRessources(loader, "langCaisse");
                loader.setControllerFactory(c -> {
                    return new CaissiereController(gest, id);
                });
            }
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            stage.setTitle(nom);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceDAccueilController.class.getName()).log(Level.SEVERE, null, ex);
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.erreurChargement[valTab]);
        }
    }
}
