package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Renderer {

    private static Node[] nodes = new Node[1000];

    private static GraphicsContext gc;

    public static void init(GraphicsContext gc) {
        Renderer.gc = gc;
        nodes[0] = new Node(400, 200, 200, null);
        for (int i = 0; i < 10; i++) {
            nodes[2 * i + 1] = new Node(nodes[i].x - nodes[i].widthSpace,
                    nodes[i].y + Node.heightSpace, nodes[i].widthSpace / 2, nodes[i]);
            nodes[2 * i + 2] = new Node(nodes[i].x + nodes[i].widthSpace,
                    nodes[i].y + Node.heightSpace, nodes[i].widthSpace / 2, nodes[i]);
        }
        try {
            Font font = Font.loadFont(new FileInputStream("src/main/resources/roboto/Roboto-Bold.ttf"), 25);
            gc.setFont(font);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void renderArray(int arr[], int sortedSplit, Color unsortedColor, Color sortedColor) {
        for (int i = 0; i < arr.length; i++) {
            if(i < arr.length - sortedSplit) {
                gc.setFill(unsortedColor);
            }
            else {
                gc.setFill(sortedColor);
            }
            gc.fillRect(50 + i * 60, 100 - 33, 50, 50);
            gc.setStroke(Color.BLACK);
            gc.strokeRect(50 + i * 60, 100 - 33, 50, 50);
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(arr[i]), 55 + i * 60 + 5, 100);
        }
    }

    public static void renderTree(int arr[], int sortedElement) {

        for (int i = 0; i < arr.length - sortedElement; i++) {
            gc.setFill(Color.LIGHTGRAY);
            gc.fillOval(nodes[i].x - 25, nodes[i].y - 25, 50, 50);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);
            gc.strokeOval(nodes[i].x - 25, nodes[i].y - 25, 50, 50);
            gc.setFill(Color.BLACK);
            gc.fillText(String.valueOf(arr[i]), nodes[i].x - 13, nodes[i].y + 5);
            //


        }
    }

    public static void renderTreeLine(int arr[], int sortedElement) {
        for(int i = 0; i < arr.length - sortedElement; i++) {
            if (nodes[i].parent != null) {
                gc.setStroke(Color.BLACK);
                gc.strokeLine(nodes[i].parent.x, nodes[i].parent.y, nodes[i].x, nodes[i].y);
            }
        }
    }

    public static void renderSwapLine(int i, int j) {
        if(i!= j) {
            gc.setStroke(Color.RED);
            gc.setLineWidth(3);
            gc.strokeLine(nodes[i].x, nodes[i].y, nodes[j].x, nodes[j].y);
        }
    }

    public static void renderMessage(String message) {
        gc.fillText(message, 450, 575);
    }
}
