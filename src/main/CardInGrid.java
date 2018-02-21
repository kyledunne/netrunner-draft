package main;

import gui.*;

/**
 * Created by Kyle on 5/16/2017.
 */
public class CardInGrid extends ClickableRectangle {
    private int row, column;

    public CardInGrid(PhysicalCard card, int row, int column) {
        super(Draft.cardGrid, new Image(Main.CARDIMG_HOME + card.getTheAbstractCard().getCode() + ".png", null, Color.WHITE));
        this.row = row;
        this.column = column;
    }

    @Override
    public void mousedOver() {
        if (!CardGrid.selected[row][column]) {
            ((Image)getStyle()).setBindingColor(Color.adjustAColor(Color.WHITE, -0.2f));
        }
    }

    @Override
    public void unMousedOver() {
        if (!CardGrid.selected[row][column]) {
            ((Image)getStyle()).setBindingColor(Color.WHITE);
        }
    }

    @Override
    public void clicked() {
        if (CardGrid.selected[row][column]) {
            CardGrid.selected[row][column] = false;
            ((Image)getStyle()).setBindingColor(Color.WHITE);
        } else {
            CardGrid.selected[row][column] = true;
            ((Image)getStyle()).setBindingColor(CardGrid.currentChoosersColor);
            if (CardGrid.checkForSelectedRowOrColumn()) {
                if (CardGrid.isThisTheSecondChoiceOfTheRound) {
                    CardGrid.addSecondHalfResultsToDeck();
                    if (CardGrid.roundsLeftInThisDraft <= 0) {
                        if (CardGrid.isThisTheCorpDraft) {
                            CardGrid.endCorpDraft();
                        } else {
                            CardGrid.endRunnerDraft();
                        }
                    } else {
                        NewRoundListener.triggerNewRound();
                    }
                } else {
                    CardGrid.addFirstHalfResultsToDeck();
                }
                CardGrid.isThisTheSecondChoiceOfTheRound = !CardGrid.isThisTheSecondChoiceOfTheRound;
            }
        }
    }

    @Override
    public void released() {
    }
}
