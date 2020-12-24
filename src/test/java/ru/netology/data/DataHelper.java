package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private static Random random = new Random();

    private DataHelper() {}

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo("5559 0000 0000 0001");
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo("5559 0000 0000 0002");
    }

    public static int getRandomIntAmount(int balance) {
        return random.nextInt(balance);
    }

    public static double getRandomDoubleAmount(int balance) {
        int amountInKopeeks = random.nextInt(balance * 100);
        return (double) amountInKopeeks / 100;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class CardInfo {
        private String number;
    }
}
