package main;

/**
 * Created by Kyle on 5/16/2017.
 */
public class PhysicalCard {
    private AbstractCard theAbstractCard;

    public PhysicalCard(AbstractCard theAbstractCard) {
        this.theAbstractCard = theAbstractCard;
    }

    public AbstractCard getTheAbstractCard() {
        return theAbstractCard;
    }
}
