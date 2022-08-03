package DTO;

public class Item {
    String name;
    int stock, price;

    public Item(String name, int stock, int price){
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getStock(){
        return this.stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    //input a number and add it to the stock, could be pos or neg
    public void adjustStock(int amount){
        this.stock += amount;
    }
}
