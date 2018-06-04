package com.mgaudin.chip8;

public class HexUtils {
    public static String toHex(byte v, int padding) {
        return withPrefix(padWithZeros(Integer.toHexString(0x000000FF & v).toUpperCase(), padding));
    }

    private static String withPrefix(String hexString) {
        if (hexString.length() <= 1) {
            return hexString;
        }

        return "0x" + hexString;
    }

    public static String toHex(short v, int padding) {
        return withPrefix(padWithZeros(Integer.toHexString(0x0000FFFF & v).toUpperCase(), padding));
    }

    public static String toHex(int v, int padding) {
        return withPrefix(padWithZeros(Integer.toHexString(v).toUpperCase(), padding));
    }

    public static String padWithZeros(String s, int padding) {
        String paddingString = "";
        for (int i = 0; i < padding - s.length(); ++i) {
            paddingString += "0";
        }
        return paddingString + s;
    }

    public static boolean[] toBinary(byte b) {
        if (b == 0) {
            return new boolean[]{false, false, false, false, false, false, false, false};
        }

        String binaryString = padWithZeros(Integer.toBinaryString(b & 0xFF), 8);
        boolean[] result = new boolean[8];
        for (int i = 0; i < 8; ++i) {
            result[i] = binaryString.substring(i, i + 1).equals("1");
        }

        return result;
    }
}
