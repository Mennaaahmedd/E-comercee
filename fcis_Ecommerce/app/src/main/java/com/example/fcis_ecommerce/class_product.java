package com.example.fcis_ecommerce;

public class class_product {
    public int product_id;
    public String product_name;
    public double Price;
    public int Quantity;
    public int cat_id;

    public class_product(int catId, String ProductName, double Price, int quan, int proId) {
        this.cat_id = catId;
        this.Price = Price;
        this.product_id = proId ;
        this.product_name = ProductName;
        this.Quantity = quan;
    }
}
