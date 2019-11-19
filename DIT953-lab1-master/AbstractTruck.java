import java.awt.*;

public abstract class AbstractTruck extends AbstractCar {
    private double angleOfBed;

    AbstractTruck(int nrDoors, Color color, int enginePower) {
        super(nrDoors, color, enginePower);
        angleOfBed = 0;
    }

    @Override
    public void move() {
        if (angleOfBed == 0) {
            super.move();
        } else {
            System.out.println("Lower truckbed to drive");
        }
    }

    void raiseRamp(){
        if (angleOfBed < 70 && getCurrentSpeed() == 0){
            angleOfBed += 2;
        }
    }

    void lowerRamp(){
        if (angleOfBed > 0 && getCurrentSpeed() == 0){
            angleOfBed -= 2;
        }
    }

    void setAngleOfBed(int angleOfBed){
        this.angleOfBed = angleOfBed;
    }

    double getAngleOfBed(){
        return angleOfBed;
    }

    @Override
    double speedFactor() {
        return 1;
    }
}
