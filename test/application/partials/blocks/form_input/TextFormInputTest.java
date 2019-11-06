/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.partials.blocks.form_input;

import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
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
public class TextFormInputTest {
    
    public TextFormInputTest() {
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
     * Test of getDataBox method, of class TextFormInput.
     */
    @Test
    public void testGetDataBox() {
        System.out.println("getDataBox");
        TextFormInput instance = null;
        HBox expResult = null;
        HBox result = instance.getDataBox();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataBox method, of class TextFormInput.
     */
    @Test
    public void testSetDataBox() {
        System.out.println("setDataBox");
        HBox DataBox = null;
        TextFormInput instance = null;
        instance.setDataBox(DataBox);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSeparator method, of class TextFormInput.
     */
    @Test
    public void testGetSeparator() {
        System.out.println("getSeparator");
        TextFormInput instance = null;
        Separator expResult = null;
        Separator result = instance.getSeparator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSeparator method, of class TextFormInput.
     */
    @Test
    public void testSetSeparator() {
        System.out.println("setSeparator");
        Separator separator = null;
        TextFormInput instance = null;
        instance.setSeparator(separator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of init method, of class TextFormInput.
     */
    @Test
    public void testInit() {
        System.out.println("init");
        TextFormInput instance = null;
        instance.init();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
