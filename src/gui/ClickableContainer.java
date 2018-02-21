package gui;

/**
 * Created by Kyle on 4/30/2016.
 */
public abstract class ClickableContainer extends Container implements Clickable {
    private ClickableListener listener;

    public ClickableContainer(Container parent, Style style, LayoutManager layoutManager) {
        super(parent, style, layoutManager);
        listener = new ClickableListener(this);
    }

    @Override
    public float[] getBounds() {
        return ClickableRectangle.getBounds(this);
    }

    @Override
    public ClickableListener getListener() {
        return listener;
    }

    @Override
    public void activate() {
        super.activate();
        listener.addMouseListener();
    }

    @Override
    public void deactivate() {
        super.deactivate();
        listener.removeMouseListener();
    }
}
