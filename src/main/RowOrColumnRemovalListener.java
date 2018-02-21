package main;

/**
 * Created by Kyle on 5/17/2017.
 */
public class RowOrColumnRemovalListener {
    private static boolean wasARowOrColumnRemovalEventJustTriggered = false;
    private static boolean isItARowBeingRemovedAsOpposedToAColumn;
    private static int rowOrColumnNumber;

    public static void triggerRowOrColumnRemovalEvent(boolean isItARowBeingRemovedAsOpposedToAColumn, int rowOrColumnNumber) {
        wasARowOrColumnRemovalEventJustTriggered = true;
        RowOrColumnRemovalListener.isItARowBeingRemovedAsOpposedToAColumn = isItARowBeingRemovedAsOpposedToAColumn;
        RowOrColumnRemovalListener.rowOrColumnNumber = rowOrColumnNumber;
    }

    public static void checkForRowOrColumnRemovalEvent() {
        if (wasARowOrColumnRemovalEventJustTriggered) {
            wasARowOrColumnRemovalEventJustTriggered = false;
            CardGrid.removeARowOrColumn(isItARowBeingRemovedAsOpposedToAColumn, rowOrColumnNumber);
        }
    }
}
