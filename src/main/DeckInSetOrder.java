package main;

import java.util.PriorityQueue;

/**
 * Created by Kyle on 5/18/2017.
 */
public class DeckInSetOrder {
    private PriorityQueue<AbstractCardWithNumberInDeck> deck = new PriorityQueue<>();

    public void addToDeck(AbstractCard cardToAdd) {
        Object[] allElements = deck.toArray();
        boolean isAlreadyInDeck = false;
        for (Object elementAsObject: allElements) {
            AbstractCardWithNumberInDeck element = (AbstractCardWithNumberInDeck)elementAsObject;
            if (element.getTheCard().equals(cardToAdd)) {
                element.setNumberInDeck(element.getNumberInDeck() + 1);
                isAlreadyInDeck = true;
                break;
            }
        }
        if (!isAlreadyInDeck) {
            deck.add(new AbstractCardWithNumberInDeck(cardToAdd, 1));
        }
    }

    public void printToConsole() {
        while (!deck.isEmpty()) {
            AbstractCardWithNumberInDeck currentElement = deck.remove();
            System.out.println(currentElement.getNumberInDeck() + "x " + currentElement.getTheCard().getName());
        }
    }
}
