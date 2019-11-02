/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author LOIC KWATE DASSI
 */
public class Manager {
    
    public static EntityManagerFactory emf;
    public static EntityManager em;
    
    
    public static void ouvertureEntityManager() {
        Manager.emf = Persistence.createEntityManagerFactory("supermarche");
        Manager.em = emf.createEntityManager();
    }
    
    public static void fermertureEntityManager() {
        Manager.em.close();
        Manager.emf.close();
    }
}
