package main;

import gui.*;
import gui.layoutManagers.CoordinatesLayout;
import gui.layoutManagers.GridLayout;
import javax.imageio.ImageIO;
import javax.json.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Kyle on 5/19/2016.
 */
public class Main {
    public static String CARDIMG_HOME = "res/cardimgs/";
    public static JsonObject CARD_DATA;

    public static void init() {
        try {
            CARD_DATA = Json.createReader(new FileInputStream("res/cardData.txt")).readObject();
        } catch (Exception e) { e.printStackTrace(); }
        Draft.beginDraft();
    }

    public static void checkEvents() {
        Input.checkInputs();
        NewRoundListener.checkForNewRoundEvent();
        RowOrColumnRemovalListener.checkForRowOrColumnRemovalEvent();
        BeginRunnerDraftListener.checkForBeginRunnerDraftEvent();
        Decks.checkForAddToDeckEvents();
        Draft.checkForSwitchDeckBeingDisplayedEvents();
        Draft.checkForCardPreviewRelatedEvents();
    }

    public static void render() {
        GUIMain.WINDOW.draw();
    }

    private static void downloadNewCardImages() {
        System.out.println("Downloading new card images. This may take a few moments");
        try {
            File cardDataFile = new File("Netrunner Draft/res/cardData.txt");
            InputStream inputStream = new FileInputStream(cardDataFile);
            JsonReader jsonReader = Json.createReader(inputStream);
            JsonArray cardData = jsonReader.readArray();

            // TrustManager code allows receiving images from https websites
            // Create a new trust manager that trust all certificates
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(
                                java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            };
            // Activate the new trust manager
            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            } catch (Exception e) { e.printStackTrace(); }

            for (JsonObject card : cardData.getValuesAs(JsonObject.class)) {
                String code = card.getString("code");
                if (!Files.exists(Paths.get(CARDIMG_HOME + code + ".png"))) {
                    System.out.println("Downloading " + card.getString("title") + "'s image");
                    String imageName = CARDIMG_HOME + code + ".png";
                    String imagesrc = card.get("imagesrc").toString();
                    imagesrc = imagesrc.substring(1, imagesrc.length() - 1);
                    BufferedImage img = ImageIO.read(new URL("https://netrunnerdb.com" + imagesrc));
                    ImageIO.write(img, "png", new File(imageName));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    /** Run this method after downloading a new cardData file from netrunnerDB */
    private static void modifyCardDataFile() {
        try {
            JsonArray cardData = Json.createReader(new FileInputStream("Netrunner Draft/res/cardData.txt")).readArray();
            JsonObjectBuilder ob = Json.createObjectBuilder();
            for (JsonObject card: cardData.getValuesAs(JsonObject.class)) {
                String title = card.get("title").toString();
                title = title.substring(1, title.length() - 1);
                ob.add(title, card);
            }
            Json.createWriter(new FileOutputStream("Netrunner Draft/res/cardData.txt")).writeObject(ob.build());
        } catch (Exception e) { e.printStackTrace(); }
    }

    /** run this after downloading an updated cardData.txt */
    private static void netrunnerDBUpdate() {
        downloadNewCardImages();
        modifyCardDataFile();
        System.exit(0);
    }
}
