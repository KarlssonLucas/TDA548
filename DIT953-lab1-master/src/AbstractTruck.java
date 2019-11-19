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

    void rampSteer(){
        if (angleOfBed == 70){
            angleOfBed = 0;
        } else angleOfBed = 70;
    }
    double getAngleOfBed(){
        return angleOfBed;
    }

    @Override
    double speedFactor() {
        return 1;
    }
}
