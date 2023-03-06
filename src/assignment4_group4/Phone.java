/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_group4;

/**
 *
 * @author Admin
 */
public class Phone {
   private int ID;
    public String name;
    public int price;
    public int year;
    public int amount;

    public Phone(int ID, String name, int price, int year, int amount) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.year = year;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Phone{" + "ID=" + ID + ", name=" + name + ", price=" + price + ", year=" + year + ", amount=" + amount + '}';
    }
    
    
}
