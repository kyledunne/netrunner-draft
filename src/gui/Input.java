package gui;

import org.lwjgl.input.Mouse;
import java.util.LinkedList;

/**
 * Created by Kyle on 4/29/2016.
 */
public class Input {
    private static int mouseX, mouseY;
    private static int lastFrameMouseX = 0, lastFrameMouseY = 0;
    private static boolean isLeftMouseButtonDown = false, wasLeftMouseButtonDown;

    private static LinkedList<MouseListener> mouseListeners = new LinkedList<>();

    public static void checkInputs() {
        lastFrameMouseX = mouseX;
        lastFrameMouseY = mouseY;
        mouseX = Mouse.getX();
        mouseY = GUIMain.getWindowHeight() - Mouse.getY();
        wasLeftMouseButtonDown = isLeftMouseButtonDown;
        isLeftMouseButtonDown = Mouse.isButtonDown(0);
        boolean mouseMoved = (lastFrameMouseX != mouseX) || (lastFrameMouseY != mouseY);
        boolean mousePressed = (!wasLeftMouseButtonDown) && isLeftMouseButtonDown;
        boolean mouseReleased = wasLeftMouseButtonDown && (!isLeftMouseButtonDown);
        if (mouseMoved) {
            for (MouseListener listener: mouseListeners) {
                listener.mouseMoved();
            }
        }
        if (mousePressed) {
            for (MouseListener listener: mouseListeners) {
                listener.mousePressed();
            }
        }
        if (mouseReleased) {
            for (MouseListener listener: mouseListeners) {
                listener.mouseReleased();
            }
        }
    }

    public static void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public static void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static void setMouseX(int mouseX) {
        Input.mouseX = mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static void setMouseY(int mouseY) {
        Input.mouseY = mouseY;
    }

    public static int getLastFrameMouseX() {
        return lastFrameMouseX;
    }

    public static void setLastFrameMouseX(int lastFrameMouseX) {
        Input.lastFrameMouseX = lastFrameMouseX;
    }

    public static int getLastFrameMouseY() {
        return lastFrameMouseY;
    }

    public static void setLastFrameMouseY(int lastFrameMouseY) {
        Input.lastFrameMouseY = lastFrameMouseY;
    }

    public static boolean isLeftMouseButtonDown() {
        return isLeftMouseButtonDown;
    }

    public static void setIsLeftMouseButtonDown(boolean isLeftMouseButtonDown) {
        Input.isLeftMouseButtonDown = isLeftMouseButtonDown;
    }

    public static boolean isWasLeftMouseButtonDown() {
        return wasLeftMouseButtonDown;
    }

    public static void setWasLeftMouseButtonDown(boolean wasLeftMouseButtonDown) {
        Input.wasLeftMouseButtonDown = wasLeftMouseButtonDown;
    }
}
