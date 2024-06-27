package entity.util;

/**
 * A utility class that provides methods for manipulating strings
 */
public class MyStringUtils {

    /**
     * Abbreviates a string to the specified maximum length.
     * <p>
     *     If the length of the given string is greater than {@code maxLength},
     *     the string is truncated to {@code maxLength} characters. If not,
     *     the original string is returned.
     * </p>
     * @param str the string to abbreviate
     * @param maxLength the maximum length of the abbreviated string
     * @return the abbreviated string, truncated to {@code maxLength} characters.
     */
    public static String abbreviate(String str, int maxLength) {
        return str.substring(0, Math.min(maxLength, str.length()));
    }
}
