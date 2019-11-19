import java.awt.*;

public class Volvo240 extends AbstractCar{

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        super(4,Color.black,100);
    }

    /**
     * calculates speedFactor of Volvo
     * @return speedFactor
     */
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}
