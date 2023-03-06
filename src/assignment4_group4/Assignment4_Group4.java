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
public class Assignment4_Group4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Balanced Binary Search Tree - AVL Tree \n");
        Node root = new Node(new Phone(1, "Vinh", 10, 2002, 2));

        AVLTree avlTree = new AVLTree(root);

        avlTree.insert(new Phone(2, "Vinh", 9, 2021, 2));
        avlTree.insert(new Phone(3, "Vinh", 5, 2001, 2));

        avlTree.insert(new Phone(4, "Vinh", 1, 2023, 2));
        avlTree.insert(new Phone(6, "Vinh", 10, 2022, 2));
        avlTree.insert(new Phone(7, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(8, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(700, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(800, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(900, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(1000, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(1100, "Vinh", 10, 2022, 2));
//        avlTree.insert(new Phone(1200, "Vinh", 10, 2022, 3));

        //avlTree.displayInOrder();
        //avlTree.displayPostOrder();
        avlTree.breadth();

        System.out.println("\n");
        //System.out.println("Min price: " + avlTree.find_Min_price());
//        System.out.println("\n\nAfter Delete");
//        avlTree.deleteNode(100);
//        avlTree.breadth();

//        System.out.println("Newest price: " + avlTree.find_Newest_Phone());   
//        
//        System.out.println("");
//        avlTree.deleteNode(200);
//        System.out.println("after delete");
//        avlTree.breadth();
        //System.out.println("After delete by merging: ");
//        avlTree.deleteByMerging(4);
//        avlTree.breadth();

//        System.out.println("");
//         System.out.println("After delete by copying: ");
//        avlTree.deleteByCopying(800);
//        avlTree.breadth();
//        
//        System.out.println("max Price: " + avlTree.find_Max_Value());
//        System.out.println("min price: " + avlTree.find_Min_price());
System.out.println("the newest phone: " + avlTree.find_Newest_Phone());
    }

}
