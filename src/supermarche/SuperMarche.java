/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarche;

import controllers.EspaceAdministrateurController;
import controllers.InterfaceDAccueilController;
import dbManager.Manager;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import static java.util.ResourceBundle.getBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import outils.Constante;
import outils.Outils;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class SuperMarche extends Application {

    public static String langApp = Constante.FR;
    public static Stage stage;
    private Scene scene;
    private FXMLLoader loader;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        init();
        showWelcomePage();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

    public void init() {
        Outils.chargementPropertiesIndex();
        Manager.ouvertureEntityManager();
    }

    public void showAccueil() throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vues/InterfaceDAccueil.fxml"));
        AnchorPane pane = loader.load();
        InterfaceDAccueilController con = loader.getController();
        scene = new Scene(pane);
        stage.setTitle("Accueil");
        stage.setResizable(false);
        //stage.getIcons().add(new Image(Constante.CHEMINLOGO));
        stage.setScene(scene);
        stage.show();
    }

    public void showEspaceAdministrateur(String nomU, String id) throws IOException {
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/vues/espaceAdministrateur.fxml"));
        InterfaceDAccueilController.chargerRessources(loader,"langAdmin");
        loader.setControllerFactory(c -> {
            return new EspaceAdministrateurController(nomU, id);
        });
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setTitle("Espace Administrateur");
        stage.setScene(scene);
        stage.show();
    }
    public void showWelcomePage()
	{
		Stage stage=new Stage();
		String view_path="/vues/welcome.fxml";
		try {
			loader=new FXMLLoader();
			loader.setLocation(getClass().getResource(view_path));
			AnchorPane pane=(AnchorPane)loader.load();

			Scene scene=new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.UNDECORATED);
			//stage.getIcons().add(new Image(Constante.CHEMINLOGO));
			stage.show();

			PauseTransition delay = new PauseTransition(Duration.seconds(4));
			delay.setOnFinished( event -> welcomeToHomePage(pane, stage));
			delay.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public void welcomeToHomePage(AnchorPane welcome_pane, Stage welcome_stage)
	{
        try {
            showAccueil();
            FadeTransition transition=new FadeTransition(Duration.seconds(1), welcome_pane);
            transition.setFromValue(1.0);
            transition.setToValue(0.0);
            transition.setOnFinished(e->{
                    welcome_stage.close();
            });

            transition.play();
        } catch (IOException ex) {
            Logger.getLogger(SuperMarche.class.getName()).log(Level.SEVERE, null, ex);
        }

	}
}
