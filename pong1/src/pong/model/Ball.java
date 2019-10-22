package pong.model;

import java.util.Random;

import static pong.model.Pong.*;

/*
 * A Ball for the Pong game
 * A model class
 */
public class Ball extends AbstractPosition  {

    public static final double WIDTH = 100;
    public static final double HEIGHT = 100;
    private double dx;
    private double dy;




    public Ball(double x, double y, double dy, double dx){
        super(x,y, WIDTH,HEIGHT);
        this.dx = dx;
        this.dy = dy;
    }


    public void move() {
        setX(getX() + dx);
        setY(getY() + dy);
    }




    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }
}
