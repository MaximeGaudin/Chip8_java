package com.mgaudin.chip8.utils;

public class BCD {

    /*
     * long number to bcd byte array e.g. 123 --> (0000) 0001 0010 0011
     * e.g. 12 ---> 0001 0010
     */
    public static byte[] getBCDEquivalent(long num) {
        int digits = 0;

        long temp = num;
        while (temp != 0) {
            digits++;
            temp /= 10;
        }

        int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
        boolean isOdd = digits % 2 != 0;

        byte bcd[] = new byte[byteLen];

        for (int i = 0; i < digits; i++) {
            byte tmp = (byte) (num % 10);

            if (i == digits - 1 && isOdd)
                bcd[i / 2] = tmp;
            else if (i % 2 == 0)
                bcd[i / 2] = tmp;
            else {
                byte foo = (byte) (tmp << 4);
                bcd[i / 2] |= foo;
            }

            num /= 10;
        }

        for (int i = 0; i < byteLen / 2; i++) {
            byte tmp = bcd[i];
            bcd[i] = bcd[byteLen - i - 1];
            bcd[byteLen - i - 1] = tmp;
        }

        byte[] paddedResult = new byte[8];
        for (int i = 0; i < 8; ++i) {
            paddedResult[8 - i - 1] = (i >= bcd.length) ? 0 : bcd[i];
        }

        return paddedResult;
    }
}