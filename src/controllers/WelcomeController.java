package controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WelcomeController {

	@FXML
    private ProgressIndicator progressIndicator;

	@FXML
    private Label copyright;

    @FXML
    private ImageView logo;

    @FXML
    private Label tensesMaster;


    private ParallelTransition transitions;


	private DoubleProperty valueProperty=new SimpleDoubleProperty();

	@FXML
	public void initialize()
	{

		progressIndicator.setStyle("-fx-fill:RED;");

		progressIndicator.progressProperty().bind(valueProperty);

		logo.setImage(new Image("images/logo.jpg"));

		/**Gestion de l'animation du logo**/
		FadeTransition transition1=new FadeTransition(Duration.seconds(4), logo);
		transition1.setFromValue(0);
		transition1.setToValue(1.0);

		/**Gestion de l'animation du label TensesMasterRuN**/
		TranslateTransition transition2=new TranslateTransition(Duration.seconds(1.5), tensesMaster);
		transition2.setFromX(-tensesMaster.getPrefWidth());
		transition2.setToX(0);

		/**Gestion de l'animation sur le copyright**/
		TranslateTransition transition3=new TranslateTransition(Duration.seconds(2), copyright);
		transition3.setFromY(copyright.getPrefHeight());
		transition3.setToY(0);

		transitions=new ParallelTransition(transition1,  transition2, transition3);
		transitions.play();

		/**Création des Threads de gestion du proressIndicator**/
		(new Thread(new Runnable() {

			boolean running=true;
			@Override
			public void run() {
				try {

					for (double time = 0; time < 4; time++) {

						if(running)
						{

							for (double i = 1; i <= 10; i++) {

								double angle=0;
								double temp=10*i;

								if(time==0)
								{
									angle=temp;
								}else if (time==1) {
									angle=90+temp;
								}else if (time==2) {
									angle=180+temp;
								}else {
									angle=270+temp;
								}

								double progressValue=(1.0/360)*angle;
								valueProperty.set(progressValue);

								Thread.sleep(100);
							}
						}
					}

					running=false;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		})).start();


	}
}
