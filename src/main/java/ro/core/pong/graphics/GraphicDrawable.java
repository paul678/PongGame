package ro.core.pong.graphics;

import javafx.beans.property.DoubleProperty;

/**
 * Created by Paul Berbec.
 */
public interface GraphicDrawable extends PlanarObject{

    DoubleProperty getYProp();
    DoubleProperty getXProp();

    void update(double deltaTime);
}
