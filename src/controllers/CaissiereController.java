/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static application.utilities.Tools.disable;
import static application.utilities.Tools.enable;
import static application.utilities.Tools.hide;
import static application.utilities.Tools.quickAlert;
import static application.utilities.Tools.show;
import com.itextpdf.text.DocumentException;
import factures.PrintFacture;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dbManager.Manager;
import factures.imprimerPDF;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Produit;
import model.LigneFacture;
import supermarche.SuperMarche;
import model.Facture;
import model.Caissier;
import model.Client;
import model.Employe;
import model.LigneFactureId;
import outils.AlertMessages;
import outils.Constante;

/**
 * FXML Controller class
 *
 * @author E_DinaBrown
 */
public class CaissiereController implements Initializable {

    private SuperMarche sup = new SuperMarche();
    private Facture facture;
    private ObservableList<LigneFacture> listeLigneFacture = FXCollections.observableArrayList();
    private ObservableList<Client> listeClients = FXCollections.observableArrayList();
    
    Client client = new Client();
    private String nom;
    private String id;

    @FXML
    private TableView<LigneFacture> ligneCommande;

    @FXML
    private TableColumn<LigneFacture, String> idColumn;

    @FXML
    private TableColumn<LigneFacture, String> nomProduitColumn;

    @FXML
    private TableColumn<LigneFacture, Integer> quantitéColumn;

    @FXML
    private TableColumn<LigneFacture, Float> prixColumn;

    @FXML
    private JFXTextField quantité;
    
    
    private BooleanProperty openPanier=new SimpleBooleanProperty();
    
    


    @FXML
    private Text prixTotal;

    @FXML
    private JFXButton valider;

    
    @FXML
    private JFXButton ajouter;

    @FXML
    private JFXButton deconnexion;

    @FXML
    private JFXButton changePassword;
    @FXML
    private Label nomUser;
    private Caissier emp = new Caissier();
    @FXML
    private JFXButton retirer;
    @FXML
    private TableView<Produit> tableProd;
    @FXML
    private TableColumn<Produit, String> idCol;
    @FXML
    private TableColumn<Produit, String> prodCol;
    @FXML
    private TableColumn<Produit, Integer> quantiteCol;
    @FXML
    private TableColumn<Produit, Float> unitCol;
    @FXML
    private JFXTextField rechercher;
    @FXML
    private JFXButton visualiser;
    @FXML
    private TableColumn<Produit, String> categorie;
    @FXML
    private AnchorPane anchorPanier;
    @FXML
    private AnchorPane anchorTable;
    
    @FXML
    private JFXTextField clientName;

