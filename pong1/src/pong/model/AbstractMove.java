package pong.model;

public abstract class AbstractMove extends AbstractPosition {
    private double dy;

    public AbstractMove(double x, double y, double dy, double width, double height) {
        super(x, y, width, height);
        this.dy = dy;
    }




    public void setDy(double dy) {
        this.dy = dy;
    }

}
