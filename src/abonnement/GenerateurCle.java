package abonnement;

import java.util.Calendar;
import javafx.scene.control.Alert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ESDRAS
 */
public class GenerateurCle {
    public static String[] generer(long a, long b){
        int i=1;
        String[] liste= new String[5];
        Calendar calender = Calendar.getInstance();
        int jour = calender.get(Calendar.DAY_OF_MONTH);
        int mois = calender.get(Calendar.MONTH) + 1; 
        long tmp = a;
        b = (a + 1) * b - jour - mois + Calendar.YEAR;
        for(i=1;i<=5;i++){
            liste[i-1]=Integer.toString((int)(((float)Math.abs(Math.cos(b*i+jour)))*100000));
        }
        return liste;
    }

    public static Alert alerte(String entete,String mess){
        Alert alerte=new Alert(Alert.AlertType.WARNING);
        alerte.setTitle(entete);
        alerte.setHeaderText(null);
        alerte.setContentText(mess);
        alerte.showAndWait();
        return alerte;
    }
    
}
