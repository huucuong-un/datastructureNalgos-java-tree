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
public class Node {

    Phone info; //use info.ID as the key value for node
    Node left, right;
    public int height;

    Node(Phone x) {
        this.info = x;
        this.left = null;
        this.right = null;
    }

    public boolean greaterThan(int key) {
        return this.info.getID() > key;
    }

    public boolean lessThan(int key) {
        return this.info.getID() < key;
    }

    public boolean equal(int key) {
        return this.info.getID() == key;
    }


    public void printInfo() {
        System.out.println("ID: " + this.info.getID() + " - Height: " + this.height + "\n");
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Phone getInfo() {
        return info;
    }

    public void setInfo(Phone info) {
        this.info = info;
    }
    
    
}
