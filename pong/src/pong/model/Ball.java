package pong.model;

import java.awt.*;
import java.util.Random;


/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball extends AbstractPos {
    public static final double BAll_SIZE = 40;

    public Ball(double x, double y) {
        super(x, y,BAll_SIZE ,BAll_SIZE);
    }

    public void moveBall(double dx, double dy){
       moveX(dx);
       moveY(dy);
    }
}
