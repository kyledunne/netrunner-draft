package main;

import gui.ClickableRectangle;
import gui.Container;
import gui.Style;

/**
 * Created by Kyle on 5/19/2017.
 */
public class DeckCell extends ClickableRectangle {
    private AbstractCard currentDescribedCard = null;

    public DeckCell(Container parent, Style style) {
        super(parent, style);
    }

    @Override
    public void mousedOver() {
        if (!(currentDescribedCard == null)) {
            Draft.triggerPreviewCardEvent(currentDescribedCard, getAbsoluteX(), getAbsoluteY());
        }
    }

    @Override
    public void unMousedOver() {
        if (!(currentDescribedCard == null)) {
            Draft.triggerRemovePreviewedCardEvent();
        }
    }

    @Override
    public void clicked() {

    }

    @Override
    public void released() {

    }

    public void setCurrentDescribedCard(AbstractCard setTo) {
        currentDescribedCard = setTo;
    }
}
