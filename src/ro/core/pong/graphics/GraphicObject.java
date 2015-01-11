package ro.core.pong.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract base class for all visible graphical objects
 * Created by Paul Berbec.
 */
public abstract class GraphicObject implements GraphicDrawable {

    protected final GraphicsContext mGraphicContext;
    protected GraphicObject(final GraphicsContext context) {
        this.mGraphicContext = context;
    }
}
