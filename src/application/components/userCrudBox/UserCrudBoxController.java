/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.components.userCrudBox;

import application.partials.IconedLabel;
import application.partials.Separators;
import application.partials.inputs.LabelledCombo;
import application.partials.inputs.LabelledDatePicker;
import application.partials.inputs.LabelledPasswordField;
import application.partials.inputs.LabelledTextArea;
import application.partials.inputs.LabelledTextField;
import static application.utilities.AlertsManager.showConfirmation;
import static application.utilities.Tools.disable;
import static application.utilities.Tools.enable;
import static application.utilities.Tools.hide;
import static application.utilities.Tools.quickAlert;
import application.utilities.ViewDimensionner;
import static application.utilities.ViewDimensionner.bindSizes;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;
import model.Employe;
import outils.Outils;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter
@Setter
public class UserCrudBoxController implements Initializable, CustomController {

    private Employe manager;
    private Employe currentEmploye;

    private BooleanProperty EDITING_MODE = new SimpleBooleanProperty(false),
            REGISTERING_MODE = new SimpleBooleanProperty(false),
            READING_MODE = new SimpleBooleanProperty(false),
            OPEN_PROPERTY = new SimpleBooleanProperty(true);

    @FXML
    private AnchorPane operationLabel;
    private LabelledTextField userID;
    private LabelledTextField userName;
    private LabelledPasswordField userPassWord;
    private LabelledPasswordField userPasswordConfirmation;
    private LabelledCombo userGrade;
    private LabelledTextField userCni;

    private LabelledTextArea userAdresse;
    private LabelledTextField userPhone1;
    private LabelledTextField userPhone2;
    private LabelledDatePicker userDateEmb;
    @FXML
    private HBox actionBox;

    private JFXButton validateButton, discardButton;

    @FXML
    private VBox rootVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private double mainLabelHeight, inputHeight, actionBoxHeight;

    private final Integer NB_INPUT = 10;

    public void initComponentsSizes() {

        mainLabelHeight = 1.0 / NB_INPUT;
        inputHeight = 1.0 / (NB_INPUT + 15);
        actionBoxHeight = 1.0 / NB_INPUT;;

    }

    public void initHeader() {
        HBox mainLabel;
        if (this.EDITING_MODE.get()) {
            mainLabel = IconedLabel.plot("Modification des informations", "editing.png", true, 70, 70);
        } else if (this.READING_MODE.get()) {
            mainLabel = IconedLabel.plot("Informations sur l'Employé", "personCard.png", true, 70, 70);
        } else {
            mainLabel = IconedLabel.plot("Enregistrement d'un Employé", "personCard.png", true, 70, 70);
        }

        this.operationLabel.getChildren().addAll(mainLabel);
        bindSizes(operationLabel, this.rootVBox, 1, mainLabelHeight);
        bindSizes(mainLabel, operationLabel, 1, 1);

    }

    ObservableList<String> gradList = FXCollections.observableArrayList(Outils.ADMINISTRATEUR, Outils.AGENTCOMPTABLE, Outils.GESTIONNAIRESTOCK, Outils.ADMINISTRATEUR);

    ArrayList<HBox> inputList = new ArrayList();

    public void initInputs() {

        userID = new LabelledTextField("ID");
        userName = new LabelledTextField("Nom");
        userGrade = new LabelledCombo("Grade", new JFXComboBox(gradList));
        userPassWord = new LabelledPasswordField("Mot de passe ");
        userPasswordConfirmation = new LabelledPasswordField("Confirmation de mot de passe");

      

        userAdresse = new LabelledTextArea("Adresse");
        userPhone1 = new LabelledTextField("Numéro de téléphone");
        userPhone2 = new LabelledTextField("Téléphone d'un proche");
        userCni = new LabelledTextField("Numéro de CNI");
        userDateEmb = new LabelledDatePicker("Date D'embauche");

        HBox[] inputs = {userID, userName, userGrade, userPassWord, userPasswordConfirmation, userAdresse, userPhone1, userPhone2, userCni, userDateEmb};
        for (HBox input : inputs) {
            inputList.add(input);
        }

        rootVBox.getChildren().add(Separators.maxSeparatorH());

        for (HBox input : inputList) {

            ViewDimensionner.bindSizes(input, this.rootVBox, 1, inputHeight);
            rootVBox.getChildren().add(input);
            rootVBox.getChildren().add(Separators.maxSeparatorH());
        }

    }

    public void formatInputs() {

        disable(userID);
        if (READING_MODE.get()) {

            disable(this.userName);

            enable(this.userGrade);

            this.rootVBox.getChildren().removeAll(userPassWord, userPasswordConfirmation);

            return;
        }

        if (EDITING_MODE.get()) {
            
            
            disable(userGrade, userID);
              userPassWord.textProperty().addListener((observable, oldValue, value) -> {
            this.userPasswordConfirmation.getTextfield().clear();
        });
        userPasswordConfirmation.disableProperty().bind(userPassWord.textProperty().isEmpty());

        userPasswordConfirmation.focusedProperty().addListener((observable, oldValue, newValue) -> {;

            if (!newValue) {
                if (userPasswordConfirmation.getTextfield().getText().equals(this.userPassWord.getTextfield().getText())) {
                    this.userPasswordConfirmation.getTextfield().clear();
                }
            }

        });
                   

        }

    }

