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
    public static final double BALL_SPEED_FACTOR = 1.05;
    public static final long HALF_SEC = 500_000_000;
    Random random = new Random();


    // TODO More attributes
    private int pointsLeft;
    private int pointsRight;
    Ball ball;
    Paddle rightPaddle;
    Paddle leftPaddle;
    private double dyLeftPad;
    private double dyRightPad;
    private double dxBall;
    private double dyBall;


    // TODO Constructor

    public Pong(Ball ball, Paddle rightPaddle, Paddle leftPaddle) {
        this.ball = ball;
        this.rightPaddle = rightPaddle;
        this.leftPaddle = leftPaddle;

    }

    // --------  Game Logic -------------

    private long timeForLastHit;         // To avoid multiple collisions

    public void update(long now) {
        if (dxBall == 0 || dyBall == 0) {
            dxBall = random.nextInt(4) - 2;
            dyBall = random.nextInt(4) - 2;
        }
        ballBounce();
        padBounce();
        movePaddles();
        ball.moveBall(dxBall, dyBall);
        pointCheck();
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
        dyRightPad = dy;
    }

    public void setSpeedLeftPaddle(double dy) {
        dyLeftPad = dy;
    }

    public void movePaddles() {
        if (leftPaddle.getY() + dyLeftPad + leftPaddle.getHeight() < GAME_HEIGHT && leftPaddle.getY() + dyLeftPad > 0) {
            leftPaddle.movePad(dyLeftPad);
        }
        if (rightPaddle.getY() + dyRightPad + rightPaddle.getHeight() < GAME_HEIGHT && rightPaddle.getY() + dyRightPad > 0) {
            rightPaddle.movePad(dyRightPad);
        }
    }

    public void ballBounce() {
        if (ball.getY() <= 0 | ball.getY() >= GAME_HEIGHT - ball.getHeight()) {
            dyBall *= -1;
        }
    }

    public void padBounce() {
        boolean rightPosOk = rightPaddle.getY() - 30 < ball.getY() && ball.getY() < rightPaddle.getY() + 60;
        boolean leftPosOk = leftPaddle.getY() - 30 < ball.getY() && ball.getY() < leftPaddle.getY() + 60;

        if (ball.getX() <= leftPaddle.getWidth() * 2 && leftPosOk) {
            ballOnPadCol(leftPaddle.getX() + leftPaddle.getWidth());
        } else if (ball.getX() >= rightPaddle.getX() - ball.getWidth() && rightPosOk) {
            ballOnPadCol(rightPaddle.getX() - ball.getWidth());
        }


    }

    public void ballOnPadCol(double offset) {
        ball.setX(offset);
        dxBall *= -1;
        dxBall *= BALL_SPEED_FACTOR;
        dyBall *= BALL_SPEED_FACTOR;
        EventBus.INSTANCE.publish(new ModelEvent(ModelEvent.Type.BALL_HIT_PADDLE));
    }

    public void pointCheck() {
        if (ball.getX() < 0) {
            resetBall();
            pointsRight++;
        } else if (ball.getX() > GAME_WIDTH) {
            resetBall();
            pointsLeft++;
        }

    }

    public void resetBall() {
        ball.setX(GAME_WIDTH / 2 + ball.getWidth() / 2);
        ball.setY(GAME_HEIGHT / 2);
        dxBall = 0;
        dyBall = 0;
    }
}
