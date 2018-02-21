package main;

/**
 * Created by Kyle on 5/18/2017.
 */
public class AbstractCardWithNumberInDeck implements Comparable<AbstractCardWithNumberInDeck> {
    private AbstractCard theCard;
    private int numberInDeck;

    public AbstractCardWithNumberInDeck(AbstractCard theCard, int numberInDeck) {
        this.theCard = theCard;
        this.numberInDeck = numberInDeck;
    }

    public AbstractCard getTheCard() {
        return theCard;
    }

    public int getNumberInDeck() {
        return numberInDeck;
    }

    public void setNumberInDeck(int numberInDeck) {
        this.numberInDeck = numberInDeck;
    }

    @Override
    public int compareTo(AbstractCardWithNumberInDeck cardToCompareTo) {
        int thisCode = Integer.parseInt(theCard.getCode());
        int thatCode = Integer.parseInt(cardToCompareTo.getTheCard().getCode());
        return (thisCode < thatCode)? -1: 1;
    }
}
