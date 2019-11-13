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

    /**
     *The constructor for car
     * @param nrDoors number of doors on the car
     * @param color color of the car
     * @param enginePower top speed/power of car
     */
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

    /**
     * sets y-position of the car
     * @param y new y-position of the car
     */
    public void setY(double y){
        yPos = y;
    }

    /**
     * set the x-position of the car
     * @param x new x-position of car
     */
    public void setX(double x){
        xPos = x;
    }

    /**
     * gets the x-position of the car
     * @return the x-position of car
     */
    public double getX(){
        return xPos;
    }

    /**
     * gets the y-position of the car
     * @return the y-position of the car
     */
    public double getY(){
        return yPos;
    }

    /**
     * gets the number of doors of the car
     * @return number of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * gets the engine power of the car
     * @return engine power
     */

    public double getEnginePower(){
        return enginePower;
    }

    /**
     * gets the current speed of the car
     * @return current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * gets the color of the car
     * @return color
     */
    public Color getColor(){
        return color;
    }

    /**
     * sets the color of the car
     * @param clr new color of the car
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * starts the engine
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     * stops the engine
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * calculates the speed factor of car
     * is always overridden
     * @return check subclass
     */
    public double speedFactor(){
        return 1;
    }

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
        if (amount < 1 && amount > 0) {
            incrementSpeed(amount);
        }
    }

    /**
     * sanity checks for in decrementSpeed
     * @param amount factor for calculating new speed
     */
    // TODO fix this method according to lab pm
    void brake(double amount){
        if (amount < 1 && amount > 0){
            decrementSpeed(amount);
        }
    }
}
