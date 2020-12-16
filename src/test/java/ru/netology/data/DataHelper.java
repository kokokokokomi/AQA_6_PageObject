package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import ru.netology.page.PersonAccountPage;

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

    public static AuthInfo getOtherAuthInfo(AuthInfo original) { return new AuthInfo("petya", "123qwerty"); }

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
        return new CardInfo("5559_0000_0000_0001", new PersonAccountPage().returnSecondCardInfo());
    }

    public static CardInfo getCardInfo2() {
        return new CardInfo("5559_0000_0000_0002", new PersonAccountPage().returnFirstCardInfo());
    }

    public static String shouldReturnRandomAmount() {
        List<String> givenList = Arrays.asList("100", "5000", "10000");
        Random random = new Random();
        return givenList.get(random.nextInt(givenList.size()));
    }

    public static String shouldReturnDoubleAmount() {
        return "100.10";
    }

    public static int expectAmount (int initialAmount, int minusAmount) {
        return initialAmount - minusAmount;
    }

    @Value
    @AllArgsConstructor
    public static class TransferInfo {
        private String cardNumber;
        private String startBalance;
    }

    public static TransferInfo getFirstTransferData() {
        return new TransferInfo("5559000000000001", "10000");
    }

    public static TransferInfo getSecondTransferData() {
        return new TransferInfo("5559000000000002", "10000");
    }

    public static int getBalanceAfterTransfer(int balanceBefore, int value) {
        int balanceAfter = balanceBefore - value;
        return balanceAfter;
    }

    public static int getBalanceAfterGet(int balanceBefore, int value) {
        int balanceAfter = balanceBefore + value;
        return balanceAfter;
    }
}
