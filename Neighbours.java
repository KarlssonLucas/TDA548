import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

import static java.lang.Math.*;
import static java.lang.System.*;

/*
 *  Program to simulate segregation.
 *  See : http://nifty.stanford.edu/2014/mccown-schelling-model-segregation/
 *
 * NOTE:
 * - JavaFX first calls method init() and then the method start() far below.
 * - The method updateWorld() is called periodically by a Java timer.
 * - To test uncomment call to test() first in init() method!
 *
 */
// Extends Application because of JavaFX (just accept for now)
public class Neighbours extends Application {

    // Enumeration type for the Actors
    enum Actor {
        BLUE, RED, NONE   // NONE used for empty locations
    }

    // Enumeration type for the state of an Actor
    enum State {
        UNSATISFIED,
        SATISFIED,
        NA     // Not applicable (NA), used for NONEs
    }

    // Below is the *only* accepted instance variable (i.e. variables outside any method)
    // This variable may *only* be used in methods init() and updateWorld()
    Actor[][] world;              // The world is a square matrix of Actors

    // This is the method called by the timer to update the world
    // (i.e move unsatisfied) approx each 1/60 sec.
    void updateWorld() {
        Random random = new Random();
        final double threshold = 0.7;   // Percentage off neighbours that are equal to the position you're looking at
        int size = 10000; // size of grid
        int rowLength = (int) sqrt(size);   // amount of rows
        for (int i = 0; i<rowLength; i++) {     // for-loop calculating row
            for (int k = 0; k<rowLength; k++) {     // for-loop calculating column
                if (world[i][k] == Actor.NONE) {    // check if current actor is "none", then continue with next
                    continue;
                }
                int n1 = random.nextInt(rowLength);
                int n = random.nextInt(rowLength);

                Actor A = world[i][k]; // Actor A, our current position we are checking
                Actor B = world[n][n1]; // B, the actor we are comparing with
                boolean p = checkNeighbours(i, k, threshold); // Check how many percentage of the actors around you have the same value
                if (!p) { // If checkNeighbours returned false (p is below threshold)
                    while (B != Actor.NONE) {   // while actor b is not equal to none, because we can only change current actor
                        // with empty actors. If B is not NONE we randomize n and n1 until we reach an empty actor
                        n = random.nextInt(rowLength);
                        n1 = random.nextInt(rowLength);
                        B = world[n][n1];
                    }
                    world[i][k] = world[n][n1]; // Switching actors
                    world[n][n1] = A;
                }
            }
        }
    }

    // This method initializes the world variable with a random distribution of Actors
    // Method automatically called by JavaFX runtime (before graphics appear)
    // Don't care about "@Override" and "public" (just accept for now)
    @Override
    public void init() {
        // %-distribution of RED, BLUE and NONE
        double[] dist = {0.25, 0.25, 0.50};
        // Number of locations (places) in world (square)
        int nLocations = 10000;
        int nRow = (int) sqrt(nLocations);
        Random random = new Random();
        world = new Actor[nRow][nRow];  // Initialize the grid of 30x30 actors

        for (int i = 0; i< nRow; i++) { // For-loop for creating actors that fulfill the requirement of array dist
            for (int k = 0; k < nRow; k++) {
                int n = random.nextInt(4);
                if (n == 0) {
                    world[i][k] = Actor.BLUE;
                } if (n == 1) {
                    world[i][k] = Actor.RED;
                } if ( n == 2 | n == 3) {
                    world[i][k] = Actor.NONE;
                }
            }
        }
        // Should be last
        fixScreenSize(nLocations);
    }


    //---------------- Methods ----------------------------

    // Check if inside world
    boolean isValidLocation(int size, int row, int col) {
        return 0 <= row && row < size &&
                0 <= col && col < size;
    }

    private boolean checkNeighbours(int i, int i1, double t) {
        double sum = 0;
        double div = 0;
        int size = 10000;
        int rowLength = (int) Math.sqrt(size);
        // Variables that are later used in the loop

        Actor A = world[i][i1]; // The actor that you check neighbours for
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dy == 0 && dx == 0) {   // Don't count main actor as a neighbor
                    continue;
                } else if (!isValidLocation(rowLength, i + dx, i1 + dy)) {  //Check valid position (if inside grid)
                    continue;
                }
                Actor N = world[i+dx][i1+dy];
                if (N == Actor.NONE) {  // Check if actor is none (don't count none as neighbour)
                    continue;
                }
                div +=1;    // Everytime you find a neighbour add 1 to div
                if (N == A) {   // If the neighbour have the same value as the main actor, add 1 to sum
                    sum+=1;
                }
                if (div == 0) { // Div can't be zero, meaning no neighbours
                    return false;
                }
            }
        }
        return ((sum/div) > t); // calculate percentage of neighbours and compare to threshold
    }

    // ------- Testing -------------------------------------

    // Here you run your tests i.e. call your logic methods
    // to see that they really work
    void test() {
        // A small hard coded world for testing
        Actor[][] testWorld = new Actor[][]{
                {Actor.RED, Actor.RED, Actor.NONE},
                {Actor.NONE, Actor.BLUE, Actor.NONE},
                {Actor.RED, Actor.NONE, Actor.BLUE}
        };
        double th = 0.5;   // Simple threshold used for testing

        int size = testWorld.length;
        out.println(isValidLocation(size, 0, 0));
        out.println(!isValidLocation(size, -1, 0));
        out.println(!isValidLocation(size, 0, 3));
        out.println(isValidLocation(size, 2, 2));

        // TODO More tests

        exit(0);
    }

    // Helper method for testing (NOTE: reference equality)
    <T> int count(T[] arr, T toFind) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == toFind) {
                count++;
            }
        }
        return count;
    }

    // ###########  NOTHING to do below this row, it's JavaFX stuff  ###########

    double width = 400;   // Size for window
    double height = 400;
    long previousTime = nanoTime();
    final long interval = 450000000;
    double dotSize;
    final double margin = 50;

    void fixScreenSize(int nLocations) {
        // Adjust screen window depending on nLocations
        dotSize = (width - 2 * margin) / sqrt(nLocations);
        if (dotSize < 1) {
            dotSize = 2;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Build a scene graph
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().addAll(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Create a timer
        AnimationTimer timer = new AnimationTimer() {
            // This method called by FX, parameter is the current time
            public void handle(long currentNanoTime) {
                long elapsedNanos = currentNanoTime - previousTime;
                if (elapsedNanos > interval) {
                    updateWorld();
                    renderWorld(gc, world);
                    previousTime = currentNanoTime;
                }
            }
        };

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Segregation Simulation");
        primaryStage.show();

        timer.start();  // Start simulation
    }


    // Render the state of the world to the screen
    public void renderWorld(GraphicsContext g, Actor[][] world) {
        g.clearRect(0, 0, width, height);
        int size = world.length;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                double x = dotSize * col + margin;
                double y = dotSize * row + margin;

                if (world[row][col] == Actor.RED) {
                    g.setFill(Color.RED);
                } else if (world[row][col] == Actor.BLUE) {
                    g.setFill(Color.BLUE);
                } else {
                    g.setFill(Color.WHITE);
                }
                g.fillOval(x, y, dotSize, dotSize);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
