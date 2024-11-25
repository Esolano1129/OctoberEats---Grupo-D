package org.example;

public class ProductItem extends DBConextion.Categoria {
    private String name;
    private int quantity;
    private double price;
    private String description;
    private double discount;

    public ProductItem() {
    }

    public ProductItem(String name, int quantity, double price, String description, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public ProductItem(String name, String image, String name1, int quantity, double price, String description, double discount) {
        super(name, image);
        this.name = name1;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void AddToOrder() {
    }

    public void AddNewProduct() {
    }

    public void EditProduct() {
    }

    public void DeleteProduct() {
    }

}
