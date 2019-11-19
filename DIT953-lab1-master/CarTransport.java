import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarTransport extends AbstractTruck {
    private ArrayList<AbstractCar> carLoad = new ArrayList<>();

    CarTransport() {
        super(2, Color.green, 100);
    }

    @Override
    void raiseRamp() {
        setAngleOfBed(70);
    }

    @Override
    void lowerRamp() {
        setAngleOfBed(0);
    }

    void loadCar(AbstractCar car){
        if (isInRange(car.getX(),getX()-10,getX()+10) &&
            isInRange(car.getY(),getY()-10,getY()+10) && getAngleOfBed() == 0){

        }

    }

    boolean isInRange(double carPos,double truckPosMin,double truckPosMax){
        return carPos > truckPosMin && carPos < truckPosMax;
    }
}
