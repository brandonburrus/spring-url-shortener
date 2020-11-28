package com.brandonburrus.urlshortener.util;

public class IdShortener {
    private static final char[] possibleIdChars;

    static {
        possibleIdChars = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        };
    }

    String shortenId(final double id) {
        double idCountdown = id;
        StringBuilder idBuilder = new StringBuilder();
        do {
            int remainder = (int) idCountdown % possibleIdChars.length;
            idBuilder.append(possibleIdChars[remainder]);
            idCountdown -= remainder;
            idCountdown /= possibleIdChars.length;
        } while (idCountdown > 0);
        return idBuilder.reverse().toString();
    }
}
