package ro.core.pong.graphics;

import javafx.beans.property.DoubleProperty;

/**
 * Created by Paul Berbec.
 */
public interface GraphicDrawable {

    DoubleProperty getYProp();
    double getY();
    void setY(double y);

    DoubleProperty getXProp();
    double getX();
    void setX(double x);

    void update(double deltaTime);
}
