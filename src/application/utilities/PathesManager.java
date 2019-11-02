/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.utilities;

import java.nio.file.Paths;

/**
 *
 * @author test
 */
public class PathesManager {
        
    
    
    public static String GLOBAL_CSS_SHEET="application/global_styles/main.css";
    
    public static String getImagePath(String shortPath){
        
        String result="application/images/"+shortPath;
 
        return result;}
        

}
