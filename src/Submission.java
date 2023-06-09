//3 failures right now as is.
import java.util.Scanner;

class Node {
    int key, count;
    Node left, right;
}

class Tree {

    public Node deleteNode(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = deleteNode(node.left, key);
        } else if (key > node.key) {
            node.right = deleteNode(node.right, key);
        } else {
            if (node.count > 1) {
                node.count--;
                return node;
            } else if (node.left == null || node.right == null) {
                Node t;
                t = node.left == null ? node.right : node.left;

                return t;

            } else {
                Node lower = getLower(node);
                node.key = lower.key;
                deleteNode(node.right, lower.key);
                return node;
            }
        }
        return node;
    }

    public Node lookFor(Node node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (node.key < key) {
            return lookFor(node.right, key);
        }
        return lookFor(node.left,  key);

    }

    public Node getLower(Node node) {
        if (node == null) {
            return null;
        }
        Node r = node.right;
        while (r.left != null) {
            r = r.left;
        }
        return r;
    }

    public Node createNode(int key) {
        Node temp = new Node();
        temp.key = key;
        temp.left = null;
        temp.right = null;
        temp.count = 1;
        return temp;
    }

    public Node insertNode(Node node, int key) {
        //if the tree is empty then return a new node to be the head of the tree.
        if (node == null) {
            return createNode(key);
        }
        //if the key already exists then increment the count and return.
        if (key == node.key) {
            (node.count)++;
            return node;
        }
        //if it isn't then look down the tree
        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else {
            node.right = insertNode(node.right, key);
        }
        return node;

    }

    public Node findBiggest(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node findSmallest(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.print(node.key + "(" + node.count + ")");
        printInOrder(node.right);
    }

    public void printPostOrder(Node node) {
        if (node == null) {
            return;
        }
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.key + "(" + node.count + ")");
    }

    public void printPreOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.key + "(" + node.count + ")");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

}

public class Submission {
    public static void main(String[] args) {

        Tree t = new Tree();
        Scanner scanner = new Scanner(System.in);
        int input, key;
        Node n = null;

        while (true) {
            input = scanner.nextInt();
            //System.out.println(input);
            switch (input) {
                default -> System.exit(0);
                case 1 -> {
                    key = scanner.nextInt();
                    n = t.insertNode(n, key);
                }
                case 2 -> {
                    key = scanner.nextInt();
                    Node temp1 = t.lookFor(n, key);
                    if (temp1 == null) {
                        System.out.print(key + "(0)");
                    } else {
                        System.out.print(temp1.key + "(" + temp1.count + ")");
                    }
                }
                case 3 -> {
                    Node temp = t.findBiggest(n);
                    if (temp == null) {
                        System.out.print("0(0)");
                    } else {
                        System.out.print(temp.key + "(" + temp.count + ")");
                    }
                }
                case 4 -> {
                    Node temp2 = t.findSmallest(n);
                    if (temp2 == null) {
                        System.out.print("0(0)");
                    } else {
                        System.out.print(temp2.key + "(" + temp2.count + ")");
                    }
                }
                case 5 -> t.printPreOrder(n);
                case 6 -> t.printPostOrder(n);
                case 7 -> t.printInOrder(n);
                case 8 -> {
                    key = scanner.nextInt();
                    n = t.deleteNode(n, key);
                }
            }
        }
    }
}