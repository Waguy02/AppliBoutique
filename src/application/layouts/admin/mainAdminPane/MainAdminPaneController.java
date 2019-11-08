/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.mainAdminPane;

import application.partials.IconedLabel;
import application.utilities.ViewLoaders;
import application.utilities.interfaces.CustomController;
import application.utilities.interfaces.ViewDimensionner;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Administrateur;

/**
 * FXML Controller class
 *
 * @author test
 */
public class MainAdminPaneController implements Initializable,CustomController {
    
    @FXML
    private VBox rootVBox;
    @FXML
    private AnchorPane headerBox;
    @FXML
    private AnchorPane bottomBox;
    @FXML
    private JFXTabPane mainTabPane;
    
    /**
     * 
     * Valeur de Test pour l'administrateur
     */
    private Administrateur admin=new Administrateur(); 
  
    public void loadDatas(){
        
        
    }
    
   
    
    private double imX=40,imY=40;
    private Tab 
            profileTab,
            usersTab,
            stocksTab,
            statsTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.customInit();
    }    

    @Override
    public void customInit() {
            ViewDimensionner.bindSizes(headerBox,rootVBox,1.0,0.05);
            ViewDimensionner.bindSizes(mainTabPane, headerBox, 1.0, 0.9);
            ViewDimensionner.bindSizes(bottomBox,rootVBox,1.0,0.05);
            
                
            
            initProfileTab();
            initUsersTab();
            initStocksTab();
            initStatsTab();
            
    
    }
    


    
    
    
    
    public void initProfileTab(){
    this.profileTab=new Tab();
    profileTab.setGraphic(IconedLabel.plot("Mon profil", "user.png", true,imX,imY));
    profileTab.setContent(ViewLoaders.getView("layouts/admin/profileTab/profileTab"));
   this.mainTabPane.getTabs().add(profileTab);
        
    }
    
    
    
    public void initUsersTab(){
    this.usersTab=new Tab();
    
    usersTab.setGraphic(IconedLabel.plot("Utilisateurs", "user_groups.png", true,imX,imY));
    usersTab.setContent(ViewLoaders.getView("layouts/admin/usersTab/usersTab"));
    this.mainTabPane.getTabs().add(usersTab);
    };
    
    
    public void initStocksTab(){
    this.stocksTab=new Tab();
    
  stocksTab.setGraphic(IconedLabel.plot("Stocks", "stocks.png", true,imY,imY));
    stocksTab.setContent(ViewLoaders.getView("layouts/admin/stockTab/stockTab"));
    this.mainTabPane.getTabs().add(stocksTab);
    };
    
    
    
    public void initStatsTab(){
    this.statsTab=new Tab();
    statsTab.setGraphic(IconedLabel.plot("Statistiques","stats.png",true,imX,imY));
    statsTab.setContent(ViewLoaders.getView("layouts/admin/statsTab/statsTab"));
    this.mainTabPane.getTabs().add(statsTab);
    
    
    }
    
    
    
    
    
    
    
    
    















}