    public void initActionBox() {

        this.rootVBox.getChildren().remove(actionBox);
        this.rootVBox.getChildren().addAll(Separators.maxSeparatorH(), actionBox);

        this.discardButton = new JFXButton();
        this.validateButton = new JFXButton();
        bindSizes(this.actionBox, this.rootVBox, 1, this.actionBoxHeight);
        bindSizes(this.discardButton, this.actionBox, 0.2, 0.9);
        bindSizes(this.validateButton, this.actionBox, 0.2, 0.9);
        this.discardButton.getStyleClass().add("primary_button");
        this.actionBox.getChildren().addAll(this.discardButton, Separators.maxSeparatorV(), this.validateButton);

        if (this.EDITING_MODE.get()) {

            this.discardButton.setGraphic(IconedLabel.plot("Annuler", "discard.png", true));

            this.validateButton.setGraphic(IconedLabel.plot("Enregistrer", "standardConfirm.png", true));
            validateButton.setOnAction(event -> {
                if (showConfirmation("Voulez vraiment annuler ces modifications?")) {
                    this.OPEN_PROPERTY.set(false);

                }
            });
            this.validateButton.setOnAction(event -> {
                if (showConfirmation("Voulez vraiment enregsitrer ces modifications?")) {

                    this.currentEmploye.modifierMotDePasse(this.userPassWord.getPasswordField().getText());
                    quickAlert(AlertType.INFORMATION, "Informations modifiées avec succès");
                    this.OPEN_PROPERTY.set(false);
                }
                //Fermeture

            });

        }

        if (this.READING_MODE.get()) {

            hide(this.discardButton);

            this.validateButton.setGraphic(IconedLabel.plot("OK", "standardConfirm.png", true));
            validateButton.setOnAction(event -> {

                this.OPEN_PROPERTY.set(false);

            });
        }

        if (this.REGISTERING_MODE.get()) {

            this.userPassWord.getPasswordField().setText("supermarche");
            this.userPasswordConfirmation.getPasswordField().setText("supermarche");
            disable(this.userPassWord);
            this.discardButton.setGraphic(IconedLabel.plot("Annuler", "discard.png", true));

            this.validateButton.setGraphic(IconedLabel.plot("Enregistrer", "standardConfirm.png", true));

            this.discardButton.setOnAction(event -> this.OPEN_PROPERTY.set(false));
            validateButton.setOnAction(event -> {
                if (showConfirmation("Voulez vraiment annuler L'enregistrement?")) {
                    this.OPEN_PROPERTY.set(false);

                }
            });
            this.validateButton.setOnAction(event -> {
                if (showConfirmation("Voulez vraiment enregsitrer ces modifications?")) {
                    this.currentEmploye = new Employe();

                    this.currentEmploye.setAdresse(this.userAdresse.getTextArea().getText());
                    this.currentEmploye.setDateEmbauche(Date.from(this.userDateEmb.getDatePicker().getValue().atStartOfDay().toInstant(ZoneOffset.UTC)));
                    this.currentEmploye.setNom(this.userName.getTextfield().getText());
                    this.currentEmploye.setGrade((String) this.userGrade.getCombo().getValue());
                    this.currentEmploye.setTelephone(this.userPhone1.getTextfield().getText());
                    this.currentEmploye.setTelephoneProche(this.userPhone2.getTextfield().getText());

                    ((Administrateur) this.manager).creerEmploye(currentEmploye);

                    quickAlert(AlertType.INFORMATION, "Employé créer avec succès");
                    this.OPEN_PROPERTY.set(false);
                }
                //Fermeture

            });

        }

    }

    @Override
    public void customInit() {
        this.initComponentsSizes();

        this.initHeader();
        this.initInputs();
        this.formatInputs();
        this.initActionBox();
        this.initData();
    }
    
    
    public void initData(){
        
        if(this.currentEmploye!=null){
            
            this.userName.getTextfield().setText(this.currentEmploye.getNom());
            this.userCni.getTextfield().setText(currentEmploye.getNumeroCni());
            this.userDateEmb.getDatePicker().setValue(this.currentEmploye.getDateEmbauche().toInstant().atZone(ZoneOffset.UTC).toLocalDate());
            this.userGrade.getCombo().setValue(currentEmploye.getGrade());
            this.userID.getTextfield().setText(currentEmploye.getId());
            this.userPhone1.getTextfield().setText(currentEmploye.getTelephone());
            this.userPhone2.getTextfield().setText(currentEmploye.getTelephoneProche());
            
            
        }
        
        
        
        
   
    }
}
