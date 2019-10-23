package pong.model;

import static pong.model.Paddle.*;


public abstract class AbstractPos implements IPositionable {

    private double x;
    private double y;
    private double width;
    private double height;
    private double dy;

    public AbstractPos(double x, double y, double width, double height) {
        this.x = x;
        this.y = y-height;
        this.width = width;
        this.height = height;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double moveY(double dy){
        y += dy;
        return y;
    }

    public double moveX(double dx){
        x += dx;
        return x;
    }

}
