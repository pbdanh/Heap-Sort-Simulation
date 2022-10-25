package org.example;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * JavaFX App
 */
public class App extends Application {

    //jfx component
    private Group root;
    private Scene scene;
    private Canvas canvas;
    private Timeline randomArrayTimeline;
    private Timeline buildTreeTimeline;
    private Timeline heapSortTimeline;

    Queue<Swap> swapQueue;


    int[] arr;
    int sortedElement;

    @Override
    public void start(Stage stage) {
        //javafx-init
        canvas = new Canvas(800, 600);
        root = new Group();
        root.getChildren().add(canvas);
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Heap Sort Simulation");
        stage.show();
        //init renderer
        Renderer.init(canvas.getGraphicsContext2D());
        //handle key event
        ArrayList<String> input = new ArrayList<String>();
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();

                        // only add once... prevent duplicates
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });
        //random array;
        randomArray();
        randomArrayTimeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if(input.contains("R")) {
//                System.out.println("@@");
                input.remove("R");
                randomArray();
            }
            if(input.contains("ENTER")) {
                randomArrayTimeline.stop();
                sortedElement = arr.length;
                buildTreeTimeline.play();

            }
            canvas.getGraphicsContext2D().clearRect(0, 0, 800, 600);
            Renderer.renderArray(arr, sortedElement, Color.WHITE, Color.WHITE);
            Renderer.renderMessage("R = Random || ENTER = Sort");
        }));
        randomArrayTimeline.setCycleCount(Animation.INDEFINITE);
        randomArrayTimeline.play();

        //----------------------------------------------------
        //build tree
        buildTreeTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {

                canvas.getGraphicsContext2D().clearRect(0, 0, 800, 600);
                Renderer.renderArray(arr, sortedElement, Color.LIGHTYELLOW, Color.WHITE);
                Renderer.renderTreeLine(arr, sortedElement);
                Renderer.renderTree(arr, sortedElement);
                sortedElement--;
                if(sortedElement < 0) {
                    buildTreeTimeline.stop();
                    swapQueue = new LinkedList<>();
                    createSwapQueue();
                    heapSortTimeline.play();
                }
                Renderer.renderMessage("BUILD TREE");
        }));
        buildTreeTimeline.setCycleCount(Animation.INDEFINITE);
        //heap sort
        heapSortTimeline = new Timeline(new KeyFrame(Duration.millis(1500), event -> {
            if (!swapQueue.isEmpty()) {
                Swap swap = swapQueue.poll();
                int tmp = arr[swap.i];
                arr[swap.i] = arr[swap.j];
                arr[swap.j] = tmp;
                canvas.getGraphicsContext2D().clearRect(0, 0, 800, 600);
                sortedElement = Math.max(sortedElement, swap.sortedElement);
                Renderer.renderArray(arr, sortedElement, Color.LIGHTYELLOW, Color.LIGHTGREEN);
                Renderer.renderTreeLine(arr, sortedElement);
                if(!swapQueue.isEmpty()){
                    swap = swapQueue.peek();
                    Renderer.renderSwapLine(swap.i, swap.j);
                    Renderer.renderMessage(swap.message);
                }
                Renderer.renderTree(arr, sortedElement);
            } else {
                heapSortTimeline.stop();
                randomArrayTimeline.play();
            }
        }));
        heapSortTimeline.setCycleCount(Animation.INDEFINITE);
    }

    public static void main(String[] args) {
        launch();
    }


    void heapify(int arr[], int n, int i) {
        // Find largest among root, left child and right child
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Swap and continue heapifying if root is not largest
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            swapQueue.add(new Swap(i, largest, 0, "HEAPIFYING"));
            heapify(arr, n, largest);
        }
    }

    public void createSwapQueue() {
        swapQueue.add(new Swap(0, 0, 0, ""));
        int n = arr.length;
        int[] arrClone = new int[n];
        for(int i = 0; i < n; i++) {
            arrClone[i] = arr[i];
        }

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
//
//         Heap sort
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // Heapify root element
            swapQueue.add(new Swap(0, i, n - i, "REPLACE ROOT"));
            heapify(arr, i, 0);
        }
        arr = arrClone;
    }

    public void randomArray() {
        //random array
        int length = (int) (Math.random() * 100000) % 6 + 7;
        arr = new int[length];
        for(int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 100 % 31);
        }
    }
}