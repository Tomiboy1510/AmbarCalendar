package entity.util;

public class MyUtils {

    public static String abbreviate(String str, int maxLength) {
        return str.substring(0, Math.min(maxLength, str.length()));
    }
}