    public CaissiereController(String nom, String id) {
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

    int estPresent(String nom) {
        System.out.println("Entré");
        for (int i = 0; i < listeLigneFacture.size(); i++) {
            System.out.println("Element " + listeLigneFacture.get(i).getLigneFactureId().getProduitId());
            if (listeLigneFacture.get(i).getLigneFactureId().getProduitId().equals(nom)) {
                return i;
            }
        }
        return -1;
    }

    @FXML
    void onValider(ActionEvent event) {
        valider();
    }

    @FXML
    void onChangePasssword(ActionEvent event) {
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
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceAdministrateurController.class.getName()).log(Level.SEVERE, null, ex);
            Alertes.alerte("Erreur", "Problème lors du chargement, veuillez recommencer svp");
        }
    }

    @FXML
    void onDeconnection(ActionEvent event) throws IOException {
        if (Alertes.confirmation("Demande de confirmation", "Etes vous sur de vouloir vous déconnecter")) {
            Manager.fermertureEntityManager();
            try {
                sup.showAccueil();
            } catch (IOException ex) {
                Logger.getLogger(EspaceComptableController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        hide(anchorPanier);
        openPanier.set(false);
        
        openPanier.addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                   
                if(newValue==true){
                    
               
                  show (anchorPanier);
                
                
                }
                else{
               
                  hide(anchorPanier);
                  
                    
                    
                }
                
                
                
            }
        
        
        });
         
        
        
        nomUser.setText(nom);
        /**
         * déactivation des boutons
         */
        //this.ajouter.disableProperty().bind(Bindings.isEmpty(this.quantité.textProperty()));
        this.ligneCommande.setItems(listeLigneFacture);
        config();
        configPresent();
        ligneCommande.getSelectionModel().selectedItemProperty().addListener((obs, ancVal, nouvVal) -> {
            if (nouvVal != null) {
                retirer.setDisable(false);
                retirer.setOnAction((ActionEvent e) -> {
                    if (Alertes.confirmation("Demande de confirmation", "Etes vous sur de vouloir retirer cet élément du panier ?")) {
                        listeLigneFacture.remove(nouvVal);
                        float val = Float.valueOf(prixTotal.getText())
                                - (nouvVal.getQuantite() * nouvVal.getPrixUnitaire());
                        prixTotal.setText("" + val);
                        
                        if(listeLigneFacture.isEmpty())openPanier.set(false);
                  hide (anchorPanier);
                ;
                    }
                    
                   
                });
            } else {
                retirer.setDisable(true);
            }
        });
        /**
         *
         */
        disable(ajouter);
        quantité.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try{
                    
                Integer val=Integer.valueOf(newValue);
                
                if(val>0){
                    enable(ajouter);
                }
                
                
            }
                catch(Exception e){
                    disable(ajouter );
                }
            }
            
            
        }
        );
        tableProd.getSelectionModel().selectedItemProperty().addListener((obs, ancVal, nouvVal) -> {
            if (nouvVal != null) {
                String cle = nouvVal.getId() + " - " + nouvVal.getNom();
                System.out.println("Clé " + cle);
                int val = estPresent(nouvVal.getId());
                System.out.println("Valeur de Val : "+val);
                
                
                System.out.println("Liste des clés");
                for(Object key:emp.getMapProduits().keySet())System.out.println(key.toString());
                Produit prod = ((Produit) (emp.getMapProduits().get(cle)));
                
                
                
                
                if (val == -1) {
                    if (prod != null) {
                        quantité.setText("" + Math.min(Math.max(0, prod.getQuantite()), 1));
                    } else {
                        quantité.setText("0");
                    }
                } else {
                    quantité.setText("" + (Math.min(1, (((Produit) (emp.getMapProduits().get(cle))).getQuantite() - listeLigneFacture.get(val).getQuantite()))));
                }
                ajouter.setDisable(false);
                ajouter.setOnAction((ActionEvent e) -> {
                   tableProd.getSelectionModel().clearSelection();
                    if (cle != null) {
                        try {
                            int qte = Integer.parseInt(quantité.getText());
                            if (qte <= 0) {
                                throw new Exception();
                            }
                            
                            
                            if (val == -1) {
                                if (prod != null) {
                                    if (qte > prod.getQuantite()) {
                                        System.out.println(" val== -1 et qte " + qte);
                                        System.out.println(((Produit) (emp.getMapProduits().get(cle))).getQuantite());
                                        throw new IllegalArgumentException();
                                    }
                                    openPanier.set(true);
                                    ajouterProduit(cle, qte);
                                    
                                    
                                } else {
                                    int valTab = 0;
                                    if (SuperMarche.langApp.equals(Constante.EN)) {
                                        valTab = 1;
                                    }
                                    if (SuperMarche.langApp.equals(Constante.FR)) {
                                        valTab = 0;
                                    }
                                    Alertes.alerte(AlertMessages.problemEntete[valTab], AlertMessages.produitAbsent[valTab]);
                                }
                            } else {
                                if (qte + listeLigneFacture.get(val).getQuantite() > prod.getQuantite()) {
                                    System.out.println("val == -1 et qte " + qte + listeLigneFacture.get(val).getQuantite());
                                    System.out.println(prod.getQuantite());
                                    throw new IllegalArgumentException();
                                }
                                listeLigneFacture.get(val).setQuantite(listeLigneFacture.get(val).getQuantite() + qte);
                                prixTotal.setText("" + (Float.valueOf(prixTotal.getText()) + qte * prod.getPrixUnitaire()));
                            }
                        } catch (IllegalArgumentException ex) {
                            int valTab = 0;
                            if (SuperMarche.langApp.equals(Constante.EN)) {
                                valTab = 1;
                            }
                            if (SuperMarche.langApp.equals(Constante.FR)) {
                                valTab = 0;
                            }
                            Alertes.alerte(AlertMessages.problemEntete[valTab], AlertMessages.qteInsuf[valTab]);
                        } catch (Exception ex) {
                            int valTab = 0;
                            if (SuperMarche.langApp.equals(Constante.EN)) {
                                valTab = 1;
                            }
                            if (SuperMarche.langApp.equals(Constante.FR)) {
                                valTab = 0;
                            }
                            Alertes.alerte(AlertMessages.problemEntete[valTab], AlertMessages.qteInsuf[valTab]);
                        }
                        quantité.setText("0");
                    }
                });
            } else {
                ajouter.setDisable(true);
            }
        });
        recherche();
        affecterClient("CLIENT000");
        chargerClient();
        this.facture = new Facture();
        this.facture.setEmployeId(this.emp.getId());
        this.facture.genererCode();
        this.facture.setClientId(client.getId());
        this.facture.setDateEnregistrement(new Date());
    
        
        openPanier.set(false);
       
    }

    private void affecterClient(String code) {
        client = Manager.em.find(Client.class, code);
    }

    private void chargerClient() {
        client.genererCode();
        client.setAdresse("ODICAM");
        client.setNom("Nokam Odilon");
        client.setTelephone("+237 699560885");
        Manager.em.getTransaction().begin();
        Manager.em.persist(client);
        Manager.em.getTransaction().commit();
    }

    public void recherche() {
        ObservableList<Produit> listeProduit = FXCollections.observableArrayList(this.emp.getMapProduits().values());
        FilteredList<Produit> flProduit = new FilteredList(listeProduit, p -> true);
        tableProd.setItems(listeProduit);
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
        tableProd.setItems(sortedData);
    }

    public void configPresent() {
        /**
         * configuration des colonnes de la tableView
         */
        this.idCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return param.getValue().idProperty();
            }
        });

        this.quantiteCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Produit, Integer> param) {
                return param.getValue().quantiteProperty().asObject();
            }
        });

        this.unitCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Produit, Float> param) {
                return param.getValue().prixUnitaireProperty().asObject();
            }
        });

        this.prodCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Produit, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Produit, String> param) {
                return param.getValue().nomProperty();
            }
        });
        categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    }

    public void config() {
        /**
         * configuration des colonnes de la tableView
         */
        this.idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneFacture, String> param) {
                return param.getValue().getLigneFactureId().produitIdProperty();
            }
        });

        this.quantitéColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneFacture, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<LigneFacture, Integer> param) {
                return param.getValue().quantiteProperty().asObject();
            }
        });

        this.prixColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneFacture, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<LigneFacture, Float> param) {
                return param.getValue().prixUnitaireProperty().asObject();
            }
        });

        this.nomProduitColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LigneFacture, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<LigneFacture, String> param) {
                StringProperty nomProperty = new SimpleStringProperty(Manager.em.find(Produit.class, param.getValue().getLigneFactureId().getProduitId()).getNom());
                return nomProperty;
            }
        });
    }

    public SuperMarche getSup() {
        return sup;
    }

    public void setSup(SuperMarche sup) {
        this.sup = sup;
    }

    private void ajouterProduit(String nom, int qte) {
        LigneFacture ligneFacture = new LigneFacture();
        Produit produit = (Produit) emp.getMapProduits().get(nom);
        ligneFacture.setPrixUnitaire(produit.getPrixUnitaire());
        ligneFacture.setQuantite(qte);
        LigneFactureId ligneFactureId = new LigneFactureId();
        ligneFactureId.setFactureId(this.facture.getId());
        ligneFactureId.setProduitId(((Produit) (emp.getMapProduits().get(nom))).getId());
        ligneFacture.setLigneFactureId(ligneFactureId);
        this.listeLigneFacture.add(ligneFacture);
        System.out.println("taille la liste des lignes factures :"+this.listeLigneFacture.size());
        prixTotal.setText("" + (Float.valueOf(prixTotal.getText()) + qte * produit.getPrixUnitaire()));
    }

    private void valider() {
        if (!this.listeLigneFacture.isEmpty()) {
            LigneFacture ligneFacture = this.listeLigneFacture.get(0);
            LigneFacture[] ligneFactures = new LigneFacture[this.listeLigneFacture.size() - 1];
            ligneFactures = new LigneFacture[this.listeLigneFacture.size() - 1];
            for (int i = 1; i < this.listeLigneFacture.size(); i++) {
                ligneFactures[i - 1] = this.listeLigneFacture.get(i);
            }
            try {
                this.emp.effectuerVente(this.facture, ligneFacture, ligneFactures);
                PrintFacture pf = new PrintFacture();
                File file = pf.print(facture, emp, listeLigneFacture, 0, "Norm", facture.getId(), Constante.ADDR, false);
                imprimerPDF imp = new imprimerPDF(file);
                Thread t = new Thread(imp);
                t.start();
            } catch (Exception ex) {
                Logger.getLogger(CaissiereController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.listeLigneFacture.clear();
            this.clientName.clear();
            this.facture = new Facture();
            this.facture.genererCode();
            this.facture.setClientId(clientName.getText().isEmpty()?"DEFAUT":clientName.getText());
            
            this.facture.setEmployeId(emp.getId());
            this.facture.setDateEnregistrement(new Date());
            prixTotal.setText("0");
            
            quickAlert(AlertType.INFORMATION,"Vente effectuée avec succès");
            openPanier.set(false);
        } else {
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.information(AlertMessages.information[valTab], AlertMessages.produitMinimal[valTab]);
        }
        
        
    }

    @FXML
    private void visualiser(ActionEvent event) {
        if (!this.listeLigneFacture.isEmpty()) {
            LigneFacture ligneFacture = this.listeLigneFacture.get(0);
            LigneFacture[] ligneFactures = null;
            if (this.listeLigneFacture.size() == 1) {
                try {
                    //this.emp.effectuerVente(this.facture, ligneFacture, ligneFactures);
                } catch (Throwable ex) {
                    Logger.getLogger(CaissiereController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ligneFactures = new LigneFacture[this.listeLigneFacture.size() - 1];
                for (int i = 1; i < this.listeLigneFacture.size(); i++) {
                    ligneFactures[i - 1] = this.listeLigneFacture.get(i);
                }
            }
            try {
                PrintFacture pf = new PrintFacture();
                File file = pf.print(facture, emp, listeLigneFacture, 0, "Vis", "0", Constante.ADDR, true);
            } catch (DocumentException ex) {
                Logger.getLogger(CaissiereController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CaissiereController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrinterException ex) {
                Logger.getLogger(CaissiereController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            int valTab = 0;
            if (SuperMarche.langApp.equals(Constante.EN)) {
                valTab = 1;
            }
            if (SuperMarche.langApp.equals(Constante.FR)) {
                valTab = 0;
            }
            Alertes.information(AlertMessages.information[valTab], AlertMessages.produitMinimal[valTab]);
                }
        
        
    }

}
