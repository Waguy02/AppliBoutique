/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.layouts.caissiere.salePane;

import application.layouts.caissiere.mainTabPane.MainTabPaneController;
import application.layouts.caissiere.salePane.preview.SalePreviewTableController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Produit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author test
 */
public class SalePaneControllerTest {
    
    public SalePaneControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSalePreviewController method, of class SalePaneController.
     */
    @Test
    public void testGetSalePreviewController() {
        System.out.println("getSalePreviewController");
        SalePaneController instance = new SalePaneController();
        SalePreviewTableController expResult = null;
        SalePreviewTableController result = instance.getSalePreviewController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSalePreviewController method, of class SalePaneController.
     */
    @Test
    public void testSetSalePreviewController() {
        System.out.println("setSalePreviewController");
        SalePreviewTableController salePreviewController = null;
        SalePaneController instance = new SalePaneController();
        instance.setSalePreviewController(salePreviewController);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewItems method, of class SalePaneController.
     */
    @Test
    public void testGetPreviewItems() {
        System.out.println("getPreviewItems");
        SalePaneController instance = new SalePaneController();
        ObservableList<Produit> expResult = null;
        ObservableList<Produit> result = instance.getPreviewItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviewItems method, of class SalePaneController.
     */
    @Test
    public void testSetPreviewItems() {
        System.out.println("setPreviewItems");
        ObservableList<Produit> previewItems = null;
        SalePaneController instance = new SalePaneController();
        instance.setPreviewItems(previewItems);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProdCombo method, of class SalePaneController.
     */
    @Test
    public void testGetProdCombo() {
        System.out.println("getProdCombo");
        SalePaneController instance = new SalePaneController();
        ComboBox<Produit> expResult = null;
        ComboBox<Produit> result = instance.getProdCombo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProdCombo method, of class SalePaneController.
     */
    @Test
    public void testSetProdCombo() {
        System.out.println("setProdCombo");
        ComboBox<Produit> prodCombo = null;
        SalePaneController instance = new SalePaneController();
        instance.setProdCombo(prodCombo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddItemButton method, of class SalePaneController.
     */
    @Test
    public void testGetAddItemButton() {
        System.out.println("getAddItemButton");
        SalePaneController instance = new SalePaneController();
        JFXButton expResult = null;
        JFXButton result = instance.getAddItemButton();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddItemButton method, of class SalePaneController.
     */
    @Test
    public void testSetAddItemButton() {
        System.out.println("setAddItemButton");
        JFXButton addItemButton = null;
        SalePaneController instance = new SalePaneController();
        instance.setAddItemButton(addItemButton);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQteField method, of class SalePaneController.
     */
    @Test
    public void testGetQteField() {
        System.out.println("getQteField");
        SalePaneController instance = new SalePaneController();
        JFXTextField expResult = null;
        JFXTextField result = instance.getQteField();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQteField method, of class SalePaneController.
     */
    @Test
    public void testSetQteField() {
        System.out.println("setQteField");
        JFXTextField qteField = null;
        SalePaneController instance = new SalePaneController();
        instance.setQteField(qteField);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMainController method, of class SalePaneController.
     */
    @Test
    public void testGetMainController() {
        System.out.println("getMainController");
        SalePaneController instance = new SalePaneController();
        MainTabPaneController expResult = null;
        MainTabPaneController result = instance.getMainController();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMainController method, of class SalePaneController.
     */
    @Test
    public void testSetMainController() {
        System.out.println("setMainController");
        MainTabPaneController mainController = null;
        SalePaneController instance = new SalePaneController();
        instance.setMainController(mainController);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddingAnchor method, of class SalePaneController.
     */
    @Test
    public void testGetAddingAnchor() {
        System.out.println("getAddingAnchor");
        SalePaneController instance = new SalePaneController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getAddingAnchor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddingAnchor method, of class SalePaneController.
     */
    @Test
    public void testSetAddingAnchor() {
        System.out.println("setAddingAnchor");
        AnchorPane addingAnchor = null;
        SalePaneController instance = new SalePaneController();
        instance.setAddingAnchor(addingAnchor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductAddBox method, of class SalePaneController.
     */
    @Test
    public void testGetProductAddBox() {
        System.out.println("getProductAddBox");
        SalePaneController instance = new SalePaneController();
        HBox expResult = null;
        HBox result = instance.getProductAddBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProductAddBox method, of class SalePaneController.
     */
    @Test
    public void testSetProductAddBox() {
        System.out.println("setProductAddBox");
        HBox productAddBox = null;
        SalePaneController instance = new SalePaneController();
        instance.setProductAddBox(productAddBox);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProductNameSearcBox method, of class SalePaneController.
     */
    @Test
    public void testGetProductNameSearcBox() {
        System.out.println("getProductNameSearcBox");
        SalePaneController instance = new SalePaneController();
        HBox expResult = null;
        HBox result = instance.getProductNameSearcBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProductNameSearcBox method, of class SalePaneController.
     */
    @Test
    public void testSetProductNameSearcBox() {
        System.out.println("setProductNameSearcBox");
        HBox productNameSearcBox = null;
        SalePaneController instance = new SalePaneController();
        instance.setProductNameSearcBox(productNameSearcBox);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviewAnchor method, of class SalePaneController.
     */
    @Test
    public void testGetPreviewAnchor() {
        System.out.println("getPreviewAnchor");
        SalePaneController instance = new SalePaneController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getPreviewAnchor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPreviewAnchor method, of class SalePaneController.
     */
    @Test
    public void testSetPreviewAnchor() {
        System.out.println("setPreviewAnchor");
        AnchorPane previewAnchor = null;
        SalePaneController instance = new SalePaneController();
        instance.setPreviewAnchor(previewAnchor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValidationAnchor method, of class SalePaneController.
     */
    @Test
    public void testGetValidationAnchor() {
        System.out.println("getValidationAnchor");
        SalePaneController instance = new SalePaneController();
        AnchorPane expResult = null;
        AnchorPane result = instance.getValidationAnchor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValidationAnchor method, of class SalePaneController.
     */
    @Test
    public void testSetValidationAnchor() {
        System.out.println("setValidationAnchor");
        AnchorPane validationAnchor = null;
        SalePaneController instance = new SalePaneController();
        instance.setValidationAnchor(validationAnchor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class SalePaneController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        SalePaneController instance = new SalePaneController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of customInit method, of class SalePaneController.
     */
    @Test
    public void testCustomInit() {
        System.out.println("customInit");
        SalePaneController instance = new SalePaneController();
        instance.customInit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initAddProductBar method, of class SalePaneController.
     */
    @Test
    public void testInitAddProductBar() {
        System.out.println("initAddProductBar");
        SalePaneController instance = new SalePaneController();
        instance.initAddProductBar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearProductBar method, of class SalePaneController.
     */
    @Test
    public void testClearProductBar() {
        System.out.println("clearProductBar");
        SalePaneController instance = new SalePaneController();
        instance.clearProductBar();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initAddButton method, of class SalePaneController.
     */
    @Test
    public void testInitAddButton() {
        System.out.println("initAddButton");
        SalePaneController instance = new SalePaneController();
        instance.initAddButton();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initPreviewTable method, of class SalePaneController.
     */
    @Test
    public void testInitPreviewTable() {
        System.out.println("initPreviewTable");
        SalePaneController instance = new SalePaneController();
        instance.initPreviewTable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initValidationAnchor method, of class SalePaneController.
     */
    @Test
    public void testInitValidationAnchor() {
        System.out.println("initValidationAnchor");
        SalePaneController instance = new SalePaneController();
        instance.initValidationAnchor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
