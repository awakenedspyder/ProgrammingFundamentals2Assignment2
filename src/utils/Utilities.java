package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static boolean onlyContainsNumbers(String text) {
        return (text.matches("[0-9]+"));
    }

    public static String maxChars(String string, int maxlength){
        return (string.length()<=maxlength) ? string : string.substring(0,maxlength);
    }

    public static boolean validEmail(String email){
        return (email.contains("@") && email.contains("."));
    }

    public static boolean validIntRange(int start, int end, int value){
        return ((value >= start) && (value <= end));
    }
    public static boolean validDoubleRange(double start, double end, double value){
        return ((value >= start) && (value <= end));
    }

    public static boolean validDoubleHourlyRate(double number) { return (number >= 9.8);}

    public static boolean validIntNonNegative(int number) { return (number >= 0);}

    public static boolean validDoubleNonNegative(double number) { return (number >= 0);}

    public static boolean validIndex(int index, ArrayList list){
        return (index >= 0 && index < list.size());
    }

    public static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
