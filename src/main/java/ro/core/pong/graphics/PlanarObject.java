package ro.core.pong.graphics;

import javafx.scene.shape.Shape;

/**
 * Created by CORE on 1/13/2015.
 */
public interface PlanarObject {

    double getY();
    void setY(double y);

    double getX();
    void setX(double x);

    Shape getShape();

    double getHeight();
    double getWidth();
}
