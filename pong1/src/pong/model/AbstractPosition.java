package pong.model;

public abstract class AbstractPosition implements  IPositionable {

    private double x;
    private double y;
    private double width;
    private double height;

    public AbstractPosition(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(AbstractPosition other) {
        boolean above = other.getMaxY() < getY();
        boolean below = other.getY() > getMaxY();
        boolean leftOf = other.getMaxX() < getX();
        boolean rightOf = other.getX() > getMaxX();
        return !(above || below || leftOf || rightOf);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getMaxX() {
        return x + width;
    }

    public double getMaxY() {
        return y + height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}

