package carvellwakeman.shoppingapp.utils;


import java.util.Currency;
import java.util.Locale;


public class StringFormat {

    public static String currency(String formatString, Double input) {
        return String.format(Locale.ENGLISH, formatString, Currency.getInstance(Locale.US).getSymbol(), input);
    }

}
