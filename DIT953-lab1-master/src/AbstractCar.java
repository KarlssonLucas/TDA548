import java.awt.*;

public abstract class AbstractCar implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private double xPos;
    private double yPos;
    private boolean engineOn;
    private double dy = 1;
    private  double dx = 0;

    /**
     *The constructor for car
     * @param nrDoors number of doors on the car
     * @param color color of the car
     * @param enginePower top speed/power of car
     */
    AbstractCar(int nrDoors, Color color, int enginePower) {
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

    private void setY(double y){
        yPos = y;
    }

    private void setX(double x){
        xPos = x;
    }

    double getX(){
        return xPos;
    }

    double getY(){
        return yPos;
    }

    double getDy(){
        return dy;
    }

    double getDx(){
        return dx;
    }

    int getNrDoors(){
        return nrDoors;
    }

    double getEnginePower(){
        return enginePower;
    }

    double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    /**
     * starts the engine
     */
    void startEngine(){
        currentSpeed = 0.1;
        engineOn = true;
    }

    /**
     * stops the engine
     */
    void stopEngine(){
        currentSpeed = 0;
        engineOn = false;
    }

    /**
     * calculates the speed factor of car
     * is always overridden
     * @return check subclass
     */
    abstract double speedFactor();

    /**
     * calculates the new speed of car
     * @param amount factor of acceleration
     */
    private void incrementSpeed(double amount){
        if (currentSpeed + speedFactor() * amount <= enginePower ) {
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        }
    }

    /**
     * calculates the new speed of car
     * @param amount factor of retardation
     */
    private void decrementSpeed(double amount){
        if (currentSpeed - speedFactor() * amount > 0) {
            currentSpeed = getCurrentSpeed() - speedFactor() * amount;
        } else {
            currentSpeed = 0;
        }
    }

    /**
     * sanity checks for incrementSpeed
     * @param amount factor for calculating new speed
     */
    // TODO fix this method according to lab pm
    void gas(double amount){
        if (amount <= 1 && amount >= 0 && engineOn) {
            incrementSpeed(amount);
        }
    }

    /**
     * sanity checks for in decrementSpeed
     * @param amount factor for calculating new speed
     */
    // TODO fix this method according to lab pm
    void brake(double amount){
        if (amount <= 1 && amount >= 0 && engineOn){
            decrementSpeed(amount);
        }
    }
}
