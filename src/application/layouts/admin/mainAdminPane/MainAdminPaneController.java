/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.admin.mainAdminPane;

import application.layouts.admin.stockTab.StockTabController;
import application.layouts.admin.usersTab.UsersTabController;
import application.partials.IconedLabel;
import application.utilities.ViewLoaders;
import application.utilities.interfaces.CustomController;
import application.utilities.ViewDimensionner;
import com.jfoenix.controls.JFXTabPane;
import constantes.Devise;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import model.Administrateur;

/**
 * FXML Controller class
 *
 * @author test
 */
@Getter @Setter
public class MainAdminPaneController implements Initializable, CustomController {
    
    public static String CURRENT_DEVISE=Devise.FCFA;

    @FXML
    private VBox rootVBox;
    @FXML
    private AnchorPane headerBox;
    @FXML
    private AnchorPane bottomBox;
    @FXML
    private JFXTabPane mainTabPane;

    public VBox getRootVBox() {
        return rootVBox;
    }

    public void setRootVBox(VBox rootVBox) {
        this.rootVBox = rootVBox;
    }

    public AnchorPane getHeaderBox() {
        return headerBox;
    }

    public void setHeaderBox(AnchorPane headerBox) {
        this.headerBox = headerBox;
    }

    public AnchorPane getBottomBox() {
        return bottomBox;
    }

    public void setBottomBox(AnchorPane bottomBox) {
        this.bottomBox = bottomBox;
    }

    public JFXTabPane getMainTabPane() {
        return mainTabPane;
    }

    public void setMainTabPane(JFXTabPane mainTabPane) {
        this.mainTabPane = mainTabPane;
    }

    public Administrateur getAdmin() {
        return admin;
    }

    public void setAdmin(Administrateur admin) {
        this.admin = admin;
    }

    public double getImX() {
        return imX;
    }

    public void setImX(double imX) {
        this.imX = imX;
    }

    public double getImY() {
        return imY;
    }

    public void setImY(double imY) {
        this.imY = imY;
    }

    public Tab getProfileTab() {
        return profileTab;
    }

    public void setProfileTab(Tab profileTab) {
        this.profileTab = profileTab;
    }

    public Tab getUsersTab() {
        return usersTab;
    }

    public void setUsersTab(Tab usersTab) {
        this.usersTab = usersTab;
    }

    public Tab getStocksTab() {
        return stocksTab;
    }

    public void setStocksTab(Tab stocksTab) {
        this.stocksTab = stocksTab;
    }

    public Tab getStatsTab() {
        return statsTab;
    }

    public void setStatsTab(Tab statsTab) {
        this.statsTab = statsTab;
    }

    /**
     *
     * Valeur de Test pour l'administrateur
     */
    private Administrateur admin = new Administrateur();

    private double imX = 40, imY = 40;
    private Tab profileTab,
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
        
        ViewDimensionner.bindSizes(headerBox, rootVBox, 1.0, 0.05);
        ViewDimensionner.bindSizes(mainTabPane, rootVBox, 1.0, 0.9);
        ViewDimensionner.bindSizes(bottomBox, rootVBox, 1.0, 0.05);

        initProfileTab();
        initUsersTab();
        initStocksTab();
        initStatsTab();

    }

    public void initProfileTab() {
        this.profileTab = new Tab();
        profileTab.setGraphic(IconedLabel.plot("Mon profil", "user.png", true, imX, imY));
        profileTab.setContent(ViewLoaders.getView("layouts/admin/profileTab/profileTab"));
        this.mainTabPane.getTabs().add(profileTab);

    }

    public void initUsersTab() {
        this.usersTab = new Tab();

        usersTab.setGraphic(IconedLabel.plot("Utilisateurs", "user_groups.png", true, imX, imY));
        FXMLLoader loader=ViewLoaders.getLoader("layouts/admin/usersTab/usersTab");
        usersTab.setContent(ViewLoaders.getView(loader));
        
        UsersTabController tabController=loader.getController();
        tabController.setAdmin(this.admin);
        tabController.customInit();
        this.mainTabPane.getTabs().add(usersTab);
    }

    ;
    
    
    public void initStocksTab() {
        this.stocksTab = new Tab();

        stocksTab.setGraphic(IconedLabel.plot("Stocks", "stocks.png", true, imY, imY));
        FXMLLoader loader = ViewLoaders.getLoader("layouts/admin/stockTab/stockTab");
        stocksTab.setContent( ViewLoaders.getView(loader));
        
        StockTabController stockController = loader.getController();
        
        stockController.setAdmin(this.admin);
        
        
        stockController.customInit();
        this.stocksTab.selectedProperty().addListener((observable, oldValue , newValue) -> {

            if (newValue) {// Si la la le Tab des stocks est sélection 
                
                stockController.onSelectionHandle();

            }

        });

        
        
        this.mainTabPane.getTabs().add(stocksTab);
        System.out.println("HAUTEUR DU ANCHOR : "+stockController.getRootAnchor().heightProperty().get());
    }

    ;
    
    
    
    public void initStatsTab() {
        this.statsTab = new Tab();
        statsTab.setGraphic(IconedLabel.plot("Statistiques", "stats.png", true, imX, imY));
        statsTab.setContent(ViewLoaders.getView("layouts/admin/statsTab/statsTab"));
        this.mainTabPane.getTabs().add(statsTab);

    }

}
