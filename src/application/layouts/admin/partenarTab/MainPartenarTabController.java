/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.partenarTab;

import application.partials.IconedLabel;
import application.utilities.interfaces.CustomController;
import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author test
 */
public class MainPartenarTabController implements Initializable,CustomController {

    @FXML
    private JFXTabPane mainTabPane;
    
    
    
    
    
    
    private Tab  clientTab,fournisseurTab;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    @Override
    public void customInit() {
        
       
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    public void initClientTab(){
        
        this.clientTab=new Tab();
        this.clientTab.setGraphic(IconedLabel.plot("Clients", "client.png", true));
        
        
        this.mainTabPane.getTabs().add(clientTab);
        
    }
    
    public void initFournisseurTab(){
        this.fournisseurTab=new Tab();
        this.fournisseurTab.setGraphic(IconedLabel.plot("Fournisseurs", "provider.png", true));
                this.mainTabPane.getTabs().add(this.fournisseurTab);
    }
    
    
    
    
    
    
    
}
