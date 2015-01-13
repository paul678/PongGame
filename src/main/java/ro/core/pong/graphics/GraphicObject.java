package ro.core.pong.graphics;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

/**
 * Abstract base class for all visible graphical objects
 * Created by Paul Berbec.
 */
public abstract class GraphicObject implements GraphicDrawable {

    protected Rectangle mShape = new Rectangle();

      /* --- Position --- */

    private final DoubleProperty x = new SimpleDoubleProperty(0);
    private final DoubleProperty y = new SimpleDoubleProperty(0);

    @Override
    public DoubleProperty getXProp()
    {
        return x;
    }

    @Override
    public double getX()
    {
        return x.get();
    }

    @Override
    public void setX(double x)
    {
        this.x.set(x);
    }

    @Override
    public DoubleProperty getYProp()
    {
        return y;
    }

    @Override
    public double getY()
    {
        return y.get();
    }

    @Override
    public void setY(double y)
    {
        this.y.set(y);
    }

    @Override
    public Rectangle getShape() {
        return mShape;
    }

    @Override
    public double getHeight() {
        return mShape.getHeight();
    }

    @Override
    public double getWidth() {
        return mShape.getWidth();
    }
}
