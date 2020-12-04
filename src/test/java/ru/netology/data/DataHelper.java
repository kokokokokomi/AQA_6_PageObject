package ru.netology.data;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String number;
        private int balance;
    }

    public static CardInfo getCardInfo1() {
        return new CardInfo("5559_0000_0000_0001", new PersonAccountPage().returnOtherBalance());
    }

    public static CardInfo getCardInfo2() {
        return new CardInfo("5559_0000_0000_0002", new PersonAccountPage().returnBalance());
    }

    public static String shouldReturnRandomAmount() {
        List<String> givenList = Arrays.asList("100", "5000", "10000");
        Random random = new Random();
        return givenList.get(random.nextInt(givenList.size()));
    }

    public static String shouldReturnDoubleAmount() {
        return "100.10";
    }

    public static int getBalance() {
        return DataHelper.getCardInfo2().balance;
    }

    public static int getOtherBalance() {
        return DataHelper.getCardInfo1().balance;
    }

    public static int expectAmount (int initialAmount, int minusAmount) {
        return initialAmount - minusAmount;
    }
}
