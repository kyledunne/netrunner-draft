package main;

/**
 * Created by Kyle on 5/17/2017.
 */
public class NewRoundListener {
    private static boolean wasANewRoundEventJustTriggered = false;
    public static void triggerNewRound() {
        wasANewRoundEventJustTriggered = true;
    }

    public static void checkForNewRoundEvent() {
        if (wasANewRoundEventJustTriggered) {
            wasANewRoundEventJustTriggered = false;
            PhysicalCard[][] the9cards = new PhysicalCard[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    the9cards[i][j] = Draft.cardPq.poll().getThePhysicalCard();
                }
            }
            CardGrid.startNewRound(the9cards, CardGrid.isPlayer1CurrentlyChoosing);
        }
    }
}
