/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_group4;

import queue.ArrayBasedQueue;

/**
 *
 * @author Admin
 */
public class AVLTree {

    Node root;
    private int size;

    public AVLTree() {
        this.size = 0;
        this.root = null;
    }

    public AVLTree(Node root) {
        this.root = root;
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(int elem) { //search
        return contains(root, elem);
    }

    private boolean contains(Node node, int elem) { //dùng recursion
        if (node == null) {
            return false;
        }

        if (elem < node.getInfo().getID()) {
            return contains(node.getLeft(), elem);
        } else if (elem > node.getInfo().getID()) {
            return contains(node.getRight(), elem);
        } else {
            return true;
        }
    }

    private int max(int key1, int key2) {
        if (key1 < key2) {
            return key2;
        }
        return key1;
    }

    private Node leftRotation(Node node) {
        if (node == null) {
            return null;
        }

        Node returnNode = node.right;
        node.right = returnNode.left;
        returnNode.left = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        returnNode.height = max(height(returnNode.left), height(returnNode.right)) + 1;

        return returnNode;
    }

    private Node rightRotation(Node node) {
        if (node == null) {
            return null;
        }

        Node returnNode = node.left;
        node.right = returnNode.right;
        returnNode.right = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        returnNode.height = max(height(returnNode.left), height(returnNode.right)) + 1;

        return returnNode;
    }

    public void insert(Phone x) {
        this.root = insert(this.root, x);
    }

    private Node insert(Node node, Phone key) {
        if (node == null) {
            this.size++;
            return new Node(key);

        }

        if (node.equal(key.getID())) {
            return node;
        }

        if (node.lessThan(key.getID())) {
            node.right = insert(node.right, key);
        } else {
            node.left = insert(node.left, key);
        }

        node.height = max(height(node.left), height(node.right)) + 1;

        int balance = balanceFactor(node);

        if (balance > 1) {
            // left => right
            if (node.left.lessThan(key.getID())) {
                //left rotation
                node.left = leftRotation(node.left);
            }
            //right rotation
            return rightRotation(node);
        } else if (balance < -1) {
            // left <= right
            if (node.right.greaterThan(key.getID())) {
                //left rotation
                node.right = rightRotation(node.right);
            }
            //left rotation
            return leftRotation(node);
        }

        return node;
    }

    void visit(Node p) {
        if (p == null) {
            return;
        }

        System.out.println(p.info);

    }

    public int height() {
        return height(root);
    }

    private int height(Node p) {
        if (p == null) {
            return 0;
        }
        return 1 + Math.max(height(p.left), height(p.right));

    }

    private int balanceFactor(Node p) {
        if (p == null) {
            return 0;
        }
        return height(p.left) - height(p.right);

    }

    void breadth() throws Exception {
        if (root == null) {
            return;
        }
        ArrayBasedQueue q = new ArrayBasedQueue();

        q.enqueue(root);

        Node p;

        while (!q.isEmpty()) {
            p = (Node) q.dequeue();
            if (p.left != null) {
                q.enqueue(p.left);
            }
            if (p.right != null) {
                q.enqueue(p.right);
            }
            visit(p);
        }
    }

    void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void displayInOrder() {
        inOrder(root);
    }

    void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    void displayPostOrder() {
        postOrder(root);
    }

    void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public Node search(Phone x) {
        return search(root, x.getID());
    }

    private Node search(Node p, int x) {
        if (p == null) {
            return (null);
        }

        if (p.info.getID() == x) {
            return (p);
        }

        if (x < p.info.getID()) {
            return (search(p.left, x));
        } else {
            return (search(p.right, x));
        }
    }

    Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public void deleteNode(int x) {
        deleteNode(root, x);
    }

    private Node deleteNode(Node root, int key) {

        if (root == null) {
            return root;
        }

        if (key < root.info.getID()) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.info.getID()) {
            root.right = deleteNode(root.right, key);
        } else {

            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {

                Node temp = minValueNode(root.right);

                root.info.setID(temp.info.getID());

                root.right = deleteNode(root.right, temp.info.getID());
            }
        }

        if (root == null) {
            return root;
        }

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = balanceFactor(root);

        if (balance > 1 && balanceFactor(root.left) >= 0) {
            return rightRotation(root);
        }

        if (balance > 1 && balanceFactor(root.left) < 0) {
            root.left = leftRotation(root.left);
            return rightRotation(root);
        }

        if (balance < -1 && balanceFactor(root.right) <= 0) {
            return leftRotation(root);
        }

        if (balance < -1 && balanceFactor(root.right) > 0) {
            root.right = rightRotation(root.right);
            return leftRotation(root);
        }

        return root;
    }

    public Phone find_Newest_Phone() {
        Node node = find_Newest_Phone(root);
        return node == null ? null : node.info;
    }

