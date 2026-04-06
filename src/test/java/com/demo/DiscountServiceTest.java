package com.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountServiceTest {

    private DiscountService service;

    @BeforeEach
    public void setUp() {
        service = new DiscountService();
    }

    // Regular discount tests
    @Test
    public void testRegularDiscount_NormalStock() {
        double discount = service.calculateDiscount("ELECTRONICS", "SUMMER", 60);
        assertTrue(discount > 0, "Discount should be greater than 0");
    }

    @Test
    public void testRegularDiscount_FestivalSeason() {
        double discount = service.calculateDiscount("CLOTHING", "FESTIVAL", 80);
        assertTrue(discount >= 20.0, "Festival discount should be high");
    }

    @Test
    public void testRegularDiscount_OverstockedInventory() {
        double discount = service.calculateDiscount("FURNITURE", "WINTER", 150);
        assertTrue(discount > 10.0, "Overstocked items should have higher discount");
    }

    @Test
    public void testRegularDiscount_LowStock() {
        double discount = service.calculateDiscount("FOOD", "SUMMER", 5);
        assertTrue(discount > 0 && discount < 20.0, "Low stock should have minimal discount");
    }

    @Test
    public void testDiscountCappedAt50() {
        double discount = service.calculateDiscount("CLOTHING", "FESTIVAL", 200);
        assertEquals(50.0, discount, "Discount should be capped at 50%");
    }

    @Test
    public void testSeasonBasedDiscount_Winter() {
        double discount = service.calculateDiscount("SPORTS", "WINTER", 60);
        assertTrue(discount >= 10.0, "Winter discount should be applied");
    }

    @Test
    public void testCategoryBasedDiscount_Food() {
        double discount = service.calculateDiscount("FOOD", "MONSOON", 60);
        assertTrue(discount > 0, "Food category discount should be applied");
    }

    // Invalid input tests
    @Test
    public void testInvalidCategory() {
        assertThrows(IllegalArgumentException.class, () ->
            service.calculateDiscount("INVALID", "SUMMER", 50));
    }

    @Test
    public void testInvalidSeason() {
        assertThrows(IllegalArgumentException.class, () ->
            service.calculateDiscount("ELECTRONICS", "SPRING", 50));
    }

    @Test
    public void testEmptyCategory() {
        assertThrows(IllegalArgumentException.class, () ->
            service.calculateDiscount("", "SUMMER", 50));
    }

    @Test
    public void testNegativeInventory() {
        assertThrows(IllegalArgumentException.class, () ->
            service.calculateDiscount("ELECTRONICS", "SUMMER", -10));
    }

    @Test
    public void testNullSeason() {
        assertThrows(IllegalArgumentException.class, () ->
            service.calculateDiscount("ELECTRONICS", null, 50));
    }
}