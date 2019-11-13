/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.usersTab.userTable;

import application.partials.table.CustomSimpleColumn;
import application.utilities.TableViewManager;
import application.utilities.interfaces.CustomController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import model.Employe;

/**
 * FXML Controller class
 *
 * @author test
 */

@Getter @Setter
public class UserTableController implements Initializable,CustomController {

    @FXML
    private AnchorPane rootAnchor;
    
    
    private ObservableList<Employe> listData;
    
    
    @FXML
    private TableView<Employe> tableData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void customInit() {
     this.initTable();   
    }
    
    
    
    public void initTable(){
    CustomSimpleColumn colId=new CustomSimpleColumn("Id","id",0.2),
            colNom=new CustomSimpleColumn("Nom","nom",0.3),
            colGrade=new CustomSimpleColumn("Grade","grade",0.2),
            colDateEmb=new CustomSimpleColumn("Date d'embauche","dataEmbauche",0.7);
    
    TableViewManager.addTableColumns(tableData, colId,colNom,colGrade,colDateEmb);
       
    this.tableData.setItems(this.listData);
      
    
    
    
    
    }
    
    
    
    
     
}
