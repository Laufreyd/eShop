package com.tact.eshop.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class OrderTest {

    private Order currentOrder;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        this.currentOrder = new Order();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testOrder() {
        assertNotNull(this.currentOrder);
    }

    @Test
    public void testCustomer() {
        assertNull(this.currentOrder.getCustomer());

        Customer customer = new Customer("test", "test");

        this.currentOrder.setCustomer(customer);
        assertNotNull(this.currentOrder.getCustomer());

        this.currentOrder.setCustomer(null);
        assertNull(this.currentOrder.getCustomer());
    }

    @Ignore
    @Test
    public void testGetTotal() {
        fail("Not yet implemented");
    }

    @Test
    public void testProduct() {
        assertEquals(0, this.currentOrder.getOrderedProduct().size());

        Product product = new Product("test");

        this.currentOrder.addProduct(product, 2);
        assertEquals(1, this.currentOrder.getOrderedProduct().size());

        this.currentOrder.removeProduct(product, 1);
        assertEquals(1, this.currentOrder.getOrderedProduct().size());

        this.currentOrder.removeProduct(product, 2);
        assertEquals(0, this.currentOrder.getOrderedProduct().size());
    }

}
