/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static application.utilities.Tools.blur;
import static application.utilities.Tools.hide;
import static application.utilities.Tools.show;
import static application.utilities.Tools.unBlur;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dbManager.Manager;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Categorie;
import model.Commande;
import model.Employe;
import model.Fournisseur;
import model.GestionnaireStock;
import model.LigneCommande;
import model.LigneCommandeId;
import model.Produit;
import outils.AlertMessages;
import outils.Constante;

import supermarche.SuperMarche;

/**
 * FXML Controller class
 *
 * @author E_DinaBrown
 */
public class GestionnaireDeStocksPrincipaleController implements Initializable {

    public static String lequel = new String("12");
    private SuperMarche sup = new SuperMarche();
    
    
    @FXML
    private TableView<Produit> listeProduits;

    @FXML
    private TableColumn<Produit, String> idProduitColumn;

    @FXML
    private TableColumn<Produit, String> nomProduitColumn;

    @FXML
    private TableColumn<Produit, Integer> quantiteColumn;

    @FXML
    private TableColumn<Produit, Integer> prixUnitaireColumn;

    @FXML
    private TableColumn<Produit, Date> DatePéremptionColumn;

    @FXML
    private JFXButton valider;

    @FXML
    private AnchorPane ajoutQuantitéPane;

    @FXML
    private AnchorPane ajoutProduitPane;

    @FXML
    private JFXTextField nomProduit;

    @FXML
    private JFXTextField quantite;

    @FXML
    private JFXTextField prixUnitaire;

    private JFXDatePicker DatePéremption;

    @FXML
    private JFXButton deconnexion;

    @FXML
    private JFXButton changePassword;
    
    @FXML
    private JFXComboBox<String> nomProduitCombo;
    
    @FXML
    private JFXComboBox<String> listeFournisseurCombo;

    @FXML
    private JFXDatePicker datePeremption;
    @FXML
    private JFXButton ajouterFournisseur;
    @FXML
    private JFXTextField quantite1;
    @FXML
    private JFXComboBox<String> nomProduits;
    @FXML
    private JFXButton ajouterProduitFournisseur;
    @FXML
    private JFXTextArea description;
    @FXML
    private TableColumn<Produit, String> descriptionColumn;
    @FXML
    private Label nomUser;
    @FXML
    private ImageView image;

    @FXML
    private ImageView userImage;

    @FXML
    private Label nomUser1;
    private ObservableList<String> listeProd;
    @FXML
    private ImageView home;
    private ObservableList<Produit> listeProduit = null;
    private String nom;
    private String id;
    private GestionnaireStock emp = new GestionnaireStock();
    @FXML
    private JFXTextField rechercher;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private JFXComboBox<String> categorieCombo;
    @FXML
    private JFXButton ajouterCategorie;
    private int valTab = 0;
    @FXML
    private Pane tablePane;
    @FXML
    private TableColumn<Produit, Integer> prixTotalColumn;
    @FXML
    private JFXButton ajouter;
    @FXML
    private JFXButton annuler;
    
    
    private BooleanProperty isAddingProduit=new SimpleBooleanProperty(false);
    /**
     * Initializes the controller class.
     */
    public GestionnaireDeStocksPrincipaleController(String nom, String id) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (SuperMarche.langApp.equals(Constante.EN)) {
            valTab = 1;
        }
        if (SuperMarche.langApp.equals(Constante.FR)) {
            valTab = 0;
        }
        nomUser.setText(nom);
        ArrayList<String> liste = new ArrayList<String>();
        for (Object obj : emp.getMapProduits().values()) {
            liste.add(((Produit) obj).toString());
        }
        listeProd = FXCollections.observableArrayList(liste);
        
        nomProduitCombo.setItems(listeProd);
        
