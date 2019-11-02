/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dbManager.Manager;
import factures.PrintFacture;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Commande;
import model.Facture;
import model.AgentComptable;
import model.Employe;
import model.Produit;
import outils.AlertMessages;
import outils.Constante;
import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author E_DinaBrown
 */
public class EspaceComptableController implements Initializable {

    public static String lequel = new String("12");
    private AgentComptable comp;
    @FXML
    private Label nomUser;

    ObservableList<Produit> obsProduit = FXCollections.observableArrayList();

    @FXML
    private JFXButton stokcs;

    private ComboBox<String> actionUtilisateur;

    @FXML
    private JFXButton ventes;

    @FXML
    private JFXButton achat;

    @FXML
    private AnchorPane stockView;

    @FXML
    private TableView<Produit> listeProduits;

    @FXML
    private TableColumn<Produit, String> idProduitColumn;

    @FXML
    private TableColumn<Produit, String> nomProduitColumn;

    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;

    @FXML
    private TableColumn<Produit, Double> prixUnitaireColumn;

    @FXML
    private AnchorPane venteView;

    @FXML
    private TableView<Facture> listeVentes;

    @FXML
    private TableView<Commande> listeCommandes;

    @FXML
    private TableColumn<Facture, String> idFacture;

    @FXML
    private TableColumn<Facture, String> idCaissier;

    @FXML
    private TableColumn<Facture, String> idClient;

    @FXML
    private TableColumn<Facture, String> dateVente;

    @FXML
    private TableColumn<Facture, Double> totalVente;

    @FXML
    private JFXDatePicker dateFinPicker;

    @FXML
    private JFXDatePicker dateDebutPicker;

    @FXML
    private JFXButton rechercher;

    @FXML
    private JFXButton afficheFacture;

    @FXML
    private AnchorPane commandeView;

    @FXML
    private TableColumn<Commande, String> idCommandeColumn;

    @FXML
    private TableColumn<Commande, String> idGestionnaireColumn;

    @FXML
    private TableColumn<Commande, String> dateCommandeColumn;

    @FXML
    private TableColumn<Commande, Float> totalCommandeColumn;

    @FXML
    private JFXButton afficherCommande;

    @FXML
    private JFXDatePicker choisirDebutPickerCommande;

    @FXML
    private JFXDatePicker choisirFinPickerCommande;

    @FXML
    private JFXButton rechercherCommande;
    @FXML
    private JFXButton deconnexion;
    @FXML
    private JFXButton changePassword;

    @FXML
    private ImageView image;

    @FXML
    private ImageView home;

    @FXML
    private ImageView userImage;

    @FXML
    private Label nomUser1;
    private String nom;
    private String id;
    private AgentComptable emp = new AgentComptable();

    private SuperMarche sup = new SuperMarche();
    @FXML
    private JFXTextField search;
    @FXML
    private JFXButton genererCom;
    @FXML
    private JFXButton rapportVente;

