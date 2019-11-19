import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CarTransport extends AbstractTruck {
    private ArrayList<AbstractCar> carLoad = new ArrayList<>();

    CarTransport() {
        super(2, Color.green, 100);
    }

    void loadCar(AbstractCar car){
        if (isInRange(car.getX(),getX()-10,getX()+10) &&
            isInRange(car.getY(),getY()-10,getY()+10)){

        }

    }

    boolean isInRange(double carPos,double truckPosMin,double truckPosMax){
        if (carPos > truckPosMin && carPos < truckPosMax ){
            return true;
        } else return false;
    }
}
