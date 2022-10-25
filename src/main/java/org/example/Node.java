package org.example;

public class Node {
    public static int heightSpace = 100;
    public int widthSpace;
    public int x;
    public int y;

    public Node parent;

    public Node(int x, int y, int widthSpace, Node parent) {
        this.x = x;
        this.y = y;
        this.widthSpace = widthSpace;
        this.parent = parent;
    }
}
