package pong.model;

import static pong.model.Pong.GAME_HEIGHT;
import static pong.model.Pong.GAME_WIDTH;

/*
 * A Paddle for the Pong game
 * A model class
 *
 */
public class Paddle extends AbstractPos {

    public static final double PADDLE_WIDTH = 10;
    public static final double PADDLE_HEIGHT = 60;
    public static final double PADDLE_SPEED = 5;
    private double x;
    private double y;

    public Paddle(double x, double y) {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }

    public void movePad(double dy){
        moveY(dy);
    }

}
