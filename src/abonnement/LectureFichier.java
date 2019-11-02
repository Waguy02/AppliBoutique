/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abonnement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author ESDRAS
 */
public class LectureFichier {
    public static BufferedReader fis= null;
    public static String repertoire="../document.txt";
    public static String lire(int nLigne){
        try{
            fis = new BufferedReader(new BufferedReader(new FileReader(repertoire)));
            String ligne;
            int i=0;
            do{
                ligne=fis.readLine();
                if(ligne!=null && i==nLigne)
                    return ligne;
                i++;    
            }while(ligne!=null);
            fis.close();
        }
        catch(Exception e){
            GenerateurCle.alerte("Problème de récupération du numéro d'abonnement", "Impossible de récupérer le numéro d'abonnement");
        }
        return null;
    }
    public static void ecrire(String nom,int i){
        try{
            fis = new BufferedReader(new BufferedReader(new FileReader(repertoire)));
            String ligne;
            ArrayList<String> liste=new ArrayList<String>();
            do{
                ligne=fis.readLine();
                if(ligne!=null)
                    liste.add(ligne);
            }while(ligne!=null);
            fis.close();
            if(liste.size()>i){
                liste.get(i).replaceAll(liste.get(i), nom);
                Path fichier = Paths.get(repertoire);
                Files.write(fichier,liste,Charset.forName("UTF-8"));
            }
            else{
                throw new Exception("Taille de la liste trop petite");
            }
        }
        catch(Exception e){
            GenerateurCle.alerte("Impossible de modifier le fichier","Nous avons rencontré un problème lors de la modification du fichier");
        }
    }
}
