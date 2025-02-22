package ie.ucd.cs.neilgrogan13204052.labs.strings.locales;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ngrogan on 14/04/2015.
 */
public class DateFormatter {
    public static void main(String[] args) {
        Date today = new Date();

        Locale india = new Locale("en", "IN");
        Locale america = new Locale("en", "US");
        Locale germany = new Locale("de", "DE");

        DateFormat nfIndia = DateFormat.getDateInstance(DateFormat.DEFAULT, india);
        DateFormat nfAmerica = DateFormat.getDateInstance(DateFormat.DEFAULT, america);
        DateFormat nfGermany = DateFormat.getDateInstance(DateFormat.DEFAULT, germany);

        System.out.println(nfIndia.format(today));
        System.out.println(nfAmerica.format(today));
        System.out.println(nfGermany.format(today));
    }
}