    private Node find_Newest_Phone(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone newsestPhone = node.getInfo();
        if (node.left != null) {
            Phone leftNewestPhone = find_Newest_Phone(node.left).getInfo();
            if (leftNewestPhone.getYear() > newsestPhone.getYear()) {
                newsestPhone = leftNewestPhone;
            }
        }
        if (node.right != null) {
            Phone rightNewestPhone = find_Min_price(node.right).getInfo();
            if (rightNewestPhone.getYear() > newsestPhone.getYear()) {
                newsestPhone = rightNewestPhone;
            }
        }
        return new Node(newsestPhone);
    }

    private Node minRightNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node maxLeftNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public void deleteByMerging(int x) {
        if (!contains(x)) {
            System.out.println("Does not exist");
        }
        root = deleteByMergingLeft(root, x);
    }

    private Node deleteByMergingLeft(Node node, int elem) {

        if (elem > node.getInfo().getID()) {
            node.setRight(deleteByMergingLeft(node.right, elem));
        } else if (elem < node.getInfo().getID()) {
            node.setLeft(deleteByMergingLeft(node.left, elem));
        } else {

            if (node.getLeft() == null) {
                Node rightNode = node.getRight();
                node = null;

                return rightNode;

//            } else if (node.getRight() == null) {
//                Node leftNode = node.getLeft();
//                node = null;
//
//                return leftNode;
            } else {

                Node maxLeftPoint = maxLeftNode(node.getLeft());
                maxLeftPoint.setRight(node.getRight());

                node.setInfo(node.getLeft().getInfo());

                node.setRight(null);
                node.setRight(node.getLeft().getRight());

                Node newLeft = node.getLeft().getLeft();
                node.setLeft(null);
                node.setLeft(newLeft);

            }

        }

        node.height = max(height(node.left), height(node.right)) + 1;

        int balance = balanceFactor(node);

        if (balance > 1 && balanceFactor(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotation(node.left);
            return rightRotation(node);
        }

        if (balance < -1 && balanceFactor(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    public void deleteByCopying(int x) {
        if (!contains(x)) {
            System.out.println("Does not exist");
        }

        root = deleteByCopying(root, x);
    }

    private Node deleteByCopying(Node node, int elem) {

        if (elem > node.getInfo().getID()) {
            node.setRight(deleteByCopying(node.getRight(), elem));
        } else if (elem < node.getInfo().getID()) {
            node.setLeft(deleteByCopying(node.getLeft(), elem));
        } else {
            if (node.getLeft() == null) {
                Node rightNode = node.getRight();
                node = null;

                return rightNode;

            } else if (node.getRight() == null) {
                Node leftNode = node.getLeft();
                node = null;

                return leftNode;
            } else {
                Phone term = minRightNode(node.getRight()).getInfo();

                node.setInfo(term);
                node.setRight(deleteByCopying(node.getRight(), term.getID()));

            }
        }

        int balance = balanceFactor(node);
        node.height = max(height(node.left), height(node.right)) + 1;
        if (balance > 1 && balanceFactor(node.left) >= 0) {
            return rightRotation(node);
        }

        if (balance > 1 && balanceFactor(node.left) < 0) {
            node.left = leftRotation(root.left);
            return rightRotation(node);
        }

        if (balance < -1 && balanceFactor(node.right) <= 0) {
            return leftRotation(node);
        }

        if (balance < -1 && balanceFactor(node.right) > 0) {
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }

        return node;
    }

    public Phone find_Max_Value() {
        Node node = find_Max_Value(root);
        return node == null ? null : node.getInfo();
    }

    private Node find_Max_Value(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone maxPhone = node.getInfo();
        if (node.left != null) {
            Phone leftMaxPhone = find_Max_Value(node.left).getInfo();
            if (leftMaxPhone.price * leftMaxPhone.amount > maxPhone.price * maxPhone.amount) {
                maxPhone = leftMaxPhone;
            }
        }
        if (node.right != null) {
            Phone rightMaxPhone = find_Max_Value(node.right).getInfo();
            if (rightMaxPhone.price * rightMaxPhone.amount > maxPhone.price * maxPhone.amount) {
                maxPhone = rightMaxPhone;
            }
        }
        return new Node(maxPhone);
    }

    public Phone find_Min_price() {
        Node node = find_Min_price(root);
        return node == null ? null : node.getInfo();
    }

    private Node find_Min_price(Node node) {
        if (node == null || (node.left == null && node.right == null)) {
            return node;
        }
        Phone minPhone = node.getInfo();
        if (node.left != null) {
            Phone leftMinPhone = find_Min_price(node.left).getInfo();
            if (leftMinPhone.price < minPhone.price) {
                minPhone = leftMinPhone;
            }
        }
        if (node.right != null) {
            Phone rightMinPhone = find_Min_price(node.right).getInfo();
            if (rightMinPhone.price < minPhone.price) {
                minPhone = rightMinPhone;
            }
        }
        return new Node(minPhone);
    }

}
