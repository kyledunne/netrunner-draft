package main;

import gui.ClickableRectangle;
import gui.Color;
import gui.Text;
import gui.layoutManagers.GridLayout;

import java.util.ArrayList;

/**
 * Created by Kyle on 5/18/2017.
 */
public class RunnerDeckForDisplay {
    private ArrayList<AbstractCardWithNumberInDeck> icebreakers = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> programs = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> hardware = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> events = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> resources = new ArrayList<>();

    public static final int CELL_HEIGHT = 25;
    public static final int CELL_WIDTH = 371;

    private DeckCell[][] cells = new DeckCell[21][3];
    private Text[][] cellStyles = new Text[21][3];
    private String[] cellStrings = new String[63];
    private AbstractCard[] abstractCards = new AbstractCard[63];
    private boolean p1sDeck;

    public static RunnerDeckForDisplay createP1RunnerDeckForDisplay() {
        RunnerDeckForDisplay p1RunnerDeckForDisplay = new RunnerDeckForDisplay();
        p1RunnerDeckForDisplay.p1sDeck = true;
        p1RunnerDeckForDisplay.generateCellStrings();
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                p1RunnerDeckForDisplay.cellStyles[i][j] = new Text(p1RunnerDeckForDisplay.cellStrings[j * 21 + i], CorpDeckForDisplay.DECK_DISPLAY_FONT, Color.GREEN_GRASS);
                p1RunnerDeckForDisplay.cells[i][j] = new DeckCell(null, p1RunnerDeckForDisplay.cellStyles[i][j]);
            }
        }
        return p1RunnerDeckForDisplay;
    }

    public static RunnerDeckForDisplay createP2RunnerDeckForDisplay() {
        RunnerDeckForDisplay p2RunnerDeckForDisplay = new RunnerDeckForDisplay();
        p2RunnerDeckForDisplay.p1sDeck = false;
        p2RunnerDeckForDisplay.generateCellStrings();
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                p2RunnerDeckForDisplay.cellStyles[i][j] = new Text(p2RunnerDeckForDisplay.cellStrings[j * 21 + i], CorpDeckForDisplay.DECK_DISPLAY_FONT, Color.YELLOW);
                p2RunnerDeckForDisplay.cells[i][j] = new DeckCell(null, p2RunnerDeckForDisplay.cellStyles[i][j]);
            }
        }
        return p2RunnerDeckForDisplay;
    }

    public void p1CallWhenAboutToBeDisplayed() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                Draft.p1RunnerCardListGrid.addComponent(cells[i][j], new GridLayout.Constraints(i, j));
            }
        }
    }

    public void p2CallWhenAboutToBeDisplayed() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                Draft.p2RunnerCardListGrid.addComponent(cells[i][j], new GridLayout.Constraints(i, j));
            }
        }
    }

    public void addToDeck(AbstractCard cardToAdd) {
        String type = cardToAdd.getType();
        if (type.equals("Hardware")) {
            addToList(cardToAdd, hardware);
        } else if (type.equals("Event")) {
            addToList(cardToAdd, events);
        } else if (type.equals("Resource")) {
            addToList(cardToAdd, resources);
        } else if (type.equals("Program")) {
            String subtype = cardToAdd.getSubtype();
            if (subtype.contains("Icebreaker")) {
                addToList(cardToAdd, icebreakers);
            } else {
                addToList(cardToAdd, programs);
            }
        } else {
            throw new RuntimeException("Card does not have known type??? Type is " + type);
        }
        generateCellStrings();
        setCellStylesToCellStrings();
    }

    private void generateCellStrings() {
        int counter = 0;
        if (p1sDeck) {
            cellStrings[counter] = "GREEN Runner Deck:";
        } else {
            cellStrings[counter] = "YELLOW Runner Deck:";
        }
        abstractCards[counter] = null;
        counter++;
        cellStrings[counter] = "----Icebreakers----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < icebreakers.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = icebreakers.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Other Programs----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < programs.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = programs.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Hardware----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < hardware.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = hardware.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Events----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < events.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = events.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Resources----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < resources.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = resources.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        for (int i = counter; i < 63; i++) {
            cellStrings[i] = "";
            abstractCards[i] = null;
        }
    }

    private void setCellStylesToCellStrings() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                cellStyles[i][j].setString(cellStrings[j * 21 + i]);
                cells[i][j].setCurrentDescribedCard(abstractCards[j * 21 + i]);
            }
        }
    }

    private void addToList(AbstractCard ac, ArrayList<AbstractCardWithNumberInDeck> list) {
        int length = list.size();
        boolean success = false;
        for (int i = 0; i < length; i++) {
            if (list.get(i).getTheCard().equals(ac)) {
                list.get(i).setNumberInDeck(list.get(i).getNumberInDeck() + 1);
                success = true;
                break;
            }
        }
        if (!success) {
            list.add(new AbstractCardWithNumberInDeck(ac, 1));
        }
    }
}
