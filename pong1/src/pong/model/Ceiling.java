package pong.model;
import static pong.model.Pong.GAME_WIDTH;
public class Ceiling extends AbstractPosition{
    public final static double CEILING_HEIGHT = 1;

    public Ceiling(double x, double y){
        super(x,y, GAME_WIDTH, CEILING_HEIGHT);
    }

}
