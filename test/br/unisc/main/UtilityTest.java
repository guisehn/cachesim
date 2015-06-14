package br.unisc.main;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilityTest {
    
    public UtilityTest() {
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

    @Test
    public void log2DeveCalcularCorretamente() {
        for (double i = 0; i < 32; i++) {
            double n = Math.pow(2, i);
            assertEquals(Utility.log2(n), i, 0);
        }
    }
    
}
