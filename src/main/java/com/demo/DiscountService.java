package com.demo;

public class DiscountService {

    private static final java.util.List<String> VALID_CATEGORIES =
        java.util.Arrays.asList("ELECTRONICS", "CLOTHING", "FOOD", "FURNITURE", "SPORTS");
    private static final java.util.List<String> VALID_SEASONS =
        java.util.Arrays.asList("SUMMER", "WINTER", "MONSOON", "FESTIVAL");

    public double calculateDiscount(String category, String season, int inventoryLevel) {

        if (category == null || category.trim().isEmpty())
            throw new IllegalArgumentException("Category cannot be empty");
        if (season == null || season.trim().isEmpty())
            throw new IllegalArgumentException("Season cannot be empty");
        if (!VALID_CATEGORIES.contains(category.toUpperCase()))
            throw new IllegalArgumentException("Invalid category: " + category);
        if (!VALID_SEASONS.contains(season.toUpperCase()))
            throw new IllegalArgumentException("Invalid season: " + season);
        if (inventoryLevel < 0)
            throw new IllegalArgumentException("Inventory level cannot be negative");

        double discount = 0.0;

        // Inventory-level based discount
        if (inventoryLevel > 100) {
            discount += 15.0; // overstocked
        } else if (inventoryLevel > 50) {
            discount += 8.0;  // moderate stock
        } else if (inventoryLevel <= 10) {
            discount += 2.0;  // low stock
        } else {
            discount += 5.0;  // normal stock
        }

        // Season based discount
        switch (season.toUpperCase()) {
            case "FESTIVAL": discount += 25.0; break; // raised from 20 → 25 so cap can trigger
            case "WINTER":   discount += 10.0; break;
            case "SUMMER":   discount += 8.0;  break;
            case "MONSOON":  discount += 5.0;  break;
        }

        // Category based discount
        switch (category.toUpperCase()) {
            case "ELECTRONICS": discount += 5.0;  break;
            case "CLOTHING":    discount += 10.0; break;
            case "FOOD":        discount += 3.0;  break;
            case "FURNITURE":   discount += 7.0;  break;
            case "SPORTS":      discount += 6.0;  break;
        }

        // Cap at 50%
        return Math.min(discount, 50.0);
    }
}