package com.tact.eshop.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class CustomerTest {

    private Customer currentCustomer;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.currentCustomer = new Customer("fname", "lname");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCustomer() {
        assertNotNull(this.currentCustomer);
    }

    @Test
    public void testToString() {
        String result = this.currentCustomer.toString();
        assertEquals("fname lname", result);
    }

    @Test
    public void testFirstName() {
        assertNotNull(this.currentCustomer.getFirstName());

        assertEquals("fname", this.currentCustomer.getFirstName());

        this.currentCustomer.setFirstName("toto");
        assertEquals("toto", this.currentCustomer.getFirstName());

    }
    @Test
    public void testLastName() {
        assertNotNull(this.currentCustomer.getLastName());

        assertEquals("lname", this.currentCustomer.getLastName());

        this.currentCustomer.setLastName("toto");
        assertEquals("toto", this.currentCustomer.getLastName());
    }

    @Test
    public void testPhone() {
        assertNull(this.currentCustomer.getPhone());

        this.currentCustomer.setPhone("+33606060606");
        assertEquals("+33606060606", this.currentCustomer.getPhone());
    }

    @Test
    public void testAddress() {
        assertNull(this.currentCustomer.getAddress());

        this.currentCustomer.setAddress("15 rue de L'union");
        assertEquals("15 rue de L'union", this.currentCustomer.getAddress());
    }

    @Test
    public void testGetZip() {
        assertNull(this.currentCustomer.getZip());

        this.currentCustomer.setZip(35000);
        assertEquals(new Integer(35000), this.currentCustomer.getZip());
    }

    @Test
    public void testGetCity() {
        assertNull(this.currentCustomer.getCity());

        this.currentCustomer.setCity("Rennes");
        assertEquals("Rennes", this.currentCustomer.getCity());
    }

    @Ignore
    @Test
    public void testOrders() {
        fail("Not yet implemented");
    }

}
