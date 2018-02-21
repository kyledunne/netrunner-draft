package main;

import javax.json.JsonObject;
import javax.json.JsonValue;

import gui.Image;
import main.Main;

/** This represents i.e. "Hedge Fund". The PhysicalCard class represents a physical copy of an AbstractCard */
public class AbstractCard {
    private String title;
    private JsonObject data;
    private Image image;

    public AbstractCard(String title) {
        this.title = title;
        data = Main.CARD_DATA.getJsonObject(title);
        String code = data.getString("code");
        System.out.println(code);
        image = new Image(Main.CARDIMG_HOME + code + ".png", null, null);
    }

    public Image getImage() {
        return image;
    }

    public String getCode() {
        return data.getString("code");
    }

    public String getName() {
        return title;
    }

    public JsonObject getData() {
        return data;
    }

    public String getType() {
        return data.getString("type");
    }

    public String getSubtype() {
        JsonValue subtype = data.get("subtype");
        String subtypeString;
        if (subtype == null) {
            subtypeString = "[N/A]";
        } else {
            String subtypeStringTemp = subtype.toString();
            subtypeString = subtypeStringTemp.substring(1, subtypeStringTemp.length() - 1);
        }
        return subtypeString;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AbstractCard)) {
            return false;
        }
        return ((AbstractCard)o).getName().equals(getName());
    }
}
