/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class Outils {
    public static final String INDEXPROPERTIES = "../indexPrefix.properties";
    public static final String ENTITYMANAGER = "supermarche";
    public static final int LONGUEURCODE = 6;
    public static final double TVA = 19.5;
    public static final int LIMIT = 15;
    /**
     * définition des index et prefix des codes;
     */
    public static int INDPRODUIT;
    public static String PREPRODUIT;
    public static int INDMESURE;
    public static String PREMESURE;
    public static int INDFOURNISSEUR;
    public static String PREFOURNISSEUR;
    public static int INDFACTURE;
    public static String PREFACTURE;
    public static int INDCOMMANDE;
    public static String PRECOMMANDE;
    public static int INDEMPLOYE;
    public static String PREEMPLOYE;
    public static int INDCLIENT;
    public static String PRECLIENT;
    public static int INDTYPEEMPLOYE;
    public static String PRETYPEEMPLOYE;

    /**
     * Definition des grades
     */
    public static final String ADMINISTRATEUR = "ADMINISTRATEUR";
    public static final String CAISSIER = "CAISSIER";
    public static final String AGENTCOMPTABLE = "AGENTCOMPTABLE";
    public static final String GESTIONNAIRESTOCK = "GESTIONNAIRESTOCK";

    /**
     * chargement du fichier db.properties pour la gestion des identifiants de l'application
     */
    public static void chargementPropertiesIndex() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(INDEXPROPERTIES));
            INDPRODUIT = Integer.parseInt(prop.getProperty("indexProduit"));
            PREPRODUIT = prop.getProperty("prefixProduit");
            INDMESURE = Integer.parseInt(prop.getProperty("indexMesure"));
            PREMESURE = prop.getProperty("prefixMesure");
            INDFOURNISSEUR = Integer.parseInt(prop.getProperty("indexFournisseur"));
            PREFOURNISSEUR = prop.getProperty("prefixFournisseur");
            INDFACTURE = Integer.parseInt(prop.getProperty("indexFacture"));
            PREFACTURE = prop.getProperty("prefixFacture");
            INDCOMMANDE = Integer.parseInt(prop.getProperty("indexCommande"));
            PRECOMMANDE = prop.getProperty("prefixCommande");
            INDEMPLOYE = Integer.parseInt(prop.getProperty("indexEmploye"));
            PREEMPLOYE = prop.getProperty("prefixEmploye");
            INDCLIENT = Integer.parseInt(prop.getProperty("indexClient"));
            PRECLIENT = prop.getProperty("prefixClient");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Outils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Outils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * enregistrement dans le fichier db.properties les indexes et prefixes pour la gestion des identifiants dans l'application
     */
    public static void enregistrementPropertiesIndex() {
        Properties prop = new Properties();
        prop.setProperty("indexProduit", Integer.toString(INDPRODUIT));
        prop.setProperty("prefixProduit", PREPRODUIT);
        prop.setProperty("indexMesure", Integer.toString(INDMESURE));
        prop.setProperty("prefixMesure", PREMESURE);
        prop.setProperty("indexFournisseur", Integer.toString(INDFOURNISSEUR));
        prop.setProperty("prefixFournisseur", PREFOURNISSEUR);
        prop.setProperty("indexFacture", Integer.toString(INDFACTURE));
        prop.setProperty("prefixFacture", PREFACTURE);
        prop.setProperty("indexCommande", Integer.toString(INDCOMMANDE));
        prop.setProperty("prefixCommande", PRECOMMANDE);
        prop.setProperty("indexEmploye", Integer.toString(INDEMPLOYE));
        prop.setProperty("prefixEmploye", PREEMPLOYE);
        prop.setProperty("indexClient", Integer.toString(INDCLIENT));
        prop.setProperty("prefixClient", PRECLIENT);
        try {
            prop.store(new FileOutputStream(INDEXPROPERTIES), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Outils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Outils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * convertion de java.util.Date en java.sql.Date pour la sauvegarde des dates dans la base de données.
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDateUtilDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

}
