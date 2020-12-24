package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import lombok.val;
import ru.netology.data.*;
import ru.netology.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {

    @BeforeEach
    void openBrowser() { open("http://localhost:9999"); }

    DashboardPage sendLogin() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode();
        return verificationPage.validVerify(verificationCode);
    }

    @Test
    @Order(1)
    void shouldTransferFromSecondToFirst() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(firstCardInfo.getNumber());
        val amount = getRandomIntAmount(secondCardStartBalance);
        val firstCardExpectedBalance = firstCardStartBalance + amount;
        val secondCardExpectedBalance = secondCardStartBalance - amount;
        transferPage.transferMoney(secondCardInfo.getNumber(), String.valueOf(amount));
        assertEquals(firstCardExpectedBalance, dashboardPage.getCardBalance(firstCardInfo.getNumber()));
        assertEquals(secondCardExpectedBalance, dashboardPage.getCardBalance(secondCardInfo.getNumber()));
    }

    @Test
    @Order(2)
    void shouldTransferFromFirstToSecond() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(secondCardInfo.getNumber());
        val amount = getRandomIntAmount(firstCardStartBalance);
        val firstCardExpectedBalance = firstCardStartBalance - amount;
        val secondCardExpectedBalance = secondCardStartBalance + amount;
        transferPage.transferMoney(firstCardInfo.getNumber(), String.valueOf(amount));
        assertEquals(firstCardExpectedBalance, dashboardPage.getCardBalance(firstCardInfo.getNumber()));
        assertEquals(secondCardExpectedBalance, dashboardPage.getCardBalance(secondCardInfo.getNumber()));
    }

    @Test
    @Order(3)
    void shouldMakeErrorWithEmptyField() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(secondCardInfo.getNumber());
        val amount = getRandomIntAmount(firstCardStartBalance);
        transferPage.transferMoneyError(String.valueOf(amount));
    }

    @Test
    @Order(4)
    void shouldTransferDoubleAmountFromFirstToSecond() {
        val dashboardPage = sendLogin();
        val firstCardInfo = getFirstCardInfo();
        val secondCardInfo = getSecondCardInfo();
        val firstCardStartBalance = dashboardPage.getCardBalance(firstCardInfo.getNumber());
        val secondCardStartBalance = dashboardPage.getCardBalance(secondCardInfo.getNumber());
        val transferPage = dashboardPage.chooseCardToTransfer(secondCardInfo.getNumber());
        val amount = getRandomDoubleAmount(secondCardStartBalance);
        val firstCardExpectedBalance = firstCardStartBalance - amount;
        val secondCardExpectedBalance = secondCardStartBalance + amount;
        transferPage.transferMoney(firstCardInfo.getNumber(), String.valueOf(amount));
        assertEquals(firstCardExpectedBalance, dashboardPage.getCardBalance(firstCardInfo.getNumber()));
        assertEquals(secondCardExpectedBalance, dashboardPage.getCardBalance(secondCardInfo.getNumber()));
    }
}
