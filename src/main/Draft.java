package main;

import gui.*;
import gui.layoutManagers.*;

import javax.json.JsonValue;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by Kyle on 5/16/2017.
 */
public class Draft {
    public static final int ROUNDS = 14;
    public static PriorityQueue<CardForPQ> cardPq = new PriorityQueue<>();
    public static Container cardGrid;

    public static Container cardListsPanel;
    public static ClickableContainer p1CorpCardListGrid;
    public static ClickableContainer p1RunnerCardListGrid;
    public static ClickableContainer p2CorpCardListGrid;
    public static ClickableContainer p2RunnerCardListGrid;

    private static GridLayout p1RunnerCardListGridLayout, p2RunnerCardListGridLayout;

    private static boolean wasAP1SwitchToDisplayingRunnerDeckEventJustTriggered = false;
    private static boolean wasAP1SwitchToDisplayingCorpDeckEventJustTriggered = false;
    private static boolean wasAP2SwitchToDisplayingRunnerDeckEventJustTriggered = false;
    private static boolean wasAP2SwitchToDisplayingCorpDeckEventJustTriggered = false;

    private static boolean wasARemovePreviewedCardEventJustTriggered = false;
    private static boolean wasAPreviewCardEventJustTriggered = false;
    private static AbstractCard cardToPreview;
    private static float previewXCoordinate, previewYCoordinate;

    private static Rectangle previewWindow;
    private static Container previewWindowLayer;

    public static boolean hasP1RunnerDeckForDisplayBeenCreated = false;
    public static boolean hasP2RunnerDeckForDisplayBeenCreated = false;

