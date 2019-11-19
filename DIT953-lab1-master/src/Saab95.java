import java.awt.*;

public class Saab95 extends AbstractCar{

    private boolean turboOn;

    public Saab95(){
        super(4,Color.red,125);
        stopEngine();
    }

    void setTurboOn(){
	    turboOn = true;
    }

    void setTurboOff(){
	    turboOn = false;
    }

    /**
     * calculates speedFactor of saab
     * @return speedFactor
     */
    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}
