package pong.model;

import static pong.model.Pong.GAME_HEIGHT;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle extends AbstractPosition{

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;
    private double dy;


    public Paddle(double x, double y, double width, double height, double dy) {
        super(x,y, width, height);
        this.dy = dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void movePaddle() {
        setY(getY() + dy);
    }

}
