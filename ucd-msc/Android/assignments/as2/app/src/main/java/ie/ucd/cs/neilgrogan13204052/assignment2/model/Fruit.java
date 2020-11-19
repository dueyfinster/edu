package ie.ucd.cs.neilgrogan13204052.assignment2.model;

import java.text.Normalizer;

import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON;
import static ie.ucd.cs.neilgrogan13204052.assignment2.data.Constants.TAG_ICON_LETTER;

/**
 * Created by ngrogan on 24/02/2015.
 */
public class Fruit {
    private String name;

    Fruit(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getIconName() { return TAG_ICON + name; }

    public String getLetterIconName() { return TAG_ICON_LETTER + flattenToAscii(String.valueOf(name.toLowerCase().charAt(0))); }


    private String flattenToAscii(String string) {
        StringBuilder sb = new StringBuilder(string.length());
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        for (char c : string.toCharArray()) {
            if (c <= '\u007F') sb.append(c);
        }
        return sb.toString();
    }
}
