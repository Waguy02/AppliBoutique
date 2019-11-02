/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factures;

import controllers.Alertes;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ESDRAS
 */
public class imprimerPDF implements Runnable {
    private File file;
    public imprimerPDF(File file) {
        this.file = file;
    }
    public void imprimerPDF() {
        if (Desktop.isDesktopSupported()) {
            if(Desktop.getDesktop().isSupported(java.awt.Desktop.Action.PRINT)){
                try {
                    java.awt.Desktop.getDesktop().print(file);
                } catch (IOException ex) {
                    Logger.getLogger(imprimerPDF.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                Alertes.alerte("Problem", "The computer does not support the method");
            }
        }
        else{
            Alertes.alerte("Problem", "The computer does not support the method");
        }
    }

    @Override
    public void run() {
        try{
        imprimerPDF();
        }
        catch(Exception e){
            
            System.out.println("Erreur d'impression");
        }
    }
}
