package a45858000w.magiclist2;

import java.util.Set;

/**
 * Created by 45858000w on 14/10/16.
 */

public class Carta {

    private String name;
    private String manaCost;
    private String type;
    private String rarity;
    private String text;
    private String power;
    private String imageUrl;
    private String Colors;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManaCost() {
        return manaCost;
    }

    public void setManaCost(String manaCost) {
        this.manaCost = manaCost;
    }

    public String getType() {
        return type;
    }



    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getColor() {
        return Colors;
    }

    public void setColor(String colors) {
        Colors = colors;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "name='" + name + '\'' +
                ", manaCost='" + manaCost + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", text='" + text + '\'' +
                ", power='" + power + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", colors=" + Colors +
                '}';
    }
}
