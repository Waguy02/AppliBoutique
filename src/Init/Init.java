/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Init;

import dbManager.Manager;
import outils.Outils;

/**
 *
 * @author test
 */
public class Init {
    
    
    
    public static void globalInit(){
        
    Manager.ouvertureEntityManager();
        Outils.chargementPropertiesIndex();
    
}
}
