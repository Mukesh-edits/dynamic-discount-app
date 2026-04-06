package com.demo;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Dynamic Discount System Started ===");

        DiscountService service = new DiscountService();

        System.out.println(service.calculateDiscount("ELECTRONICS", "WINTER", 5));
        System.out.println(service.calculateDiscount("CLOTHING", "SUMMER", 150));
        System.out.println(service.calculateDiscount("FOOD", "MONSOON", 80));

        System.out.println("=== System Running. Press Ctrl+C to stop. ===");
        try { Thread.currentThread().join(); } catch (InterruptedException ignored) {}
    }
}