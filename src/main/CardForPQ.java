package main;

import gui.Util;

/**
 * Created by Kyle on 5/23/2016.
 */
public class CardForPQ implements Comparable {
    private float priority;
    private PhysicalCard thePhysicalCard;

    public CardForPQ(PhysicalCard thePhysicalCard) {
        this.thePhysicalCard = thePhysicalCard;
        this.priority = Util.RAND.nextFloat();
    }

    @Override
    public int compareTo(Object o) {
        return (priority - ((CardForPQ) o).getPriority() < 0)? -1: 1;
    }

    public PhysicalCard getThePhysicalCard() {
        return thePhysicalCard;
    }

    public float getPriority() {
        return priority;
    }
}