    public EspaceComptableController(String nom, String id) {
        this.nom = nom;
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

    @FXML
    void onAfficheFacture(ActionEvent event) {
        afficherLigneFacture();
    }

    @FXML
    void onAfficherCommande(ActionEvent event) {
        afficherLigneCommande();
    }

    @FXML
    void onRechercher(ActionEvent event) throws Throwable {
        LocalDate date1 = dateDebutPicker.getValue();
        LocalDate date2 = dateDebutPicker.getValue();
        Date d1, d2;
        if (date1 == null) {
            d1 = Date.from(new Date(2000, 1, 1).toInstant());
        } else {
            d1 = Date.from(dateDebutPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (date2 == null) {
            d2 = new Date();
        } else {
            d2 = Date.from(dateFinPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        listeVentes.setItems(comp.listeFacture(true, d1, d2));
        //On a changé le modèle au niveau des dates en LocalDate

    }

    @FXML
    void onRechercherCommande(ActionEvent event) {
        LocalDate date1 = choisirDebutPickerCommande.getValue();
        LocalDate date2 = choisirFinPickerCommande.getValue();
        Date d1, d2;
        if (date1 == null) {
            d1 = Date.from(new Date(2000, 1, 1).toInstant());
        } else {
            d1 = Date.from(choisirDebutPickerCommande.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        if (date2 == null) {
            d2 = new Date();
        } else {
            d2 = Date.from(choisirFinPickerCommande.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }
        listeCommandes.setItems(comp.listeCommande(true, d1, d2));
        // On a changé le modèle pour transformé le type Date en LocalDate

    }

    public void recherche() {
        ObservableList<Produit> produits = FXCollections.observableArrayList(this.emp.listeProduits());
        FilteredList<Produit> flProduit = new FilteredList(produits, p -> true);
        listeProduits.setItems(produits);
        /*rechercher.setOnKeyReleased(keyEvent
                -> {
            flProduit.setPredicate(p -> p.nomProperty().getValue().toLowerCase().contains(rechercher.getText().toLowerCase().trim()));//filter table by first name
        });*/
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            flProduit.setPredicate(p -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                Produit prod = (Produit) p;
                if ((prod).getNom().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(flProduit);
        listeProduits.setItems(sortedData);
    }

    @FXML
    void onStocks(ActionEvent event) {
        venteView.setVisible(false);
        commandeView.setVisible(false);
        stockView.setVisible(true);
    }

    @FXML
    void onVentes(ActionEvent event) {
        stockView.setVisible(false);
        commandeView.setVisible(false);
        venteView.setVisible(true);

    }

    @FXML
    void onAchat(ActionEvent event) {
        stockView.setVisible(false);
        venteView.setVisible(false);
        commandeView.setVisible(true);
    }

    @FXML
    void onHome(MouseEvent event) throws IOException {
        sup.showEspaceAdministrateur(this.nom, this.id);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comp = new AgentComptable();
        nomUser.setText(nom);
        stockView.setVisible(true);
        venteView.setVisible(false);
        commandeView.setVisible(false);
        if (lequel.equals("ad")) {
            image.setVisible(false);
            userImage.setVisible(true);
            nomUser.setVisible(false);
            nomUser1.setText(nom);
            nomUser1.setVisible(true);
            home.setVisible(true);
        }
        System.out.println(comp.listeCommande());
        chargerTableProduit();
        chargerTableAchat();
        chargerTableVente();
        recherche();
        listeCommandes.setItems(comp.listeCommande());
        //listeProduits.setItems(comp.listeProduits());
        listeVentes.setItems(comp.listeFacture());
    }

    private void afficherLigneFacture() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/vues/ligneFactureEspaceComptable.fxml"));
            String facture;
            Float totalFacture;
            Facture fac = listeVentes.getSelectionModel().getSelectedItem();
            if (fac != null) {
                facture = fac.getId();
                totalFacture = fac.getMontant();
                System.out.println("Je suis entré");
                loader.setControllerFactory(c -> {
                    System.out.println("Je suis entré");
                    // return new LigneFactureController("1245","1541564");
                    return new LigneFactureController(facture, totalFacture);
                });
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Ligne Facture");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } else {
                int valTab = 0;
                if (SuperMarche.langApp.equals(Constante.EN)) {
                    valTab = 1;
                }
                if (SuperMarche.langApp.equals(Constante.FR)) {
                    valTab = 0;
                }
                Alertes.alerte(AlertMessages.selectElement[valTab], AlertMessages.selectElementMessage[valTab]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherLigneCommande() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(getClass().getResource("/vues/ligneCommandeEspaceComptable.fxml"));
            String commande;
            Float totalCommande;
            Commande com = listeCommandes.getSelectionModel().getSelectedItem();
            if (com != null) {
                commande = com.getId();
                totalCommande = com.getMontant();
                loader.setControllerFactory(c -> {
                    System.out.println("Je suis entré");
                    // return new LigneCommandeController("1245","46545");
                    return new LigneCommandeController(commande, totalCommande);
                });
                Scene scene = new Scene(loader.load());
                Stage stage = new Stage();
                stage.setTitle("Ligne Commande");
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } else {
                int valTab = 0;
                if (SuperMarche.langApp.equals(Constante.EN)) {
                    valTab = 1;
                }
                if (SuperMarche.langApp.equals(Constante.FR)) {
                    valTab = 0;
                }
                Alertes.alerte(AlertMessages.selectElement[valTab], AlertMessages.selectElementMessage[valTab]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void chargerTableVente() {
        idFacture.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCaissier.setCellValueFactory(new PropertyValueFactory<>("employeId"));
        idClient.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        dateVente.setCellValueFactory(new PropertyValueFactory<>("dateEnregistrement"));
        totalVente.setCellValueFactory(new PropertyValueFactory<>("montant"));

    }

    private void chargerTableProduit() {
        idProduitColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProduitColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
    }

    private void chargerTableAchat() {
        idCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idGestionnaireColumn.setCellValueFactory(new PropertyValueFactory<>("employeid"));
        dateCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnregistrement"));
        totalCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
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
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            stage.setTitle(AlertMessages.titreParametre[valTab]);
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
    private void genererCom() {
        LocalDate date1 = choisirDebutPickerCommande.getValue();
        LocalDate date2 = choisirFinPickerCommande.getValue();
        if (date1 == null) {
            date1 = LocalDate.of(2018, Month.JANUARY, 1);
        }
        if (date2 == null) {
            date2 = LocalDate.now();
        }
        String nom = "Rapport Com" + date1.toString() + "#" + date2.toString();
        PrintFacture pf = new PrintFacture();
        pf.ecrireRapportCommande(listeCommandes.getItems(), nom, date1, date2);
    }

    @FXML
    private void genererVente(ActionEvent event) {
        LocalDate date1 = dateDebutPicker.getValue();
        LocalDate date2 = dateFinPicker.getValue();
        if (date1 == null) {
            date1 = LocalDate.of(2018, Month.JANUARY, 1);
        }
        if (date2 == null) {
            date2 = LocalDate.now();
        }
        String nom = "Rapport Ven" + date1.toString() + "#" + date2.toString();
        PrintFacture pf = new PrintFacture();
        pf.ecrireRapportFacture(listeVentes.getItems(), nom, date1, date2);
    }

}
