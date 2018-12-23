package jdk.util.datastructure.tree.redblack;

public class RedBlackTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BALCK = true;

    private Node root;

    private class Node {
        Key key;
        Value value;
        boolean color;
        Node left, right;

        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public Value get(Key key) {

        Node point = root;
        while (point != null) {
            int result = key.compareTo(point.key);
            if (result == 0) return point.value;
            else if (result < 0) point = point.left;
            else point = point.right;
        }
        return null;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;

        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;

        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;

        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;

        return x;
    }

    private Node colorFlip(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = x.right.color;
        return x;
    }

    private boolean isRed(Node node) {
        return node.color == RED;
    }

    public Node insert(Node h, Key key, Value value) {

        if (h == null) {
            return new Node(key, value, RED);   //insert new node at the bottom
        }

        if (isRed(h.left) && isRed(h.right)) {
            colorFlip(h);   //  split 4-nodes on the way down
        }

        int cmp = key.compareTo(h.key);
        if (cmp == 0) h.value = value;
        else if (cmp < 0)
            h.left = insert(h.left, key, value);
        else
            h.right = insert(h.left, key, value);   //standard BST insert node

        if (isRed(h.right))
            h = rotateLeft(h);      //fix right-leaning reds on the way up
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);     //fix two reds in a row on the way up
        return h;

    }

}
