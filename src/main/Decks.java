package main;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Kyle on 5/17/2017.
 */
public class Decks {
    private static DeckInSetOrder p1CorpDeck = new DeckInSetOrder(), p2CorpDeck = new DeckInSetOrder();
    private static DeckInSetOrder p1RunnerDeck = new DeckInSetOrder(), p2RunnerDeck = new DeckInSetOrder();

    public static CorpDeckForDisplay p1CorpDeckForDisplay = new CorpDeckForDisplay(), p2CorpDeckForDisplay = new CorpDeckForDisplay();
    public static RunnerDeckForDisplay p1RunnerDeckForDisplay = new RunnerDeckForDisplay(), p2RunnerDeckForDisplay = new RunnerDeckForDisplay();

    private static boolean wasAnAddToP1CorpDeckEventJustTriggered = false;
    private static boolean wasAnAddToP2CorpDeckEventJustTriggered = false;
    private static boolean wasAnAddToP1RunnerDeckEventJustTriggered = false;
    private static boolean wasAnAddToP2RunnerDeckEventJustTriggered = false;
    private static PhysicalCard[] cardsToAddToP1CorpDeck;
    private static PhysicalCard[] cardsToAddToP2CorpDeck;
    private static PhysicalCard[] cardsToAddToP1RunnerDeck;
    private static PhysicalCard[] cardsToAddToP2RunnerDeck;

    public static void checkForAddToDeckEvents() {
        if (wasAnAddToP1CorpDeckEventJustTriggered) {
            wasAnAddToP1CorpDeckEventJustTriggered = false;
            for (PhysicalCard element: cardsToAddToP1CorpDeck) {
                p1CorpDeck.addToDeck(element.getTheAbstractCard());
                p1CorpDeckForDisplay.addToDeck(element.getTheAbstractCard());
            }
        }
        if (wasAnAddToP2CorpDeckEventJustTriggered) {
            wasAnAddToP2CorpDeckEventJustTriggered = false;
            for (PhysicalCard element: cardsToAddToP2CorpDeck) {
                p2CorpDeck.addToDeck(element.getTheAbstractCard());
                p2CorpDeckForDisplay.addToDeck(element.getTheAbstractCard());
            }
        }
        if (wasAnAddToP1RunnerDeckEventJustTriggered) {
            wasAnAddToP1RunnerDeckEventJustTriggered = false;
            for (PhysicalCard element: cardsToAddToP1RunnerDeck) {
                p1RunnerDeck.addToDeck(element.getTheAbstractCard());
                p1RunnerDeckForDisplay.addToDeck(element.getTheAbstractCard());
            }
        }
        if (wasAnAddToP2RunnerDeckEventJustTriggered) {
            wasAnAddToP2RunnerDeckEventJustTriggered = false;
            for (PhysicalCard element: cardsToAddToP2RunnerDeck) {
                p2RunnerDeck.addToDeck(element.getTheAbstractCard());
                p2RunnerDeckForDisplay.addToDeck(element.getTheAbstractCard());
            }
        }
    }

    public static void addToP1CorpDeck(PhysicalCard[] cardsToAdd) {
        wasAnAddToP1CorpDeckEventJustTriggered = true;
        cardsToAddToP1CorpDeck = cardsToAdd;
    }

    public static void addToP2CorpDeck(PhysicalCard[] cardsToAdd) {
        wasAnAddToP2CorpDeckEventJustTriggered = true;
        cardsToAddToP2CorpDeck = cardsToAdd;
    }

    public static void addToP1RunnerDeck(PhysicalCard[] cardsToAdd) {
        wasAnAddToP1RunnerDeckEventJustTriggered = true;
        cardsToAddToP1RunnerDeck = cardsToAdd;
    }

    public static void addToP2RunnerDeck(PhysicalCard[] cardsToAdd) {
        wasAnAddToP2RunnerDeckEventJustTriggered = true;
        cardsToAddToP2RunnerDeck = cardsToAdd;
    }

    public static void printAllDecksInConsole() {
        System.out.println("");
        System.out.println("Printing Decks:");
        System.out.println("");
        System.out.println("Green corp deck:");
        System.out.println("");
        p1CorpDeck.printToConsole();
        System.out.println("");
        System.out.println("Green runner deck:");
        System.out.println("");
        p1RunnerDeck.printToConsole();
        System.out.println("");
        System.out.println("Yellow corp deck:");
        System.out.println("");
        p2CorpDeck.printToConsole();
        System.out.println("");
        System.out.println("Yellow runner deck:");
        System.out.println("");
        p2RunnerDeck.printToConsole();
    }
}