        if (lequel.equals("ad")) {
            image.setVisible(false);
            userImage.setVisible(true);
            nomUser.setVisible(false);
            nomUser1.setText(nom);
            nomUser1.setVisible(true);
            home.setVisible(true);
        }
        nomProduitCombo.getItems().add("Autre");
        nomProduitCombo.getSelectionModel().select(0);
        if (nomProduitCombo.getItems().size() > 1) {
            String code = nomProduitCombo.getSelectionModel().getSelectedItem().split(" ")[0];
            Produit produit = Manager.em.find(Produit.class, code);
            prixUnitaire.setEditable(false);
            description.setEditable(false);
            categorie.setEditable(false);
            remplirFournisseurAdapte(produit);
            remplirCategorieAdapte(produit);
        }
        if (nomProduitCombo.getItems().get(0).equals("Autre")) {
            nomProduit.setDisable(false);
            prixUnitaire.setEditable(true);
            prixUnitaire.setText("");
            description.setEditable(true);
            description.setText("");
            datePeremption.setEditable(true);
            remplirFournisseur();
            remplirCategorie();
        }
        nomProduitCombo.valueProperty().addListener(new javafx.beans.value.ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Autre")) {
                    nomProduit.setDisable(false);
                    prixUnitaire.setEditable(true);
                    prixUnitaire.setText("");
                    description.setEditable(true);
                    description.setText("");
                    datePeremption.setEditable(true);
                    remplirFournisseur();
                    remplirCategorie();
                } else {
                    nomProduit.setDisable(true);
                    String code = nomProduitCombo.getSelectionModel().getSelectedItem().split(" ")[0];
                    Produit prod = Manager.em.find(Produit.class, code);
                    if (prod != null) {
                        prixUnitaire.setText("" + prod.getPrixUnitaire());
                        prixUnitaire.setEditable(false);
                        description.setText(prod.getDescription());
                        description.setEditable(false);
                        if (prod.getDatePeremption() != null) {
                            datePeremption.setValue(Instant.ofEpochMilli(prod.getDatePeremption().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                        }

                        datePeremption.setEditable(false);
                        categorie.setEditable(false);
                        //on met juste l'ensemble des fournisseurs disponible pour le produit
                        remplirFournisseurAdapte(prod);
                        remplirCategorieAdapte(prod);
                    }
                }
            }
        });
        chargerTableProduit();
        //remplirTableProduit();
        recherche();
        initAddAnchor();
    }

    
    
    
    
    
    
    public void initAddAnchor(){
        
        isAddingProduit.addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue==true){
                
                blur(tablePane);show(ajoutProduitPane);
            }
            else{
                unBlur(tablePane);hide(ajoutProduitPane);
                
            }
            }
        
        
        
        });
        
        ajouter.setOnAction((event)->{
        
            isAddingProduit.set(true);
        
        
        });
        
        
       annuler.setOnAction((event)->{
           
       isAddingProduit.set(false);
       });
        
    }
    
    
    public void recherche() {
        listeProduit = FXCollections.observableArrayList(this.emp.getMapProduits().values());
        FilteredList<Produit> flProduit = new FilteredList(listeProduit, p -> true);
        listeProduits.setItems(listeProduit);
        /*rechercher.setOnKeyReleased(keyEvent
                -> {
            flProduit.setPredicate(p -> p.nomProperty().getValue().toLowerCase().contains(rechercher.getText().toLowerCase().trim()));//filter table by first name
        });*/
        rechercher.textProperty().addListener((observable, oldValue, newValue) -> {
            flProduit.setPredicate(p -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                Produit prod = (Produit) p;
                if ((prod).getNom().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getId().toLowerCase().contains(lowerCaseFilter)
                        || (prod).getCategorie().getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Produit> sortedData = new SortedList<>(flProduit);
        listeProduits.setItems(sortedData);
    }

    @FXML
    void onValider(ActionEvent event) {
        //ajouter dans la liste des produits
        String nomProd;
        if (!nomProduit.isDisable()) {
            //mettre a jour l'article
            nomProd = nomProduit.getText();
            if (validerChamps(nomProd)) {
                Produit prod = new Produit();
                if (datePeremption.getValue() != null) {
                    prod.setDatePeremption(java.sql.Date.valueOf(datePeremption.getValue()));
                }

                if (description.getText() != null) {
                    prod.setDescription(description.getText());
                } else {
                    prod.setDescription(" ");
                }
                prod.setNom(nomProd);
                prod.setPrixUnitaire(Integer.valueOf(prixUnitaire.getText()));
                prod.setCategorie(emp.selectCategorie(categorieCombo.getSelectionModel().getSelectedItem()));
                //ajouter l'article
                emp.ajouterIdentiteProduit(prod);
                prod.setQuantite(Integer.valueOf(quantite.getText()));
                String codeFournisseur = listeFournisseurCombo.getSelectionModel().getSelectedItem().split(" ")[0];
                Commande commande = new Commande();
                commande.genererCode();
                commande.setDateEnregistrement(new Date());
                commande.setEmployeid(emp.getId());
                LigneCommandeId ligneId = new LigneCommandeId();
                ligneId.setCommandeid(commande.getId());
                ligneId.setProduitid(prod.getId());
                LigneCommande ligneCommande = new LigneCommande();
                ligneCommande.setLigneCommandeId(ligneId);
                ligneCommande.setFournisseurid(codeFournisseur);
                ligneCommande.setPrixUnitaire(prod.getPrixUnitaire());
                ligneCommande.setQuantite(prod.getQuantite());
                try {
                    emp.EffectuerAchat(commande, ligneCommande);
                    emp.ajouterProduitFournisseur(codeFournisseur, prod.getId());
                    listeProduit.add(prod);
                    listeProd.add(prod.toString());
                    Alertes.information(AlertMessages.succes[valTab], AlertMessages.productAdd[valTab]);
                       isAddingProduit.set(false);
                } catch (Throwable ex) {
                    Logger.getLogger(GestionnaireDeStocksPrincipaleController.class.getName()).log(Level.SEVERE, null, ex);
                    Alertes.alerte(AlertMessages.echec[valTab], AlertMessages.productNotAdd[valTab]);
                }
            } else {
                Alertes.alerte(AlertMessages.champIncomplet[valTab], AlertMessages.remplirChamps[valTab]);
            }
        } else {
            String code = nomProduitCombo.getSelectionModel().getSelectedItem().split(" ")[0];
            if (validerChamps(code)) {
                Produit prod = Manager.em.find(Produit.class, code);
                String codeFournisseur = listeFournisseurCombo.getSelectionModel().getSelectedItem().split(" ")[0];
                Commande commande = new Commande();
                commande.genererCode();
                commande.setDateEnregistrement(new Date());
                commande.setEmployeid(emp.getId());
                LigneCommandeId ligneId = new LigneCommandeId();
                ligneId.setCommandeid(commande.getId());
                ligneId.setProduitid(code);
                LigneCommande ligneCommande = new LigneCommande();
                ligneCommande.setLigneCommandeId(ligneId);
                ligneCommande.setFournisseurid(codeFournisseur);
                ligneCommande.setPrixUnitaire(prod.getPrixUnitaire());
                ligneCommande.setQuantite(Integer.valueOf(quantite.getText()));
                try {
                    emp.EffectuerAchat(commande, ligneCommande);
                    //remplirTableProduit();
                    Alertes.information(AlertMessages.succes[valTab], AlertMessages.majProd[valTab]);
                    isAddingProduit.set(false);
                } catch (Throwable ex) {
                    Logger.getLogger(GestionnaireDeStocksPrincipaleController.class.getName()).log(Level.SEVERE, null, ex);
                    Alertes.alerte(AlertMessages.echec[valTab], AlertMessages.notMajProd[valTab]);
                }
            } else {
                Alertes.alerte(AlertMessages.champIncomplet[valTab], AlertMessages.remplirChamps[valTab]);
            }
        }
    }

    private void chargerTableProduit() {
        idProduitColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomProduitColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixUnitaireColumn.setCellValueFactory(new PropertyValueFactory<>("prixUnitaire"));
        DatePéremptionColumn.setCellValueFactory(new PropertyValueFactory<>("datePeremption"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        //colEtat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
    }

    private void remplirTableProduit() {
        listeProduits.getItems().clear();
        listeProduits.getItems().addAll(emp.getMapProduits().values());
    }

    @FXML
    void onHome(MouseEvent event) throws IOException {
        sup.showEspaceAdministrateur(this.nom, this.id);
    }

    @FXML
    void onDeconnexion(ActionEvent event) throws IOException {
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
            stage.setTitle(AlertMessages.titreParametre[valTab]);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceAdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.erreurChargement[valTab]);
        }
    }

    public SuperMarche getSup() {
        return sup;
    }

    public void setSup(SuperMarche sup) {
        this.sup = sup;
    }

    private void remplirFournisseur() {
        //afficher tous les fournisseurs de la BD
        List<Fournisseur> listfournisseur = Manager.em.createNamedQuery("Fournisseur.findAll", Fournisseur.class).getResultList();
        //prendre la liste de tous les fournisseurs de la BD
        listeFournisseurCombo.getItems().clear();
        for (Fournisseur four : listfournisseur) {
            listeFournisseurCombo.getItems().add(four.toString());
        }
        listeFournisseurCombo.getSelectionModel().select(0);
    }

    private void remplirFournisseurAdapte(Produit prod) {
        //afficher tous les fournisseurs ayant le produit prod
        ObservableList<String> listeFournisseurs = prod.listeIdFournisseur();
        System.out.println(prod);
        for (String s : listeFournisseurs) {
            System.out.println("\t" + s);
        }
        //prendre la liste de tous les fournisseurs de la BD qui ont ce produit
        listeFournisseurCombo.setItems(listeFournisseurs);
        listeFournisseurCombo.getSelectionModel().select(0);

    }

    //remplir les categories
    private void remplirCategorie() {
        //afficher tous les categories de la BD
        List<Categorie> listCategorie = Manager.em.createNamedQuery("Categorie.findAll", Categorie.class).getResultList();
        //prendre la liste de tous les fournisseurs de la BD
        categorieCombo.getItems().clear();
        for (Categorie cat : listCategorie) {
            categorieCombo.getItems().add(cat.getId());
        }
        categorieCombo.getSelectionModel().select(0);
    }

    private void remplirCategorieAdapte(Produit prod) {
        ObservableList<String> listeCategories = FXCollections.observableArrayList();
        listeCategories.add(prod.getCategorie().getId());
        categorieCombo.setItems(listeCategories);
        categorieCombo.getSelectionModel().select(0);
    }

    private boolean validerChamps(String nomProduit) {
        return (!nomProduit.equals("") && listeFournisseurCombo.getSelectionModel().getSelectedItem() != null
                && !quantite.getText().equals("") && !prixUnitaire.getText().equals(""));
    }

    @FXML
    private void ajouterFournisseur(ActionEvent event) {
        chargerVue("/vues/pageFournisseur.fxml", "nouveauFournisseur");
    }

    private void chargerVue(String vue, String but) {
        FXMLLoader loader = new FXMLLoader();
        try {
            Stage stage = new Stage();
            stage.setResizable(false);
            loader.setLocation(getClass().getResource(vue));
            if (but.equals("nouveauFournisseur")) {
                InterfaceDAccueilController.chargerRessources(loader, "langFour");
            }
            if (but.equals("ajouterProduit")) {
                InterfaceDAccueilController.chargerRessources(loader, "langAjoutProduit");
            }
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> c) {
                    if (but.equals("nouveauFournisseur")) {
                        return new PageFournisseurController(stage, listeProduits.getItems(), emp, listeFournisseurCombo.getItems());
                    }
                    if (but.equals("ajouterProduit")) {
                        return new PageAjoutProduitController(stage, listeProduits.getItems(), emp);
                    } else {
                        return null;
                    }
                }
            });
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alertes.alerte(AlertMessages.erreur[valTab], AlertMessages.recommencer[valTab]);
        }
    }

    @FXML
    private void ajouterProduitFournisseur(ActionEvent event) {
        chargerVue("/vues/pageAjoutProduit.fxml", "ajouterProduit");
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        String categorie = Alertes.textInput(AlertMessages.addCategorie[valTab], AlertMessages.addCategorie[valTab], AlertMessages.categorie[valTab], AlertMessages.categorie[valTab]);
        if (categorie != null) {
            try {
                emp.ajouterCategorie(categorie);
                Alertes.information(AlertMessages.succes[valTab], AlertMessages.addCategorieMessage[valTab]);
            } catch (Exception e) {
                Alertes.information(AlertMessages.echec[valTab], AlertMessages.notAddCategorie[valTab]);
            }
        }
    }

    private void listenClick(MouseEvent event) {
        if (nomProduitCombo.getSelectionModel().getSelectedItem().equals("Autre")) {
            nomProduit.setDisable(false);
            prixUnitaire.setEditable(true);
            prixUnitaire.setText("");
            description.setEditable(true);
            description.setText("");
            datePeremption.setEditable(true);
            remplirFournisseur();
            remplirCategorie();
        } else {
            nomProduit.setDisable(true);
            String code = nomProduitCombo.getSelectionModel().getSelectedItem().split(" ")[0];
            Produit prod = Manager.em.find(Produit.class, code);
            if (prod != null) {
                prixUnitaire.setText("" + prod.getPrixUnitaire());
                prixUnitaire.setEditable(false);
                description.setText(prod.getDescription());
                description.setEditable(false);
                if (prod.getDatePeremption() != null) {
                    datePeremption.setValue(Instant.ofEpochMilli(prod.getDatePeremption().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                }
                remplirCategorieAdapte(prod);
                datePeremption.setEditable(false);
                categorie.setEditable(false);
                //on met juste l'ensemble des fournisseurs disponible pour le produit
                remplirFournisseurAdapte(prod);
            }
        }
    }
}
