package main;

import gui.*;
import gui.layoutManagers.*;

/**
 * Created by Kyle on 5/16/2017.
 */
public class CardGrid {
    public static PhysicalCard[][] the9cards;
    public static boolean[][] selected = new boolean[3][3];
    public static boolean[][] removed = new boolean[3][3];
    public static boolean isPlayer1CurrentlyChoosing;
    public static PhysicalCard[][] results;
    public static Color currentChoosersColor;
    public static CardInGrid[][] cardsInGrid;
    public static final Color FIRST_COLOR = Color.GREEN;
    public static final Color SECOND_COLOR = Color.YELLOW;
    public static boolean isThisTheSecondChoiceOfTheRound;
    public static int roundsLeftInThisDraft = 14;
    public static boolean isThisTheCorpDraft = true;

    public static void startNewRound(PhysicalCard[][] the9cards, boolean isPlayer1CurrentlyChoosing) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                selected[i][j] = false;
                removed[i][j] = false;
            }
        }
        results = new PhysicalCard[2][3];
        cardsInGrid = new CardInGrid[3][3];

        roundsLeftInThisDraft--;
        CardGrid.the9cards = the9cards;
        CardGrid.isPlayer1CurrentlyChoosing = isPlayer1CurrentlyChoosing;
        if (isPlayer1CurrentlyChoosing) {
            currentChoosersColor = FIRST_COLOR;
        } else {
            currentChoosersColor = SECOND_COLOR;
        }
        isThisTheSecondChoiceOfTheRound = false;

        Draft.cardGrid.empty();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cardsInGrid[i][j] = new CardInGrid(the9cards[i][j], i, j);
                Draft.cardGrid.addComponent(cardsInGrid[i][j], new GridLayout.Constraints(i, j));
            }
        }
    }

    public static boolean checkForSelectedRowOrColumn() {
        if (removed[0][0]) {
            if (removed[0][1]) {
                if (selected[1][0] && selected[2][0]) {
                    results[1][0] = the9cards[1][0];
                    results[1][1] = the9cards[2][0];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][1] && selected[2][1]) {
                    results[1][0] = the9cards[1][1];
                    results[1][1] = the9cards[2][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][2] && selected[2][2]) {
                    results[1][0] = the9cards[1][2];
                    results[1][1] = the9cards[2][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][0] && selected[1][1] && selected[1][2]) {
                    results[1][0] = the9cards[1][0];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = the9cards[1][2];
                    return true;
                } else if (selected[2][0] && selected[2][1] && selected[2][2]) {
                    results[1][0] = the9cards[2][0];
                    results[1][1] = the9cards[2][1];
                    results[1][2] = the9cards[2][2];
                    return true;
                } else {
                    return false;
                }
            } else {
                if (selected[0][1] && selected[0][2]) {
                    results[1][0] = the9cards[0][1];
                    results[1][1] = the9cards[0][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][1] && selected[1][2]) {
                    results[1][0] = the9cards[1][1];
                    results[1][1] = the9cards[1][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[2][1] && selected[2][2]) {
                    results[1][0] = the9cards[2][1];
                    results[1][1] = the9cards[2][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][1] && selected[1][1] && selected[2][1]) {
                    results[1][0] = the9cards[0][1];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = the9cards[2][1];
                    return true;
                } else if (selected[0][2] && selected[1][2] && selected[2][2]) {
                    results[1][0] = the9cards[0][2];
                    results[1][1] = the9cards[1][2];
                    results[1][2] = the9cards[2][2];
                    return true;
                } else {
                    return false;
                }
            }
        } else if (removed[1][1]) {
            if (removed[1][0]) {
                if (selected[0][0] && selected[2][0]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[2][0];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][1] && selected[2][1]) {
                    results[1][0] = the9cards[0][1];
                    results[1][1] = the9cards[2][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][2] && selected[2][2]) {
                    results[1][0] = the9cards[0][2];
                    results[1][1] = the9cards[2][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][0] && selected[0][1] && selected[0][2]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[0][1];
                    results[1][2] = the9cards[0][2];
                    return true;
                } else if (selected[2][0] && selected[2][1] && selected[2][2]) {
                    results[1][0] = the9cards[2][0];
                    results[1][1] = the9cards[2][1];
                    results[1][2] = the9cards[2][2];
                    return true;
                } else {
                    return false;
                }
            } else {
                if (selected[0][0] && selected[0][2]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[0][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][0] && selected[1][2]) {
                    results[1][0] = the9cards[1][0];
                    results[1][1] = the9cards[1][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[2][0] && selected[2][2]) {
                    results[1][0] = the9cards[2][0];
                    results[1][1] = the9cards[2][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][0] && selected[1][0] && selected[2][0]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[1][0];
                    results[1][2] = the9cards[2][0];
                    return true;
                } else if (selected[0][2] && selected[1][2] && selected[2][2]) {
                    results[1][0] = the9cards[0][2];
                    results[1][1] = the9cards[1][2];
                    results[1][2] = the9cards[2][2];
                    return true;
                } else {
                    return false;
                }
            }
        } else if (removed[2][2]) {
            if (removed[2][0]) {
                if (selected[0][0] && selected[1][0]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[1][0];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][1] && selected[1][1]) {
                    results[1][0] = the9cards[0][1];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][2] && selected[1][2]) {
                    results[1][0] = the9cards[0][2];
                    results[1][1] = the9cards[1][2];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][0] && selected[0][1] && selected[0][2]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[0][1];
                    results[1][2] = the9cards[0][2];
                    return true;
                } else if (selected[1][0] && selected[1][1] && selected[1][2]) {
                    results[1][0] = the9cards[1][0];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = the9cards[1][2];
                    return true;
                } else {
                    return false;
                }
            } else {
                if (selected[0][0] && selected[0][1]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[0][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[1][0] && selected[1][1]) {
                    results[1][0] = the9cards[1][0];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[2][0] && selected[2][1]) {
                    results[1][0] = the9cards[2][0];
                    results[1][1] = the9cards[2][1];
                    results[1][2] = null;
                    return true;
                } else if (selected[0][0] && selected[1][0] && selected[2][0]) {
                    results[1][0] = the9cards[0][0];
                    results[1][1] = the9cards[1][0];
                    results[1][2] = the9cards[2][0];
                    return true;
                } else if (selected[0][1] && selected[1][1] && selected[2][1]) {
                    results[1][0] = the9cards[0][1];
                    results[1][1] = the9cards[1][1];
                    results[1][2] = the9cards[2][1];
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            if (selected[0][0]) {
                if (selected[0][1] && selected[0][2]) {
                    results[0][0] = the9cards[0][0];
                    results[0][1] = the9cards[0][1];
                    results[0][2] = the9cards[0][2];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(true, 0);
                    return true;
                } else if (selected[1][0] && selected[2][0]) {
                    results[0][0] = the9cards[0][0];
                    results[0][1] = the9cards[1][0];
                    results[0][2] = the9cards[2][0];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(false, 0);
                    return true;
                } else {
                    return false;
                }
            } else if (selected[1][1]) {
                if (selected[1][0] && selected[1][2]) {
                    results[0][0] = the9cards[1][0];
                    results[0][1] = the9cards[1][1];
                    results[0][2] = the9cards[1][2];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(true, 1);
                    return true;
                } else if (selected[0][1] && selected[2][1]) {
                    results[0][0] = the9cards[0][1];
                    results[0][1] = the9cards[1][1];
                    results[0][2] = the9cards[2][1];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(false, 1);
                    return true;
                } else {
                    return false;
                }
            } else if (selected[2][2]) {
                if (selected[2][0] && selected[2][1]) {
                    results[0][0] = the9cards[2][0];
                    results[0][1] = the9cards[2][1];
                    results[0][2] = the9cards[2][2];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(true, 2);
                    return true;
                } else if (selected[0][2] && selected[1][2]) {
                    results[0][0] = the9cards[0][2];
                    results[0][1] = the9cards[1][2];
                    results[0][2] = the9cards[2][2];
                    RowOrColumnRemovalListener.triggerRowOrColumnRemovalEvent(false, 2);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void removeARowOrColumn(boolean isItARowBeingRemovedAsOpposedToAColumn, int rowOrColumnNumber) {
        if (isItARowBeingRemovedAsOpposedToAColumn) {
            if (rowOrColumnNumber == 0) {
                removed[0][0] = true;
                removed[0][1] = true;
                removed[0][2] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[0][0]);
                Draft.cardGrid.removeComponent(cardsInGrid[0][1]);
                Draft.cardGrid.removeComponent(cardsInGrid[0][2]);
                unselectAll();
                switchCurrentChooser();
            } else if (rowOrColumnNumber == 1) {
                removed[1][0] = true;
                removed[1][1] = true;
                removed[1][2] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[1][0]);
                Draft.cardGrid.removeComponent(cardsInGrid[1][1]);
                Draft.cardGrid.removeComponent(cardsInGrid[1][2]);
                unselectAll();
                switchCurrentChooser();
            } else {
                removed[2][0] = true;
                removed[2][1] = true;
                removed[2][2] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[2][0]);
                Draft.cardGrid.removeComponent(cardsInGrid[2][1]);
                Draft.cardGrid.removeComponent(cardsInGrid[2][2]);
                unselectAll();
                switchCurrentChooser();
            }
        } else {
            if (rowOrColumnNumber == 0) {
                removed[0][0] = true;
                removed[1][0] = true;
                removed[2][0] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[0][0]);
                Draft.cardGrid.removeComponent(cardsInGrid[1][0]);
                Draft.cardGrid.removeComponent(cardsInGrid[2][0]);
                unselectAll();
                switchCurrentChooser();
            } else if (rowOrColumnNumber == 1) {
                removed[0][1] = true;
                removed[1][1] = true;
                removed[2][1] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[0][1]);
                Draft.cardGrid.removeComponent(cardsInGrid[1][1]);
                Draft.cardGrid.removeComponent(cardsInGrid[2][1]);
                unselectAll();
                switchCurrentChooser();
            } else {
                removed[0][2] = true;
                removed[1][2] = true;
                removed[2][2] = true;
                Draft.cardGrid.removeComponent(cardsInGrid[0][2]);
                Draft.cardGrid.removeComponent(cardsInGrid[1][2]);
                Draft.cardGrid.removeComponent(cardsInGrid[2][2]);
                unselectAll();
                switchCurrentChooser();
            }
        }
    }

    private static void unselectAll() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (selected[i][j]) {
                    if (!removed[i][j]) {
                        ((Image)cardsInGrid[i][j].getStyle()).setBindingColor(Color.WHITE);
                    }
                }
                selected[i][j] = false;
            }
        }
    }

    private static void switchCurrentChooser() {
        if (isPlayer1CurrentlyChoosing) {
            isPlayer1CurrentlyChoosing = false;
            currentChoosersColor = SECOND_COLOR;
        } else {
            isPlayer1CurrentlyChoosing = true;
            currentChoosersColor = FIRST_COLOR;
        }
    }

    public static void addSecondHalfResultsToDeck() {
        if (isThisTheCorpDraft) {
            if (!isPlayer1CurrentlyChoosing) {
                PhysicalCard[] cardsToAddToP2sCorpDeck;
                if (results[1][2] == null) {
                    cardsToAddToP2sCorpDeck = new PhysicalCard[2];
                    cardsToAddToP2sCorpDeck[0] = results[1][0];
                    cardsToAddToP2sCorpDeck[1] = results[1][1];
                } else {
                    cardsToAddToP2sCorpDeck = results[1];
                }
                Decks.addToP2CorpDeck(cardsToAddToP2sCorpDeck);
            } else {
                PhysicalCard[] cardsToAddToP1sCorpDeck;
                if (results[1][2] == null) {
                    cardsToAddToP1sCorpDeck = new PhysicalCard[2];
                    cardsToAddToP1sCorpDeck[0] = results[1][0];
                    cardsToAddToP1sCorpDeck[1] = results[1][1];
                } else {
                    cardsToAddToP1sCorpDeck = results[1];
                }
                Decks.addToP1CorpDeck(cardsToAddToP1sCorpDeck);
            }
        } else {
            if (!isPlayer1CurrentlyChoosing) {
                PhysicalCard[] cardsToAddToP2sRunnerDeck;
                if (results[1][2] == null) {
                    cardsToAddToP2sRunnerDeck = new PhysicalCard[2];
                    cardsToAddToP2sRunnerDeck[0] = results[1][0];
                    cardsToAddToP2sRunnerDeck[1] = results[1][1];
                } else {
                    cardsToAddToP2sRunnerDeck = results[1];
                }
                Decks.addToP2RunnerDeck(cardsToAddToP2sRunnerDeck);
            } else {
                PhysicalCard[] cardsToAddToP1sRunnerDeck;
                if (results[1][2] == null) {
                    cardsToAddToP1sRunnerDeck = new PhysicalCard[2];
                    cardsToAddToP1sRunnerDeck[0] = results[1][0];
                    cardsToAddToP1sRunnerDeck[1] = results[1][1];
                } else {
                    cardsToAddToP1sRunnerDeck = results[1];
                }
                Decks.addToP1RunnerDeck(cardsToAddToP1sRunnerDeck);
            }
        }
    }

    public static void addFirstHalfResultsToDeck() {
        if (isThisTheCorpDraft) {
            if (isPlayer1CurrentlyChoosing) {
                PhysicalCard[] cardsToAddToP1sCorpDeck = results[0];
                Decks.addToP1CorpDeck(cardsToAddToP1sCorpDeck);
            } else {
                PhysicalCard[] cardsToAddToP2sCorpDeck = results[0];
                Decks.addToP2CorpDeck(cardsToAddToP2sCorpDeck);
            }
        } else {
            if (isPlayer1CurrentlyChoosing) {
                PhysicalCard[] cardsToAddToP1sRunnerDeck = results[0];
                Decks.addToP1RunnerDeck(cardsToAddToP1sRunnerDeck);
            } else {
                PhysicalCard[] cardsToAddToP2sRunnerDeck = results[0];
                Decks.addToP2RunnerDeck(cardsToAddToP2sRunnerDeck);
            }
        }

    }

    public static void endCorpDraft() {
        BeginRunnerDraftListener.triggerABeginRunnerDraftEvent();
        Draft.triggerP1SwitchToDisplayingRunnerDeckEvent();
        Draft.triggerP2SwitchToDisplayingRunnerDeckEvent();
    }

    public static void endRunnerDraft() {
        Decks.printAllDecksInConsole();
        System.exit(0);
    }
}