import java.awt.*;

public abstract class AbstractCar implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private double xPos;
    private double yPos;
    private double dy = 1;
    private  double dx = 0;

    public AbstractCar(int nrDoors, Color color, int enginePower) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        xPos = 0;
        yPos = 0;
    }

    @Override
    public void move() {
        setX(xPos + currentSpeed * dx);
        setY(yPos + currentSpeed * dy);
    }

    @Override
    public void turnLeft() {
        double temp = dx;
        dx = dy * -1;
        dy = temp;
    }

    @Override
    public void turnRight() {
        double temp = dy;
        dy = dx * -1;
        dx = temp;

    }

    public void setY(double y){
        yPos = y;
    }

    public void setX(double x){
        xPos = x;
    }

    public double getX(){
        return xPos;
    }

    public double getY(){
        return yPos;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public double speedFactor(){return 0;}

    public void incrementSpeed(double amount){
        if (currentSpeed + speedFactor() * amount <= enginePower ) {
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        }
    }

    public void decrementSpeed(double amount){
        if (currentSpeed - speedFactor() * amount > 0) {
            currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        } else {
            currentSpeed = 0;
        }
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount < 1 && amount > 0) {
            incrementSpeed(amount);
        }
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount < 1 && amount > 0){
            decrementSpeed(amount);
        }
    }
}
