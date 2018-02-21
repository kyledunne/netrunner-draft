package main;

import gui.ClickableRectangle;
import gui.Color;
import gui.Text;
import gui.layoutManagers.GridLayout;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kyle on 5/18/2017.
 */
public class CorpDeckForDisplay {
    private ArrayList<AbstractCardWithNumberInDeck> ice = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> barriers = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> codeGates = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> sentries = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> agendas = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> operations = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> assets = new ArrayList<>();
    private ArrayList<AbstractCardWithNumberInDeck> upgrades = new ArrayList<>();

    public static final Font DECK_DISPLAY_FONT = new Font("Verdana", 0, 22);

    public static final int CELL_HEIGHT = 25;
    public static final int CELL_WIDTH = 371;

    public DeckCell[][] cells = new DeckCell[21][3];
    public Text[][] cellStyles = new Text[21][3];
    private String[] cellStrings = new String[63];
    private AbstractCard[] abstractCards = new AbstractCard[63];
    private boolean p1sDeck;

    public static CorpDeckForDisplay createP1CorpDeckForDisplay() {
        CorpDeckForDisplay p1CorpDeckForDisplay = new CorpDeckForDisplay();
        p1CorpDeckForDisplay.p1sDeck = true;
        p1CorpDeckForDisplay.generateCellStrings();
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                p1CorpDeckForDisplay.cellStyles[i][j] = new Text(p1CorpDeckForDisplay.cellStrings[j * 21 + i], DECK_DISPLAY_FONT, Color.GREEN_GRASS);
                p1CorpDeckForDisplay.cells[i][j] = new DeckCell(Draft.p1CorpCardListGrid, p1CorpDeckForDisplay.cellStyles[i][j]);
                Draft.p1CorpCardListGrid.addComponent(p1CorpDeckForDisplay.cells[i][j], new GridLayout.Constraints(i, j));
            }
        }
        return p1CorpDeckForDisplay;
    }

    public static CorpDeckForDisplay createP2CorpDeckForDisplay() {
        CorpDeckForDisplay p2CorpDeckForDisplay = new CorpDeckForDisplay();
        p2CorpDeckForDisplay.p1sDeck = false;
        p2CorpDeckForDisplay.generateCellStrings();
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 3; j++) {
                p2CorpDeckForDisplay.cellStyles[i][j] = new Text(p2CorpDeckForDisplay.cellStrings[j * 21 + i], DECK_DISPLAY_FONT, Color.YELLOW);
                p2CorpDeckForDisplay.cells[i][j] = new DeckCell(Draft.p2CorpCardListGrid, p2CorpDeckForDisplay.cellStyles[i][j]);
                Draft.p2CorpCardListGrid.addComponent(p2CorpDeckForDisplay.cells[i][j], new GridLayout.Constraints(i, j));
            }
        }
        return p2CorpDeckForDisplay;
    }

    public void addToDeck(AbstractCard cardToAdd) {
        String type = cardToAdd.getType();
        if (type.equals("Agenda")) {
            addToList(cardToAdd, agendas);
        } else if (type.equals("Operation")) {
            addToList(cardToAdd, operations);
        } else if (type.equals("Asset")) {
            addToList(cardToAdd, assets);
        } else if (type.equals("Upgrade")) {
            addToList(cardToAdd, upgrades);
        } else if (type.equals("ICE")) {
            String subtype = cardToAdd.getSubtype();
            if (subtype.contains("Barrier")) {
                if (subtype.contains("Code Gate") || subtype.contains("Sentry")) {
                    addToList(cardToAdd, ice);
                } else {
                    addToList(cardToAdd, barriers);
                }
            } else if (subtype.contains("Code Gate")) {
                if (subtype.contains("Sentry")) {
                    addToList(cardToAdd, ice);
                } else {
                    addToList(cardToAdd, codeGates);
                }
            } else if (subtype.contains("Sentry")) {
                addToList(cardToAdd, sentries);
            } else {
                addToList(cardToAdd, ice);
            }
        } else {
            throw new RuntimeException("Card does not have known type??? Type is " + type);
        }
        generateCellStrings();
        setCellStylesToCellStrings();
    }

    private void setTextTo(int row, int column, String string) {
        cellStyles[row][column].setString(string);
    }

    private void generateCellStrings() {
        int counter = 0;
        if (p1sDeck) {
            cellStrings[counter] = "GREEN Corp Deck:";
        } else {
            cellStrings[counter] = "YELLOW Corp Deck:";
        }
        abstractCards[counter] = null;
        counter++;
        cellStrings[counter] = "----Barriers----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < barriers.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = barriers.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Code Gates----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < codeGates.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = codeGates.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Sentries----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < sentries.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = sentries.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Miscellaneous ICE----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < ice.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = ice.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Agendas----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < agendas.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = agendas.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Operations----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < operations.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = operations.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Assets----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < assets.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = assets.get(i);
            cellStrings[counter] = acwnid.getNumberInDeck() + "x " + acwnid.getTheCard().getName();
            abstractCards[counter] = acwnid.getTheCard();
            counter++;
        }
        cellStrings[counter] = "----Upgrades----";
        abstractCards[counter] = null;
        counter++;
        for (int i = 0; i < upgrades.size(); i++) {
            AbstractCardWithNumberInDeck acwnid = upgrades.get(i);
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
