package pong.model;


import pong.event.EventBus;
import pong.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Logic for the Pong Game
 * Model class representing the "whole" game
 * Nothing visual here
 *
 */
public class Pong {

    public static final double GAME_WIDTH = 600;
    public static final double GAME_HEIGHT = 400;
    public static final double BALL_SPEED_FACTOR = 1.05;
    public static final long HALF_SEC = 500_000_000;
    private final Random rand = new Random();

    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;
    Ball ball;
    Paddle leftPaddle;
    Paddle rightPaddle;
    Ceiling ceiling;
    Floor floor;



    // TODO Constructor

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions


    public Pong(Paddle rightPaddle, Paddle leftPaddle, Ball ball, Ceiling ceiling, Floor floor) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ball = ball;
        this.ceiling = ceiling;
        this.floor = floor;
    }


    public void resetBall(){
        ball.setX(GAME_WIDTH/2);
        ball.setY(GAME_HEIGHT/2);
        ball.setDx(rand.nextInt(4)-2);
        ball.setDy(rand.nextInt(4)-2);
    }
    public void paddleCollision(){

        double oldYRight = rightPaddle.getY();
        double oldYLeft = leftPaddle.getY();
        leftPaddle.movePaddle();
        rightPaddle.movePaddle();
        if (leftPaddle.getY() < 0 || leftPaddle.getY() > GAME_HEIGHT - Paddle.PADDLE_HEIGHT) {
            leftPaddle.setY(oldYLeft);
        }
        if(rightPaddle.getY() < 0 || rightPaddle.getY() > GAME_HEIGHT - Paddle.PADDLE_HEIGHT){
            rightPaddle.setY(oldYRight);
        }
    }
    public void ballCollision(){
        if(ball.getDx() == 0){
            resetBall();
        }
        if(ball.getDy() == 0){
            resetBall();
        }
        if(ball.intersects(ceiling) || ball.intersects(floor)){
            ball.setDy(-ball.getDy());

        } else if(ball.intersects(leftPaddle) || ball.intersects(rightPaddle)){
            EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE));
            ball.setDx(-ball.getDx() * BALL_SPEED_FACTOR);
            ball.setDy(ball.getDy() * BALL_SPEED_FACTOR);

        }
    }

    public void update(long now) {
        ball.move();
        paddleCollision();
        ballCollision();

        if(ball.getX() < 0 ||ball.getX() > GAME_WIDTH) {
            if (ball.getX() < 0) {
                resetBall();
                pointsRight++;
            } else if (ball.getX() > GAME_WIDTH) {
                resetBall();
                pointsLeft++;
            }
        }
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        drawables.add(this.leftPaddle);
        drawables.add(this.rightPaddle);
        drawables.add(this.ball);

        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;

    }

    public void setSpeedRightPaddle(double dy){
                rightPaddle.setDy(dy);
        }


    public void setSpeedLeftPaddle(double dy) {
            leftPaddle.setDy(dy);
}}
