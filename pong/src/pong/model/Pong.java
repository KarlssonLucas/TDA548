package pong.model;


import pong.event.ModelEvent;
import pong.event.EventBus;
import pong.model.Paddle.*;

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
    public static final double BALL_SPEED_FACTOR = 1.5;
    public static final long HALF_SEC = 250_000_000;
    Random random = new Random();


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;
    Ball ball;
    Paddle rightPaddle;
    Paddle leftPaddle;
    private double dxBall;
    private double dyBall;


    // TODO Constructor

    public Pong(Ball ball,Paddle rightPaddle,Paddle leftPaddle) {
        this.ball = ball;
        this.rightPaddle = rightPaddle;
        this.leftPaddle = leftPaddle;

    }

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        if (dxBall == 0 || dyBall == 0){
            dxBall = random.nextInt(4)-2;
            dyBall = random.nextInt(4)-2;
        }
        ballBounce();
        padBounce();
        pointCheck();
        ball.moveBall(dxBall,dyBall);
    }


    // --- Used by GUI  ------------------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> drawables = new ArrayList<>();
        // TODO
        drawables.add(this.ball);
        drawables.add(this.rightPaddle);
        drawables.add(this.leftPaddle);
        return drawables;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public int getPointsRight() {
        return pointsRight;
    }

    public void setSpeedRightPaddle(double dy) {
        if (rightPaddle.getY() + dy + rightPaddle.getHeight() < GAME_HEIGHT && rightPaddle.getY() + dy > 0) {
            rightPaddle.movePad(dy);
        }
    }

    public void setSpeedLeftPaddle(double dy) {
        if (leftPaddle.getY() + dy + leftPaddle.getHeight() < GAME_HEIGHT && leftPaddle.getY() + dy > 0) {
            leftPaddle.movePad(dy);
        }
    }

    public void ballBounce(){
        double posY = ball.getY();
        if (posY <= 0 | posY >= GAME_HEIGHT-ball.getHeight()){
            dyBall *= -1;
        }
    }

    public void padBounce(){
        double paddleY=1000;
        double ballX = ball.getX();
        double ballY = ball.getY();

        if (ballX <= 0+leftPaddle.getWidth()){
            paddleY = leftPaddle.getY();
        } else if (ballX >= GAME_WIDTH-ball.getWidth()-rightPaddle.getWidth()){
            paddleY = rightPaddle.getY();
        }

        if (paddleY-30 < ballY && ballY < paddleY + 60 ) {
            if (dxBall < 0){
                ball.moveBall(3,0);
            } else {
                ball.moveBall(-3,0);
            }
            dxBall *= -1;
            dxBall *= BALL_SPEED_FACTOR;
            dyBall *= BALL_SPEED_FACTOR;
            EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE));
        }


    }

    public void pointCheck(){
        if (ball.getX() < 0){
            resetBall();
            pointsRight++;
        }else if (ball.getX()>GAME_WIDTH){
            resetBall();
            pointsLeft++;
        }

    }

    public void resetBall(){
         this.ball = new Ball(GAME_WIDTH/2-ball.getWidth()/2,GAME_HEIGHT/2);
         dxBall = random.nextInt(4)-2;
         dyBall = random.nextInt(4)-2;
    }
}
