package org.example;

/*
* 5) Create binary tree implementation (type of data: int):
    a) Create method which insert elements in your BT and return to you filled/ready binary tree.
    * You can send simple java array
    b) create method which find  value in BT if it’s exist
    c) create method which delete value in BT if it’s exist
*/

public class BinaryTree {
    private Node root;
    BinaryTree(){
        root = null;
    }

    static class Node{
        int value;
        Node left;
        Node right;
        Node(int value){
            this.value = value;
            left = null;
            right = null;
        }
        public void displayData(){
            System.out.print(value + " ");
        }
    }

    public void insert(int i){
        root = insert(root, i);
    }

    public Node insert(Node node, int value){
        if(node == null) return new Node(value);

        if(value < node.value){
            node.left = insert(node.left, value);
        } else if(value > node.value){
            node.right = insert(node.right, value);
        }
        return node;
    }

    public Node find(int searchedValue){
        Node current = root;
        while(current.value != searchedValue){
            if(searchedValue < current.value)
                current = current.left;
            else
                current = current.right;
            if(current == null){
                return null;
            }
        }
        System.out.println("Your value is found: ");
        return current;
    }

    public void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            node.displayData();
            inOrder(node.right);
        }
    }

    public boolean delete(int value){
        Node current = root;
        Node parent = root;
        boolean isLeftChild = false;
        while(current.value != value){
            parent = current;
            if(value < current.value){
                current = current.left;
                isLeftChild = true;
            }
            else{
                current = current.right;
                isLeftChild = false;
            }
            if(current == null){
                return false;
            }
        }

        if(current.left == null && current.right == null){
            System.out.println("Value deleted");
            if(current == root){
                root = null;
            }
            else if(isLeftChild){
                parent.left = null;
            }
            else{
                parent.right = null;
            }
        } else if(current.left == null){
            System.out.println("Value deleted");

            if(current == root){
                root = current.right;
            }
            else if(isLeftChild){
                parent.left = current.right;
            }
            else{
                parent.right = current.right;
            }
        }
        else if(current.right == null){
            System.out.println("Value deleted");
            if(current == root){
                root = current.left;
            }
            else if(isLeftChild){
                parent.left = current.left;
            }
            else{
                parent.right = current.left;
            }
        }
        else{
            System.out.println("Value deleted");
            Node successor = findSuccessor(current);
            if(current == root){
                root = successor;
            }
            else if(isLeftChild){
                parent.left = successor;
            }
            else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }
    private Node findSuccessor(Node node){
        Node successor = node;
        Node successorParent = node;
        Node current = node.right;
        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if(successor != node.right){
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(70);
        tree.insert(15);
        tree.insert(35);
        tree.insert(30);
        tree.insert(31);
        tree.insert(8);
        tree.insert(99);
        System.out.println("Binary tree");
        tree.inOrder(tree.root);
        System.out.println();
        Node node = tree.find(30);
        System.out.println((node == null)? "Node not found" : String.valueOf(node.value));
        tree.delete(8);
        System.out.println("Binary tree after value deleted");
        tree.inOrder(tree.root);
    }
}