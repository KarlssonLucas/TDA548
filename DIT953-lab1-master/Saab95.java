import java.awt.*;

public class Saab95 extends AbstractCar{

    public boolean turboOn;
    
    public Saab95(){
        super(4,Color.red,125);
        stopEngine();
    }
    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }

}
