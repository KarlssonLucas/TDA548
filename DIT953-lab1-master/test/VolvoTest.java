import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class VolvoTest {

    @Test
    public void canBeInitialized(){
        Volvo240 volvo240 = new Volvo240();
        assertEquals(4,volvo240.getNrDoors());
        assertEquals(Color.black,volvo240.getColor());
        assertEquals(100,volvo240.getEnginePower(),0);
    }

}
