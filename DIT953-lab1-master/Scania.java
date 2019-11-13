import java.awt.*;

public class Scania extends AbstractCar implements Trucks {
    private double angleOfTruckBed = 0;

    public Scania(){
        super(4, Color.white,125);
    }


    @Override
    public void move() {
        if (angleOfTruckBed == 0){
            super.move();
        }
    }


    @Override
    public void raiseTruckBed() {
        if (getCurrentSpeed() == 0) {
            angleOfTruckBed += 5;
        }
    }

    @Override
    public void lowerTruckBed() {
        if (getCurrentSpeed() == 0){
            angleOfTruckBed -= 5;
        }
    }

    @Override
    public double getAngleOfBed() {
        return angleOfTruckBed;
    }
}
