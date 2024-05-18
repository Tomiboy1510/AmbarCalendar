package entity.util;

public class MyUtils {

    public static String abbreviate(String str, int maxLength) {
        return str.substring(0, Math.min(maxLength, str.length()));
    }

    public static String capitalizeFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