    public static void beginDraft() {
        try {
            List<String> corpcube = Files.readAllLines(Paths.get("res/corpcube.txt"), Charset.defaultCharset());
            for (String line : corpcube) {
                int number = Integer.parseInt(line.substring(0, 1));
                String title = line.substring(2, line.length());
                AbstractCard thisAbstractCard = new AbstractCard(title);
                //cardsInCube.add(thisAbstractCard);
                for (int i = 0; i < number; i++) {
                    cardPq.add(new CardForPQ(new PhysicalCard(thisAbstractCard)));
                }
            }

            GridLayout cardGridLayout = GridLayout.new4(3, 3, 10, 10, 10, 10, 249, 418f / 300f);
            cardGrid = new Container(GUIMain.WINDOW, null, cardGridLayout);
            GUIMain.WINDOW.addComponent(cardGrid, new CoordinatesLayout.Constraints(0, 0, 787, 1080));
            cardGridLayout.finalize4();

            GridLayout cardListsPanelLayout = GridLayout.new1(2, 1, 10, 10, 10, 10, 10, 10);
            cardListsPanel = new Container(GUIMain.WINDOW, null, cardListsPanelLayout);
            GUIMain.WINDOW.addComponent(cardListsPanel, new CoordinatesLayout.Constraints(787, 0, 1920 - 787, 1080));
            cardListsPanelLayout.finalize1();

            GridLayout p1CorpCardListGridLayout = GridLayout.new1(21, 3, 0, 0, 0, 0, 0, 0);
            p1RunnerCardListGridLayout = GridLayout.new1(21, 3, 0, 0, 0, 0, 0, 0);
            GridLayout p2CorpCardListGridLayout = GridLayout.new1(21, 3, 0, 0, 0, 0, 0, 0);
            p2RunnerCardListGridLayout = GridLayout.new1(21, 3, 0, 0, 0, 0, 0, 0);

            p1CorpCardListGrid = new ClickableContainer(cardListsPanel, null, p1CorpCardListGridLayout) {
                @Override
                public void mousedOver() {
                }

                @Override
                public void unMousedOver() {
                }

                @Override
                public void clicked() {
                    triggerP1SwitchToDisplayingRunnerDeckEvent();
                }

                @Override
                public void released() {

                }
            };

            p1RunnerCardListGrid = new ClickableContainer(null, null, p1RunnerCardListGridLayout) {
                @Override
                public void mousedOver() {
                }

                @Override
                public void unMousedOver() {
                }

                @Override
                public void clicked() {
                    triggerP1SwitchToDisplayingCorpDeckEvent();
                }

                @Override
                public void released() {

                }
            };

            p2CorpCardListGrid = new ClickableContainer(cardListsPanel, null, p2CorpCardListGridLayout) {
                @Override
                public void mousedOver() {

                }

                @Override
                public void unMousedOver() {

                }

                @Override
                public void clicked() {
                    triggerP2SwitchToDisplayingRunnerDeckEvent();
                }

                @Override
                public void released() {

                }
            };

            p2RunnerCardListGrid = new ClickableContainer(null, null, p2RunnerCardListGridLayout) {
                @Override
                public void mousedOver() {

                }

                @Override
                public void unMousedOver() {

                }

                @Override
                public void clicked() {
                    triggerP2SwitchToDisplayingCorpDeckEvent();
                }

                @Override
                public void released() {

                }
            };

            cardListsPanel.addComponent(p1CorpCardListGrid, new GridLayout.Constraints(0, 0));
            cardListsPanel.addComponent(p2CorpCardListGrid, new GridLayout.Constraints(1, 0));
            p1CorpCardListGridLayout.finalize1();
            p2CorpCardListGridLayout.finalize1();

            Decks.p1CorpDeckForDisplay = CorpDeckForDisplay.createP1CorpDeckForDisplay();
            Decks.p2CorpDeckForDisplay = CorpDeckForDisplay.createP2CorpDeckForDisplay();
            Decks.p1RunnerDeckForDisplay = RunnerDeckForDisplay.createP1RunnerDeckForDisplay();
            Decks.p2RunnerDeckForDisplay = RunnerDeckForDisplay.createP2RunnerDeckForDisplay();

            PhysicalCard[][] the9cards = new PhysicalCard[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    the9cards[i][j] = cardPq.remove().getThePhysicalCard();
                }
            }

            previewWindowLayer = new Container(GUIMain.WINDOW, null, new CoordinatesLayout());
            previewWindow = new Rectangle(null, null);
            GUIMain.WINDOW.addLayer(previewWindowLayer);

            CardGrid.startNewRound(the9cards, true);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void beginRunnerDraft() {
        cardPq.clear();
        CardGrid.roundsLeftInThisDraft = 14;
        try {
            List<String> runnercube = Files.readAllLines(Paths.get("res/runnercube.txt"), Charset.defaultCharset());
            for (String line : runnercube) {
                int number = Integer.parseInt(line.substring(0, 1));
                String title = line.substring(2, line.length());
                AbstractCard thisAbstractCard = new AbstractCard(title);
                //cardsInCube.add(thisAbstractCard);
                for (int i = 0; i < number; i++) {
                    cardPq.add(new CardForPQ(new PhysicalCard(thisAbstractCard)));
                }
            }

            PhysicalCard[][] the9cards = new PhysicalCard[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    the9cards[i][j] = cardPq.poll().getThePhysicalCard();
                }
            }

            CardGrid.isThisTheCorpDraft = false;
            CardGrid.startNewRound(the9cards, true);
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void triggerP1SwitchToDisplayingRunnerDeckEvent() {
        wasAP1SwitchToDisplayingRunnerDeckEventJustTriggered = true;
    }

    public static void triggerP1SwitchToDisplayingCorpDeckEvent() {
        wasAP1SwitchToDisplayingCorpDeckEventJustTriggered = true;
    }

    public static void triggerP2SwitchToDisplayingRunnerDeckEvent() {
        wasAP2SwitchToDisplayingRunnerDeckEventJustTriggered = true;
    }

    public static void triggerP2SwitchToDisplayingCorpDeckEvent() {
        wasAP2SwitchToDisplayingCorpDeckEventJustTriggered = true;
    }

    public static void checkForSwitchDeckBeingDisplayedEvents() {
        if (wasAP1SwitchToDisplayingRunnerDeckEventJustTriggered) {
            wasAP1SwitchToDisplayingRunnerDeckEventJustTriggered = false;
            cardListsPanel.removeComponent(p1CorpCardListGrid);
            cardListsPanel.addComponent(p1RunnerCardListGrid, new GridLayout.Constraints(0, 0));
            if (!hasP1RunnerDeckForDisplayBeenCreated) {
                p1RunnerCardListGridLayout.finalize1();
                Decks.p1RunnerDeckForDisplay.p1CallWhenAboutToBeDisplayed();
            }
            triggerRemovePreviewedCardEvent();
        }
        if (wasAP1SwitchToDisplayingCorpDeckEventJustTriggered) {
            wasAP1SwitchToDisplayingCorpDeckEventJustTriggered = false;
            cardListsPanel.removeComponent(p1RunnerCardListGrid);
            cardListsPanel.addComponent(p1CorpCardListGrid, new GridLayout.Constraints(0, 0));
            triggerRemovePreviewedCardEvent();
        }
        if (wasAP2SwitchToDisplayingRunnerDeckEventJustTriggered) {
            wasAP2SwitchToDisplayingRunnerDeckEventJustTriggered = false;
            cardListsPanel.removeComponent(p2CorpCardListGrid);
            cardListsPanel.addComponent(p2RunnerCardListGrid, new GridLayout.Constraints(1, 0));
            if (!hasP2RunnerDeckForDisplayBeenCreated) {
                p2RunnerCardListGridLayout.finalize1();
                Decks.p2RunnerDeckForDisplay.p2CallWhenAboutToBeDisplayed();
            }
            triggerRemovePreviewedCardEvent();
        }
        if (wasAP2SwitchToDisplayingCorpDeckEventJustTriggered) {
            wasAP2SwitchToDisplayingCorpDeckEventJustTriggered = false;
            cardListsPanel.removeComponent(p2RunnerCardListGrid);
            cardListsPanel.addComponent(p2CorpCardListGrid, new GridLayout.Constraints(1, 0));
            triggerRemovePreviewedCardEvent();
        }
    }

    public static void triggerPreviewCardEvent(AbstractCard cardToPreview, float x, float y) {
        wasAPreviewCardEventJustTriggered = true;
        Draft.cardToPreview = cardToPreview;
        Draft.previewXCoordinate = x;
        Draft.previewYCoordinate = y;
    }

    public static void triggerRemovePreviewedCardEvent() {
        wasARemovePreviewedCardEventJustTriggered = true;
    }

    public static final float CARD_WIDTH = 300;
    public static final float CARD_HEIGHT = 418;

    public static void checkForCardPreviewRelatedEvents() {
        if (wasARemovePreviewedCardEventJustTriggered) {
            wasARemovePreviewedCardEventJustTriggered = false;
            previewWindowLayer.removeComponent(previewWindow);
            previewWindowLayer.setStyle(null);
        }
        if (wasAPreviewCardEventJustTriggered) {
            wasAPreviewCardEventJustTriggered = false;
            previewWindow.setStyle(new Image(Main.CARDIMG_HOME + cardToPreview.getCode() + ".png", null, Color.WHITE));
            if (previewYCoordinate + CARD_HEIGHT + 10 > 1080) {
                previewYCoordinate = 1080 - CARD_HEIGHT - 10;
            }
            previewXCoordinate = previewXCoordinate - CARD_WIDTH;
            previewWindowLayer.addComponent(previewWindow, new CoordinatesLayout.Constraints(previewXCoordinate, previewYCoordinate, CARD_WIDTH, CARD_HEIGHT));
            previewWindowLayer.setStyle(Color.adjustAColorsAlpha(Color.BLACK, .5f));
        }
    }
}

