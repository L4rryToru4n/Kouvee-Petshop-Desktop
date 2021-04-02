package main.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {

    private IntegerProperty Id;
    private StringProperty productName;
    private IntegerProperty productQuantity;
    private IntegerProperty productPrice;
    private StringProperty meassurement;
    private IntegerProperty minimumQuantity;
    private String imagePath;
    private byte[] image;

    public Product(String imagePath, byte[] image) {

        this.Id = new SimpleIntegerProperty();
        this.productQuantity = new SimpleIntegerProperty();
        this.minimumQuantity = new SimpleIntegerProperty();
        this.productPrice = new SimpleIntegerProperty();
        this.productName = new SimpleStringProperty();
        this.meassurement = new SimpleStringProperty();
        this.imagePath = imagePath;
        this.image = image;
    }

    public int getId() {
        return Id.get();
    }

    public IntegerProperty idProperty() {
        return Id;
    }

    public void setId(int id) {
        this.Id.set(id);
    }

    public String getProductName() {
        return productName.get();
    }

    public StringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public int getProductQuantity() {
        return productQuantity.get();
    }

    public IntegerProperty productQuantityProperty() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity.set(productQuantity);
    }

    public double getProductPrice() {
        return productPrice.get();
    }

    public IntegerProperty productPriceProperty() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice.set(productPrice);
    }

    public String getMeassurement() {
        return meassurement.get();
    }

    public StringProperty meassurementProperty() {
        return meassurement;
    }

    public void setMeassurement(String meassurement) {
        this.meassurement.set(meassurement);
    }

    public int getMinimumQuantity() {
        return minimumQuantity.get();
    }

    public IntegerProperty minimumQuantityProperty() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity.set(minimumQuantity);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
