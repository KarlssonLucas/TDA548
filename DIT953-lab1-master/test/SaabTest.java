import org.junit.Test;
import java.awt.*;
import static org.junit.Assert.*;

public class SaabTest {
    @Test
    public void canBeInitialized(){
        Saab95 saab95 = new Saab95();
        assertEquals(4,saab95.getNrDoors());
        assertEquals(Color.red,saab95.getColor());
        assertEquals(125,saab95.getEnginePower(),0);
    }

    @Test
    public void canChangeColor(){
        Saab95 saab95 = new Saab95();
        Color clr = Color.cyan;
        saab95.setColor(clr);
        assertEquals(clr,saab95.getColor());
    }

    @Test
    public void canChangeSpeed(){
        Saab95 saab95 = new Saab95();
        double speedStart = saab95.getCurrentSpeed();
        saab95.startEngine();
        saab95.gas(1);
        double speedGas = saab95.getCurrentSpeed();
        saab95.brake(1);
        double speedBreak = saab95.getCurrentSpeed();
        assertEquals(speedStart,0,0);
        assertTrue(speedStart < speedGas);
        assertTrue(speedGas > speedBreak);
    }

    @Test
    public void canMove(){
        Saab95 saab95 = new Saab95();
        saab95.startEngine();
        double x = saab95.getX();
        double y = saab95.getY();
        saab95.move();
        assertTrue(y < saab95.getY());
    }

    @Test
    public void canTurn(){
        Saab95 saab95 = new Saab95();
        saab95.turnRight();
        assertTrue(saab95.getDx() != 0);
        saab95.turnLeft();
        assertTrue(saab95.getDy() != 0);
    }

    @Test
    public void CanTurnTurboOnAndOff(){
        Saab95 saab95 = new Saab95();
        double speedfactorTurboOff = saab95.speedFactor();
        saab95.setTurboOn();
        double speedFactorTurboOn = saab95.speedFactor();
        saab95.setTurboOff();
        assertTrue(speedfactorTurboOff < speedFactorTurboOn);
    }


}
